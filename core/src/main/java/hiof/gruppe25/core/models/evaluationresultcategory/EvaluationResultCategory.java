package hiof.gruppe25.core.models.evaluationresultcategory;

import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswerComparator;

import java.util.*;

public class EvaluationResultCategory {
    private final int mostPressingMaxSize;
    private final int MAX_PENALTY_ADDABLE = 50;
    private final int SCALE = 5;
    private int satisfactoryElements;
    private double rawPenaltySum;
    private double differencePenaltyScore;
    private double desiredPenaltySum;
    private double actualAverage;
    private double desiredAverage;
    private double maxPenaltySum;
    private double maxPenaltyWeightedSum;
    private double rawPenaltyByPrioritySum;
    private int categoryElements;
    private final HashMap<Integer, Integer> resultsByDifference = new HashMap<>(Map.of(
            0, 0,
            1, 0,
            2, 0,
            3, 0,
            4, 0,
            5,0
    ));

    private final PriorityQueue<EvaluationAnswer> mostPressing = new PriorityQueue<>(new EvaluationAnswerComparator());

    public EvaluationResultCategory(int MostPressingMaxSize) {
        this.mostPressingMaxSize = MostPressingMaxSize;
    }

    private void updatePenaltyScores(EvaluationAnswer evaluationAnswer) {
        desiredPenaltySum += Math.max(5 - evaluationAnswer.getDesiredValue(), 0) * evaluationAnswer.getControl().getPriority();
        rawPenaltySum += Math.max(5 - evaluationAnswer.getValue(), 0) * evaluationAnswer.getControl().getPriority();
        // Gets the maximum value an optimal answer would award. Used to get a coefficient of actual/desired.
        maxPenaltyWeightedSum += Math.max(evaluationAnswer.getDesiredValue(), 0) * evaluationAnswer.getControl().getPriority();
        // Ignores what desired value actually is, used to get a coefficient of what is actual compared to what is possible.
        rawPenaltyByPrioritySum += 5 * evaluationAnswer.getControl().getPriority();
        maxPenaltySum += MAX_PENALTY_ADDABLE;
    }

    private void updateAverageScores(EvaluationAnswer evaluationAnswer) {
        actualAverage += evaluationAnswer.getValue();
        desiredAverage += evaluationAnswer.getDesiredValue();
    }

    private void updateSatisfactoryElements(EvaluationAnswer evaluationAnswer) {
        if (evaluationAnswer.getValue() >= evaluationAnswer.getDesiredValue()) {
            satisfactoryElements++;
        }
    }

    private void updateDifferenceScores(EvaluationAnswer evaluationAnswer) {
        // Score based on the difference between desired and actual value. Used in 0-10 calculations of actual progress.
        differencePenaltyScore += Math.max(evaluationAnswer.getDesiredValue() - evaluationAnswer.getValue(), 0) * evaluationAnswer.getControl().getPriority();
        resultsByDifference.merge(evaluationAnswer.getDesiredValue() - evaluationAnswer.getValue(), 1, Integer::sum);
    }


    public void updateCategoryScore(EvaluationAnswer evaluationAnswer) {
        // -2 is used as N/A
        if (evaluationAnswer.getValue() == -2) {
            return;
        }

        if (evaluationAnswer.getValue() == -1) {
            evaluationAnswer.setValue(0);
        }

        updateAverageScores(evaluationAnswer);
        updatePenaltyScores(evaluationAnswer);
        updateSatisfactoryElements(evaluationAnswer);
        updateDifferenceScores(evaluationAnswer);

        categoryElements++;
    }

    // Is in practice reversed to allow for poll to remove the least significant value within the top segment.
    // Will have to be reversed before displaying.
    public void insertMostPressing(EvaluationAnswer evaluationAnswer) {
        if(evaluationAnswer.getValue() >= evaluationAnswer.getDesiredValue()) {
            return;
        }
        
        if (evaluationAnswer.getValue() == -2) {
            return;
        }

        if (evaluationAnswer.getValue() == -1) {
            evaluationAnswer.setValue(0);
        }

        mostPressing.add(evaluationAnswer);
        if (mostPressing.size() > mostPressingMaxSize) {
            mostPressing.poll();
        }
    }

    public List<EvaluationAnswer> getMostPressing() {
        List<EvaluationAnswer> results = new ArrayList<>(mostPressing);
        results.sort(new EvaluationAnswerComparator().reversed());
        return results;
    }

    // Difference divided by full penalty. Allows highlighting which areas are more important than others
    // As categories with higher value controls will be punished less.
    public double getUnweightedPenaltyAverage() {
        return (differencePenaltyScore / maxPenaltySum) * SCALE;
    }

    public double getUnweightedGapScoreAverage() {
        return SCALE - getUnweightedPenaltyAverage();
    }


    // Difference between obtained and desired value divided by the penalty that actually exists in the category.
    // This is the standard return, and the number 0-10 in each category determines how much "percentage" of the
    // desired percentage is completed. This is weighted by priority,
    public double getWeightedPenaltyAverage() {
        if (maxPenaltyWeightedSum == 0) {
            return 0;
        }
        return (differencePenaltyScore / maxPenaltyWeightedSum) * SCALE;
    }

    public double getWeightedGapScoreAverage() {
        return SCALE - getWeightedPenaltyAverage();
    }

    // Difference score that uses a penaltyByPriority to get actual/possible.

    public double getDifferenceValueWeightedByOnlyPriority() {
        return differencePenaltyScore / rawPenaltyByPrioritySum * SCALE;
    }

    public double getWeightedByPriorityGapScoreAverage() {
        return SCALE - getDifferenceValueWeightedByOnlyPriority();
    }

    // Desired/Possible, used in conjunction with actual/desired in graphs like spider diagrams where the difference
    // between these two also display what is actually possible.

    public double getDesiredPenaltyAverage() {
        return (desiredPenaltySum / rawPenaltyByPrioritySum) * SCALE;
    }

    public double getDesiredGapScoreAverage() {
        return SCALE - getDesiredPenaltyAverage();
    }

    // Ignores desired value completely, instead graphs just how much of the possible score is actually gained.

    public double getRawPenaltyAverage() {
        return (rawPenaltySum / rawPenaltyByPrioritySum) * SCALE;
    }

    public double getRawGapScoreAverage() {
        return SCALE - getRawPenaltyAverage();
    }

    public int getSatisfactoryElements() {
        return satisfactoryElements;
    }

    public int getCategoryElements() {
        return categoryElements;
    }

    public int getUnsatisfactoryElements() {
        return categoryElements - satisfactoryElements;
    }

    public double getScoreAverage() {
        return actualAverage / categoryElements;
    }

    public double getDesiredAverage() {
        return desiredAverage / categoryElements;
    }

    public Integer[] getResultsByDifference() {
        // Array should not actually include the number of 0-s, as this is covered elsewhere.
        resultsByDifference.remove(0);
        return resultsByDifference.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).toArray(Integer[]::new);
    }
}

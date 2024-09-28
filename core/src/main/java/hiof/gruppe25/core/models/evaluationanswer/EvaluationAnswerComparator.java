package hiof.gruppe25.core.models.evaluationanswer;

import java.util.Comparator;

public class EvaluationAnswerComparator implements Comparator<EvaluationAnswer> {
    @Override
    public int compare(EvaluationAnswer o1, EvaluationAnswer o2) {
        return Integer.compare(getEvaluationWeight(o1), getEvaluationWeight(o2));
    }

    public int getEvaluationWeight(EvaluationAnswer answer) {
        int differenceFromMax = Math.max(answer.getDesiredValue() - answer.getValue(), 0);
        return differenceFromMax * answer.getControl().getPriority();
    }
}

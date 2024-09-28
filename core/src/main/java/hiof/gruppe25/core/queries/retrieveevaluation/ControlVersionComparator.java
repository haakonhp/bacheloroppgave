package hiof.gruppe25.core.queries.retrieveevaluation;

import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;

import java.util.Comparator;
import java.util.regex.Pattern;

public class ControlVersionComparator implements Comparator<EvaluationAnswer> {
    @Override
    public int compare(EvaluationAnswer o1, EvaluationAnswer o2) {
        String controlId1 = o1.getControl().getControlID();
        String controlId2 = o2.getControl().getControlID();
        Pattern containsOnlyNumerical = Pattern.compile("^[0-9.]+$");

        // If it contains values other than numericals and peroids, fallback to lexiographical sort.
        if(!containsOnlyNumerical.matcher(controlId1).find() ||
                !containsOnlyNumerical.matcher(controlId2).find()) {
            return controlId1.compareTo(controlId2);
        }

        String[] array1 = controlId1.split("\\.");
        String[] array2 = controlId2.split("\\.");
        // Non pointbased versioning, return lexiographic comparator.
        if(array1.length <= 1 || array2.length <= 1) {
            return controlId1.compareTo(controlId2);
        }

        // Versioned number, check for differences at each step.
        for (int i = 0; i < array1.length; i++)
        {
            if(Integer.parseInt(array1[i]) < Integer.parseInt(array2[i]))
                return -1;
            if(Integer.parseInt(array1[i]) > Integer.parseInt(array2[i]))
                return 1;
        }
        return 0;
    }
}

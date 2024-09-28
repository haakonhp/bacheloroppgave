package hiof.gruppe25.core.models.evaluationresult;

import hiof.gruppe25.core.models.evaluationresultcategory.EvaluationResultCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EvaluationResult {
    private UUID evaluationId;
    private final int mostPressingMaxSize;

    public EvaluationResult(int mostPressingMaxSize) {
        this.mostPressingMaxSize = mostPressingMaxSize;
    }

    public EvaluationResult() {
        this(5);
    }

    private final Map<String, EvaluationResultCategory> categories = new HashMap<>();

    public EvaluationResultCategory accessCategory(String category) {
        if(!categories.containsKey(category)) {
            categories.put(category, new EvaluationResultCategory(mostPressingMaxSize));
        }
        return categories.get(category);
    }

    public UUID getEvaluationId() {
        return evaluationId;
    }
    public void setEvaluationId(UUID evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Map<String, EvaluationResultCategory> getCategories() {
        return categories;
    }
}

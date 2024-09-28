package hiof.gruppe25.core.persistenceinterfaces;

import hiof.gruppe25.core.models.evaluation.Evaluation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface iEvaluationRepository extends iRepository<Evaluation> {
    List<Evaluation> getEvaluationsByCorporation(UUID id);

    void updateTimeAndAnsweredQuestions(UUID id, LocalDateTime updatedAt, int answeredQuestions);
    int getUpdatedQuestionsCount(UUID id);
    List<String> getDistinctSources(UUID id);
    List<String> getDistinctFrameworks(UUID id);
}

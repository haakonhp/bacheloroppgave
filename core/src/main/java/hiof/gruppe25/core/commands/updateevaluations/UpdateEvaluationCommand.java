package hiof.gruppe25.core.commands.updateevaluations;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;

import java.util.List;
import java.util.UUID;

public class UpdateEvaluationCommand implements iCommand {
    public List<EvaluationAnswer> answerList;
    public UUID evaluationId;

    public void setEvaluationId(UUID evaluationId) {
        this.evaluationId = evaluationId;
    }
}

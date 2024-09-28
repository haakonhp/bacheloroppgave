package hiof.gruppe25.core.commands.updateevaluations;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.time.LocalDateTime;

public class UpdateEvaluationCommandHandler implements iCommandHandler {
    private final iRepository<EvaluationAnswer> evaluationAnswerRepository;
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public UpdateEvaluationCommandHandler(iRepository<EvaluationAnswer> evaluationAnswerRepository, iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationAnswerRepository = evaluationAnswerRepository;
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    public void execute(UpdateEvaluationCommand command) {
        evaluationAnswerRepository.mergeAll(command.answerList);
        int sumUpdates = evaluationRepository.getUpdatedQuestionsCount(command.evaluationId);
        evaluationRepository.updateTimeAndAnsweredQuestions(command.evaluationId, LocalDateTime.now(), sumUpdates);

        unitOfWork.saveChanges();
        unitOfWork.close();
    }

    @Override
    public void execute(iCommand command) {

        execute((UpdateEvaluationCommand) command);
    }
}

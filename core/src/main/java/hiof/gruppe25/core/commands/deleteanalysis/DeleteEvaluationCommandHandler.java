package hiof.gruppe25.core.commands.deleteanalysis;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class DeleteEvaluationCommandHandler implements iCommandHandler {
    private final iRepository<Evaluation> evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public DeleteEvaluationCommandHandler(iRepository<Evaluation> evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }

    public void execute(DeleteEvaluationCommand command) {
        evaluationRepository.delete(command.deleteID);
        unitOfWork.saveChanges();
        unitOfWork.close();
    }

    @Override
    public void execute(iCommand command) {
        execute((DeleteEvaluationCommand) command);
    }
}

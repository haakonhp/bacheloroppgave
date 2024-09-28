package hiof.gruppe25.core.commands.deletecorporation;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class DeleteCorporationCommandHandler implements iCommandHandler {
    private final iRepository<Corporation> corporationRepository;
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public DeleteCorporationCommandHandler(iRepository<Corporation> corporationRepository, iRepository<Evaluation> evaluationRepository, iEvaluationRepository evaluationRepository1, iUnitOfWork unitOfWork) {
        this.corporationRepository = corporationRepository;
        this.evaluationRepository = evaluationRepository1;
        this.unitOfWork = unitOfWork;
    }

    public void execute(DeleteCorporationCommand command) {
        List<Evaluation> evaluations = evaluationRepository.getEvaluationsByCorporation(command.deleteID);
        evaluations.forEach((evaluation -> evaluationRepository.delete(evaluation.getId())));
        unitOfWork.saveChanges();
        corporationRepository.delete(command.deleteID);


        unitOfWork.saveChanges();
        unitOfWork.close();
    }

    @Override
    public void execute(iCommand command) {
        execute((DeleteCorporationCommand) command);
    }
}

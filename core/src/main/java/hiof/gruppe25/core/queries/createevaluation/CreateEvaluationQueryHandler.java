package hiof.gruppe25.core.queries.createevaluation;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateEvaluationQueryHandler implements iQueryHandler<UUID,CreateEvaluationQuery>  {
    private final iControlRepository controlRepository;
    private final iRepository<Evaluation> evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public CreateEvaluationQueryHandler(iControlRepository controlRepository, iRepository<Evaluation> evaluationRepository, iUnitOfWork unitOfWork) {
        this.controlRepository = controlRepository;
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public UUID execute(CreateEvaluationQuery command) {
        List<Control> applicableControls = controlRepository.getFilteredBySourcesAndFrameworks(command.sources, command.frameworks);
        List<EvaluationAnswer> emptyEvaluations = applicableControls.stream().map((control) -> new EvaluationAnswer(control, LocalDateTime.now(), -1, 5)).toList();
        Evaluation evaluation = new Evaluation(command.evalName, LocalDateTime.now(), command.corporation, -1,command.sources, command.frameworks, emptyEvaluations, applicableControls.size());

        evaluationRepository.add(evaluation);
        unitOfWork.saveChanges();
        unitOfWork.close();
        return evaluation.getId();
    }

}

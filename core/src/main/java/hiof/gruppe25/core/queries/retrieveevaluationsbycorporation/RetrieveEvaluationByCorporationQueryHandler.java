package hiof.gruppe25.core.queries.retrieveevaluationsbycorporation;

import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveEvaluationByCorporationQueryHandler implements iQueryHandler<List<Evaluation>, RetrieveEvaluationByCorporationQuery>  {
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveEvaluationByCorporationQueryHandler(iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<Evaluation> execute(RetrieveEvaluationByCorporationQuery command) {
       List<Evaluation> evaluations = evaluationRepository.getEvaluationsByCorporation(command.id);
       unitOfWork.close();
       return evaluations;
    }

}

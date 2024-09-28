package hiof.gruppe25.core.queries.retrieveevaulationframeworks;

import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveEvaluationFrameworksQueryHandler implements iQueryHandler<List<String>, RetrieveEvaluationFrameworksQuery>  {
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveEvaluationFrameworksQueryHandler(iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<String> execute(RetrieveEvaluationFrameworksQuery command) {
       List<String> sources = evaluationRepository.getDistinctFrameworks(command.id);
       unitOfWork.close();
       return sources;
    }

}

package hiof.gruppe25.core.queries.retrieveevaluationsources;

import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveEvaluationSourcesQueryHandler implements iQueryHandler<List<String>, RetrieveEvaluationSourcesQuery>  {
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveEvaluationSourcesQueryHandler(iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<String> execute(RetrieveEvaluationSourcesQuery command) {
       List<String> sources = evaluationRepository.getDistinctSources(command.id);
       unitOfWork.close();
       return sources;
    }

}

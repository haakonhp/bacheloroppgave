package hiof.gruppe25.core.queries.retrievedistinctsources;

import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveDistinctSourcesQueryHandler implements iQueryHandler<List<String>, RetrieveDistinctSourcesQuery> {
    private final iControlRepository controlRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveDistinctSourcesQueryHandler(iControlRepository controlRepository, iUnitOfWork unitOfWork) {
        this.controlRepository = controlRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<String> execute(RetrieveDistinctSourcesQuery retrieveControlQuery) {
       List<String> distinctSources = controlRepository.getDistinctSources();
       unitOfWork.close();
       return distinctSources;
    }
}

package hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources;

import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iFrameworkRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveFrameworksFilteredByControlSourcesQueryHandler implements iQueryHandler<List<String>, RetrieveFrameworksFilteredByControlSourcesQuery> {
    private final iFrameworkRepository frameworkRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveFrameworksFilteredByControlSourcesQueryHandler(iFrameworkRepository frameworkRepository, iUnitOfWork unitOfWork) {
        this.frameworkRepository = frameworkRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<String> execute(RetrieveFrameworksFilteredByControlSourcesQuery retrieveFrameworksQuery) {
       List<String> distinctFrameworksFilteredBySources = frameworkRepository.getDistinctFrameworksByFilteredSources(retrieveFrameworksQuery.filters);
       unitOfWork.close();
       return distinctFrameworksFilteredBySources;
    }
}

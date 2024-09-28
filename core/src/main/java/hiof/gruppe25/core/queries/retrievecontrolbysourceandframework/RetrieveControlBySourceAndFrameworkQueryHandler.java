package hiof.gruppe25.core.queries.retrievecontrolbysourceandframework;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveControlBySourceAndFrameworkQueryHandler implements iQueryHandler<List<Control>, RetrieveControlBySourceAndFrameworkQuery> {
    private final iControlRepository controlRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveControlBySourceAndFrameworkQueryHandler(iControlRepository controlRepository, iUnitOfWork unitOfWork) {
        this.controlRepository = controlRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<Control> execute(RetrieveControlBySourceAndFrameworkQuery retrieveFrameworksQuery) {
       List<Control> controls = controlRepository.getFilteredBySourcesAndFrameworks(retrieveFrameworksQuery.sources, retrieveFrameworksQuery.frameworks);
       unitOfWork.close();
       return controls;
    }
}

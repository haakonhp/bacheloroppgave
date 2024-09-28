package hiof.gruppe25.core.queries.retrievecontrol;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveControlQueryHandler implements iQueryHandler<List<Control>, RetrieveControlQuery> {
    private final iRepository<Control> controlRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveControlQueryHandler(iRepository<Control> controlRepository, iUnitOfWork unitOfWork) {
        this.controlRepository = controlRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<Control> execute(RetrieveControlQuery retrieveControlQuery) {
       List<Control> controls = controlRepository.getAll();
       unitOfWork.close();
       return controls;
    }
}

package hiof.gruppe25.core.queries.retrievecorporations;

import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveCorporationQueryHandler implements iQueryHandler<List<Corporation>, RetrieveCorporationQuery> {
    private final iRepository<Corporation> corporationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveCorporationQueryHandler(iRepository<Corporation> controlRepository, iUnitOfWork unitOfWork) {
        this.corporationRepository = controlRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public List<Corporation> execute(RetrieveCorporationQuery retrieveControlQuery) {
       List<Corporation> corporations = corporationRepository.getAll();
       unitOfWork.close();
       return corporations;
    }
}

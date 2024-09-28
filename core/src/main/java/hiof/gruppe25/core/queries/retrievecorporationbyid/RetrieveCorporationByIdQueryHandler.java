package hiof.gruppe25.core.queries.retrievecorporationbyid;

import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.UUID;

public class RetrieveCorporationByIdQueryHandler implements iQueryHandler<Corporation, RetrieveCorporationByIdQuery> {
    private final iRepository<Corporation> corporationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveCorporationByIdQueryHandler(iRepository<Corporation> corporationRepository, iUnitOfWork unitOfWork) {
        this.corporationRepository = corporationRepository;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Corporation execute(RetrieveCorporationByIdQuery retrieveCorporationByIdQuery) {
        UUID id = retrieveCorporationByIdQuery.getId();
        Corporation corporation = corporationRepository.getById(id);
        unitOfWork.close();
        return corporation;
    }
}

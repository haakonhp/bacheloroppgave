package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;

public class CorporationRepository extends BaseRepository<Corporation> implements iRepository<Corporation> {
    public CorporationRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, Corporation.class);
    }
}

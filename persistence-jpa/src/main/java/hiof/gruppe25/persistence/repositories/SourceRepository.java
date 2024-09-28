package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.source.Source;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;

public class SourceRepository extends BaseRepository<Source> implements iRepository<Source> {
    public SourceRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, Source.class);
    }
}

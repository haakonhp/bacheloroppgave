package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.questionresponse.QuestionResponse;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;

public class ResponseRepository extends BaseRepository<QuestionResponse> implements iRepository<QuestionResponse> {
    public ResponseRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, QuestionResponse.class);
    }
}

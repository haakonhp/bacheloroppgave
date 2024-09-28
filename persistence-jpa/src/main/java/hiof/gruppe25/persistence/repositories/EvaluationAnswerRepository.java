package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

public class EvaluationAnswerRepository extends BaseRepository<EvaluationAnswer> implements iRepository<EvaluationAnswer> {
    public EvaluationAnswerRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, EvaluationAnswer.class);
    }
    public void merge(EvaluationAnswer answer) {
        CriteriaBuilder builder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<EvaluationAnswer> criteria = builder.createCriteriaUpdate(EvaluationAnswer.class);
        Root<EvaluationAnswer> EvaluationRoot = criteria.from(EvaluationAnswer.class);

        criteria.set(EvaluationRoot.get("value"), answer.getValue());
        criteria.set(EvaluationRoot.get("desiredValue"), answer.getDesiredValue());
        criteria.set(EvaluationRoot.get("lastUpdatedAt"), answer.getLastUpdatedAt());

        criteria.where(builder.equal(EvaluationRoot.get("id"), answer.getId()));

        unitOfWork.getEntityManager().createQuery(criteria).executeUpdate();
    }
}

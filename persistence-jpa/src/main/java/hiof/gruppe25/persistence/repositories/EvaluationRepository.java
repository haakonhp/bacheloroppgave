package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;
import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.persistence.predicates.EvaluationPredicateBuilder;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import jakarta.persistence.criteria.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EvaluationRepository extends BaseRepository<Evaluation> implements iEvaluationRepository {
    public EvaluationRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, Evaluation.class);
    }

    public Evaluation getById(UUID id) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Evaluation> controlQuery = criteriaBuilder.createQuery(Evaluation.class);

        Root<Evaluation> root = controlQuery.from(Evaluation.class);

        cacheApplicableControls(id);

        root.fetch("answers", JoinType.LEFT);

        CriteriaQuery<Evaluation> singleElement = controlQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        return unitOfWork.getEntityManager().createQuery(singleElement).getSingleResult();
    }

    public void cacheApplicableControls(UUID id) {
        List<String> sources = getDistinctSources(id);
        List<String> frameworks = getDistinctFrameworks(id);

        new ControlRepository(unitOfWork).getFilteredBySourcesAndFrameworks(sources, frameworks);
    }

    public List<String> getDistinctSources(UUID id) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<String> sourcesBaseQuery = criteriaBuilder.createQuery(String.class);
        Root<Evaluation> sourceRoot = sourcesBaseQuery.from(Evaluation.class);

        Join<Evaluation, EvaluationAnswer> sourceAnswers = sourceRoot.join("answers");
        Join<Control, EvaluationAnswer> sourceControls = sourceAnswers.join("control");

        CriteriaQuery<String> sourcesQuery = sourcesBaseQuery.select(sourceControls.get("source")).where(criteriaBuilder.equal(sourceRoot.get("id"), id)).distinct(true);

        return unitOfWork.getEntityManager().createQuery(sourcesQuery).getResultList();
    }


    public List<String> getDistinctFrameworks(UUID id) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<String> frameworkBaseQuery = criteriaBuilder.createQuery(String.class);
        Root<Evaluation> frameworkRoot = frameworkBaseQuery.from(Evaluation.class);

        Join<Evaluation, EvaluationAnswer> frameworkAnswers = frameworkRoot.join("answers");
        Join<Control, EvaluationAnswer> frameworkControls = frameworkAnswers.join("control");
        Join<FrameworkReference, Control> frameworkReferences = frameworkControls.join("implementingFrameworks");
        Join<Framework, FrameworkReference> frameworks = frameworkReferences.join("framework");

        CriteriaQuery<String> frameworkQuery = frameworkBaseQuery.select(frameworks.get("framework")).where(criteriaBuilder.equal(frameworkRoot.get("id"), id)).distinct(true);

        return unitOfWork.getEntityManager().createQuery(frameworkQuery).getResultList();
    }


    public List<Evaluation> getEvaluationsByCorporation(UUID id) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Evaluation> controlQuery = criteriaBuilder.createQuery(Evaluation.class);
        Root<Evaluation> root = controlQuery.from(Evaluation.class);
        CriteriaQuery<Evaluation> evaluations = controlQuery.multiselect(
                root.get("id"),
                root.get("name"),
                root.get("createdAt"),
                root.get("corporation"),
                root.get("createdByTemplate"),
                root.get("answerCount"),
                root.get("updatedAt"),
                root.get("completedAnswerCount")
        ).where(criteriaBuilder.equal(root.get("corporation"), id));

        return unitOfWork.getEntityManager().createQuery(evaluations).getResultList();
    }

    @Override
    public void updateTimeAndAnsweredQuestions(UUID id, LocalDateTime updatedAt, int answeredQuestions) {
        CriteriaBuilder builder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<Evaluation> criteria = builder.createCriteriaUpdate(Evaluation.class);
        Root<Evaluation> root = criteria.from(Evaluation.class);

        criteria.set(root.get("updatedAt"), updatedAt);
        criteria.set(root.get("completedAnswerCount"), answeredQuestions);
        criteria.where(builder.equal(root.get("id"), id));
        unitOfWork.getEntityManager().createQuery(criteria).executeUpdate();
    }

    public int getUpdatedQuestionsCount(UUID id) {
        CriteriaBuilder builder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Evaluation> root = query.from(Evaluation.class);

        Join<EvaluationAnswer, Evaluation> answers = root.join("answers", JoinType.INNER);
        query.select(builder.count(answers.get("value")));
        Predicate predicate = new EvaluationPredicateBuilder()
                .withId(id)
                .withAnswerValueOtherThan(-1)
                .resolvePredicate(root,builder);

        return unitOfWork.getEntityManager().createQuery(query.where(predicate)).getSingleResult().intValue();
    }


}

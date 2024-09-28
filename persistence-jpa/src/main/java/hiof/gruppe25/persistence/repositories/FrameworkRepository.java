package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.persistenceinterfaces.iFrameworkRepository;
import hiof.gruppe25.persistence.predicates.ControlPredicateBuilder;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class FrameworkRepository extends BaseRepository<Framework> implements iFrameworkRepository {

    public FrameworkRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, Framework.class);
    }
    @Override
    public List<Framework> getAll() {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Framework> frameworkQuery = criteriaBuilder.createQuery(Framework.class);
        Root<Framework> root = frameworkQuery.from(Framework.class);
        CriteriaQuery<Framework> allElements = frameworkQuery.select(root);

        return unitOfWork.getEntityManager().createQuery(allElements).getResultList();
    }

    public List<String> getDistinctFrameworksByFilteredSources(List<String> sources) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();

        ControlPredicateBuilder predicate = new ControlPredicateBuilder().withSources(sources);

        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Control> root = query.from(Control.class);

        Join<Control, Framework> join = root.join("implementingFrameworks").join("framework");

        CriteriaQuery<String> elements = query.select(join.get("framework")).where(predicate.resolvePredicate(root, criteriaBuilder)).distinct(true);
        return unitOfWork.getEntityManager().createQuery(elements).getResultList();
    }




}


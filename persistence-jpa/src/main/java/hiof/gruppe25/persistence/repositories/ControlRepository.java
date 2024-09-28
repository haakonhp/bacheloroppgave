package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;
import hiof.gruppe25.core.models.source.Source;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.persistence.predicates.ControlPredicateBuilder;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import jakarta.persistence.criteria.*;


import java.util.List;

import static java.util.Objects.isNull;

public class ControlRepository extends BaseRepository<Control> implements iControlRepository {

    public ControlRepository(JPAUnitOfWork unitOfWork) {
        super(unitOfWork, Control.class);
    }

    // Utility functions, the query is seperated to prevent queries that cause overly large cartesian products, and satisfies
    // requirements defined by JPA.
    private void cacheReferences(CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<FrameworkReference> referenceQuery = criteriaBuilder.createQuery(FrameworkReference.class);
        Root<FrameworkReference> referenceRoot = referenceQuery.from(FrameworkReference.class);

        referenceRoot.fetch("framework", JoinType.LEFT);
        referenceRoot.fetch("dbReferences", JoinType.LEFT);
        unitOfWork.getEntityManager().createQuery(referenceQuery).getResultList();
    }

    private List<Control> getWithPredicatePart(ControlPredicateBuilder predicate, int part) {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Control> controlQuery = criteriaBuilder.createQuery(Control.class);
        Root<Control> root = controlQuery.from(Control.class);

        switch (part) {
            case 0 -> {cacheReferences(criteriaBuilder); return null;}
            case 1 -> root.fetch("cyberDefenseAsset", JoinType.LEFT);
            case 2 -> root.fetch("controlTypes", JoinType.LEFT);
            case 3 -> root.fetch("implementingFrameworks", JoinType.LEFT);
        }

        CriteriaQuery<Control> allElements = isNull(predicate) ? controlQuery.select(root) :
                controlQuery.select(root).where((predicate.resolvePredicate(root, criteriaBuilder)));

        return unitOfWork.getEntityManager().createQuery(allElements).getResultList();
    }

    private List<Control> getControls(ControlPredicateBuilder predicate) {
        getWithPredicatePart(predicate, 0);
        getWithPredicatePart(predicate, 1);
        getWithPredicatePart(predicate, 2);
        return getWithPredicatePart(predicate, 3);
    }
    // Query functions

    @Override
    public List<Control> getAll() {
        return getControls(null);
    }

    @Override
    public List<Control> getWithPriorityOver(int priority) {
        return getControls(new ControlPredicateBuilder().hasPriorityOver(5));
    }

    public List<Control> getFilteredBySources(List<String> sources) {
        return getControls(new ControlPredicateBuilder().withSources(sources));
    }
    public List<Control> getFilteredByFrameworks(List<String> framewokrs) {
        return getControls(new ControlPredicateBuilder().withFrameworks(framewokrs));
    }

    @Override
    public List<Control> getFilteredBySourcesAndFrameworks(List<String> sources, List<String> frameworks) {
        return getControls(new ControlPredicateBuilder().withSources(sources).withFrameworks(frameworks));
    }
    public List<String> getDistinctSources() {
       SourceRepository sourceRepository = new SourceRepository(unitOfWork);
       return sourceRepository.getAll().stream().map((Source::getSource)).toList();
    }
}


package hiof.gruppe25.persistence.predicates;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PredicateBuilder<T> {
    protected final List<DelayedPredicate<T, ?>> predicates = new ArrayList<>();
    public Predicate resolvePredicate(Root<T> root, CriteriaBuilder builder) {
        return builder.and(
                predicates.stream()
                .map((delayedPredicate -> delayedPredicate.resolvePredicate(root, builder)))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }
}

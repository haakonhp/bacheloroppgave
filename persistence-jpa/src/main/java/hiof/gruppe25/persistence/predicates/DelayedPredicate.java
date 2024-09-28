package hiof.gruppe25.persistence.predicates;

import jakarta.persistence.criteria.*;
import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class DelayedPredicate<T, G> {
    private final String name;
    private final SQLOperation operation;
    private G value;
    private List<G> values;
    private String firstLevelJoin;
    private String secondLevelJoin;

    public DelayedPredicate(String name, String firstLevelJoin, G value, SQLOperation operation) {
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.firstLevelJoin = firstLevelJoin;
    }


    public DelayedPredicate(String name, String firstLevelJoin, String secondLevelJoin, List<G> values, SQLOperation operation) {
        this.name = name;
        this.operation = operation;
        this.values = values;
        this.firstLevelJoin = firstLevelJoin;
        this.secondLevelJoin = secondLevelJoin;
    }

    public DelayedPredicate(String name, G value, SQLOperation operation) {
        this.name = name;
        this.value = value;
        this.operation = operation;
    }

    public DelayedPredicate(String name, List<G> values, SQLOperation operation) {
        this.name = name;
        this.values = values;
        this.operation = operation;
    }

    public Join<T, ?> getFirstJoin(Root<T> root) {
        if (!root.getJoins().isEmpty()) {
            Join<T, ?> resolvedJoin = root.getJoins().stream().filter((join) -> Objects.equals(join.getAttribute().getName(), firstLevelJoin)).findFirst().orElse(null);
            if(!isNull(resolvedJoin)) return resolvedJoin;
        }
       if(!root.getFetches().isEmpty()) {
           Set<Join<T,?>> joins = root.getFetches().stream().map(fetch -> (Join<T,?>) fetch).collect(Collectors.toSet());
           Join<T, ?> resolvedJoin = joins.stream().filter((join) -> Objects.equals(join.getAttribute().getName(), firstLevelJoin)).findFirst().orElse(null);
           if(!isNull(resolvedJoin)) return resolvedJoin;
       }
       return null;
    }
    // Second join is a functionality to allow a resolved fetch to be "explored" one step further via join.
    // Fetches will already have retrieved this data, but it's impossible to actually refer to it within the context
    // of a Criteria. This functionality allows these values to still be accessed.
    public From<T, ?> resolveDepth(Root<T> root) {
        if (!isNull(secondLevelJoin)) {
            Join<T, ?> firstJoin = getFirstJoin(root);
            if(!isNull(firstJoin)) return firstJoin.join(secondLevelJoin, JoinType.LEFT);
        }
        if (!isNull(firstLevelJoin)) return getFirstJoin(root);
        return root;
    }

    public Predicate resolvePredicate(Root<T> root, CriteriaBuilder builder) {
        From<T,?> resolvedRoot = resolveDepth(root);
        if(isNull(resolvedRoot)) return null;

        switch (operation) {
            case GREATER_THAN -> {
                return builder.greaterThan(resolvedRoot.get(name), Integer.parseInt(value.toString()));
            }
            case LESS_THAN -> {
                return builder.lessThan(resolvedRoot.get(name), Integer.parseInt(value.toString()));
            }
            case OTHER_THAN -> {
                return builder.notEqual(resolvedRoot.get(name), Integer.parseInt(value.toString()));
            }
            case LIKE -> {
                return builder.like(resolvedRoot.get(name), value.toString());
            }
            case IN -> {
                values.remove(null);
                if(isNull(values) || values.isEmpty()) return null;
                In<G> inValues = builder.in(resolvedRoot.get(name));
                values.forEach(inValues::value);
                return inValues;
            }
            case EQUAL -> {
                return builder.equal(resolvedRoot.get(name), value);
            }
        }
        return null;
    }
}

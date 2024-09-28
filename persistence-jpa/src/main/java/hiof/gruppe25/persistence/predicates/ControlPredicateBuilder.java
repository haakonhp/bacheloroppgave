package hiof.gruppe25.persistence.predicates;

import hiof.gruppe25.core.models.control.Control;

import java.util.List;

public class ControlPredicateBuilder extends PredicateBuilder<Control> {
    public ControlPredicateBuilder hasPriorityOver(int priority) {
        predicates.add(new DelayedPredicate<>("priority", priority, SQLOperation.GREATER_THAN));
        return this;
    }
    public ControlPredicateBuilder withSources(List<String> sources) {
        predicates.add(new DelayedPredicate<>("source", sources, SQLOperation.IN));
        return this;
    }
    public ControlPredicateBuilder withFrameworks(List<String> frameworks) {
        predicates.add(new DelayedPredicate<>("framework", "implementingFrameworks", "framework", frameworks, SQLOperation.IN));
        return this;
    }
}

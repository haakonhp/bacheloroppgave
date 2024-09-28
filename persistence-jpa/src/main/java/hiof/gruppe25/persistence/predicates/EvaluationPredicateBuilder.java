package hiof.gruppe25.persistence.predicates;

import hiof.gruppe25.core.models.evaluation.Evaluation;

import java.util.UUID;

public class EvaluationPredicateBuilder extends PredicateBuilder<Evaluation> {

    public EvaluationPredicateBuilder withId(UUID id) {
        predicates.add(new DelayedPredicate<>("id", id, SQLOperation.EQUAL));
        return this;
    }
    public EvaluationPredicateBuilder withAnswerValueGreaterThan(int value) {
        predicates.add(new DelayedPredicate<>("value", "answers", value, SQLOperation.GREATER_THAN));
        return this;
    }
    public EvaluationPredicateBuilder withAnswerValueOtherThan(int value) {
        predicates.add(new DelayedPredicate<>("value", "answers", value, SQLOperation.OTHER_THAN));
        return this;
    }
}

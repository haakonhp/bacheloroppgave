package hiof.gruppe25.core.queries.createevaluation;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.List;
import java.util.UUID;

public class CreateEvaluationQuery implements iQuery {
    public String evalName;
    public UUID corporation;
    public List<String> sources;
    public List<String> frameworks;
}

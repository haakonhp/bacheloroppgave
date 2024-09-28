package hiof.gruppe25.core.queries.retrieveevaluation;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class RetrieveEvaluationQuery implements iQuery {
   public UUID id;
   public RetrieveEvaluationQuery(UUID id) {
      this.id = id;
   }
}

package hiof.gruppe25.core.queries.retrieveevaulationframeworks;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class RetrieveEvaluationFrameworksQuery implements iQuery {
   public UUID id;

   public RetrieveEvaluationFrameworksQuery(UUID id) {
      this.id = id;
   }
}

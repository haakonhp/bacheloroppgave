package hiof.gruppe25.core.queries.retrieveevaluationsources;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class RetrieveEvaluationSourcesQuery implements iQuery {
   public UUID id;

   public RetrieveEvaluationSourcesQuery(UUID id) {
      this.id = id;
   }
}

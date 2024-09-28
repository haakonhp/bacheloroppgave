package hiof.gruppe25.core.queries.retrieveevaluationsbycorporation;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class RetrieveEvaluationByCorporationQuery implements iQuery {
   public UUID id;

   public RetrieveEvaluationByCorporationQuery(UUID id) {
      this.id = id;
   }
}

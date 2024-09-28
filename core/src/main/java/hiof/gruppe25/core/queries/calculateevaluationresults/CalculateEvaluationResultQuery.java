package hiof.gruppe25.core.queries.calculateevaluationresults;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class CalculateEvaluationResultQuery implements iQuery {
   public int mostPressingMaxSize = 5;
   public UUID id;

   public CalculateEvaluationResultQuery(UUID id) {
      this.id = id;
   }

   public CalculateEvaluationResultQuery(UUID id, int mostPressingMaxSize) {
      this.id = id;
      this.mostPressingMaxSize = mostPressingMaxSize;
   }
}

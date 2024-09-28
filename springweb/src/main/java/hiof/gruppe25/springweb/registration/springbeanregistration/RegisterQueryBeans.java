package hiof.gruppe25.springweb.registration.springbeanregistration;

import hiof.gruppe25.core.queries.calculateevaluationresults.CalculateEvaluationResultQueryHandler;
import hiof.gruppe25.core.queries.createbackup.CreateBackupQueryHandler;
import hiof.gruppe25.core.queries.createevaluation.CreateEvaluationQueryHandler;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQueryHandler;
import hiof.gruppe25.core.queries.retrievecontrolbysourceandframework.RetrieveControlBySourceAndFrameworkQueryHandler;
import hiof.gruppe25.core.queries.retrievecorporationbyid.RetrieveCorporationByIdQueryHandler;
import hiof.gruppe25.core.queries.retrievecorporations.RetrieveCorporationQueryHandler;
import hiof.gruppe25.core.queries.retrievedistinctsources.RetrieveDistinctSourcesQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluation.RetrieveEvaluationQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluationsbycorporation.RetrieveEvaluationByCorporationQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluationsources.RetrieveEvaluationSourcesQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaulationframeworks.RetrieveEvaluationFrameworksQueryHandler;
import hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources.RetrieveFrameworksFilteredByControlSourcesQueryHandler;
import org.springframework.context.support.GenericApplicationContext;

public class RegisterQueryBeans {
    public static void registerQueries(GenericApplicationContext ctx) {
        ctx.registerBean(CreateEvaluationQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveControlQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveFrameworksFilteredByControlSourcesQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveDistinctSourcesQueryHandler.class, Scopes::prototypeScoped);

        ctx.registerBean(RetrieveCorporationQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveCorporationByIdQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveControlBySourceAndFrameworkQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveEvaluationQueryHandler.class, Scopes::prototypeScoped);

        ctx.registerBean(RetrieveEvaluationByCorporationQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(CalculateEvaluationResultQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(CreateBackupQueryHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(RetrieveEvaluationSourcesQueryHandler.class, Scopes::prototypeScoped);

        ctx.registerBean(RetrieveEvaluationFrameworksQueryHandler.class, Scopes::prototypeScoped);
    }
}

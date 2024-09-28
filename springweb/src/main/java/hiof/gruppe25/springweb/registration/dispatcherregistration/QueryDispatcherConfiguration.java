package hiof.gruppe25.springweb.registration.dispatcherregistration;

import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommand;
import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommandHandler;
import hiof.gruppe25.core.queries.calculateevaluationresults.CalculateEvaluationResultQuery;
import hiof.gruppe25.core.queries.calculateevaluationresults.CalculateEvaluationResultQueryHandler;
import hiof.gruppe25.core.queries.createbackup.CreateBackupQuery;
import hiof.gruppe25.core.queries.createbackup.CreateBackupQueryHandler;
import hiof.gruppe25.core.queries.createevaluation.CreateEvaluationQuery;
import hiof.gruppe25.core.queries.createevaluation.CreateEvaluationQueryHandler;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQuery;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQueryHandler;
import hiof.gruppe25.core.queries.retrievecontrolbysourceandframework.RetrieveControlBySourceAndFrameworkQuery;
import hiof.gruppe25.core.queries.retrievecontrolbysourceandframework.RetrieveControlBySourceAndFrameworkQueryHandler;
import hiof.gruppe25.core.queries.retrievecorporationbyid.RetrieveCorporationByIdQuery;
import hiof.gruppe25.core.queries.retrievecorporationbyid.RetrieveCorporationByIdQueryHandler;
import hiof.gruppe25.core.queries.retrievecorporations.RetrieveCorporationQuery;
import hiof.gruppe25.core.queries.retrievecorporations.RetrieveCorporationQueryHandler;
import hiof.gruppe25.core.queries.retrievedistinctsources.RetrieveDistinctSourcesQuery;
import hiof.gruppe25.core.queries.retrievedistinctsources.RetrieveDistinctSourcesQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluation.RetrieveEvaluationQuery;
import hiof.gruppe25.core.queries.retrieveevaluation.RetrieveEvaluationQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluationsbycorporation.RetrieveEvaluationByCorporationQuery;
import hiof.gruppe25.core.queries.retrieveevaluationsbycorporation.RetrieveEvaluationByCorporationQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaluationsources.RetrieveEvaluationSourcesQuery;
import hiof.gruppe25.core.queries.retrieveevaluationsources.RetrieveEvaluationSourcesQueryHandler;
import hiof.gruppe25.core.queries.retrieveevaulationframeworks.RetrieveEvaluationFrameworksQuery;
import hiof.gruppe25.core.queries.retrieveevaulationframeworks.RetrieveEvaluationFrameworksQueryHandler;
import hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources.RetrieveFrameworksFilteredByControlSourcesQuery;
import hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources.RetrieveFrameworksFilteredByControlSourcesQueryHandler;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;

public class QueryDispatcherConfiguration {
    public static void configureQueryDispatcher() {
        QueryDispatcher.register(CreateEvaluationQuery.class, CreateEvaluationQueryHandler.class);
        QueryDispatcher.register(RetrieveControlQuery.class, RetrieveControlQueryHandler.class);
        QueryDispatcher.register(RetrieveDistinctSourcesQuery.class, RetrieveDistinctSourcesQueryHandler.class);
        QueryDispatcher.register(RetrieveFrameworksFilteredByControlSourcesQuery.class, RetrieveFrameworksFilteredByControlSourcesQueryHandler.class);

        QueryDispatcher.register(RetrieveControlBySourceAndFrameworkQuery.class, RetrieveControlBySourceAndFrameworkQueryHandler.class);
        QueryDispatcher.register(RetrieveEvaluationQuery.class, RetrieveEvaluationQueryHandler.class);
        QueryDispatcher.register(RetrieveEvaluationSourcesQuery.class, RetrieveEvaluationSourcesQueryHandler.class);
        QueryDispatcher.register(RetrieveEvaluationFrameworksQuery.class, RetrieveEvaluationFrameworksQueryHandler.class);

        QueryDispatcher.register(RetrieveEvaluationByCorporationQuery.class, RetrieveEvaluationByCorporationQueryHandler.class);
        QueryDispatcher.register(CalculateEvaluationResultQuery.class, CalculateEvaluationResultQueryHandler.class);
        QueryDispatcher.register(UpdateEvaluationCommand.class, UpdateEvaluationCommandHandler.class);
        QueryDispatcher.register(CreateBackupQuery.class, CreateBackupQueryHandler.class);

        QueryDispatcher.register(RetrieveCorporationQuery.class, RetrieveCorporationQueryHandler.class);
        QueryDispatcher.register(RetrieveCorporationByIdQuery.class, RetrieveCorporationByIdQueryHandler.class);
    }
}

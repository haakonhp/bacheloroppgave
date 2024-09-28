package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.commands.deleteanalysis.DeleteEvaluationCommand;
import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.queries.createevaluation.CreateEvaluationQuery;
import hiof.gruppe25.core.queries.retrieveevaluationsbycorporation.RetrieveEvaluationByCorporationQuery;
import hiof.gruppe25.core.queries.retrieveevaluation.RetrieveEvaluationQuery;
import hiof.gruppe25.core.queries.retrieveevaluationsources.RetrieveEvaluationSourcesQuery;
import hiof.gruppe25.core.queries.retrieveevaulationframeworks.RetrieveEvaluationFrameworksQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping(path="evaluations")
public class EvaluationController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public EvaluationController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public UUID createEvaluation(@RequestBody CreateEvaluationQuery command) throws Exception {
       return queryDispatcher.query(command);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Evaluation getEvaluation(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new RetrieveEvaluationQuery(id));
    }
    @RequestMapping(value = "/{id}/frameworks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Framework> getEvaluationFrameworks(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new RetrieveEvaluationFrameworksQuery(id));
    }
    @RequestMapping(value = "/{id}/sources", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getEvaluationSources(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new RetrieveEvaluationSourcesQuery(id));
    }
    @RequestMapping(value = "/corporation/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Evaluation> getEvaluationByCorporation(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new RetrieveEvaluationByCorporationQuery(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvaluationById(@PathVariable UUID id) throws Exception {
        commandDispatcher.execute((new DeleteEvaluationCommand(id)));
    }

}

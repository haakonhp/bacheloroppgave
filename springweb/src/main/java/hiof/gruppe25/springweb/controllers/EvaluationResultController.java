package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.models.evaluationresult.EvaluationResult;
import hiof.gruppe25.core.queries.calculateevaluationresults.CalculateEvaluationResultQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "evaluations/results")
public class EvaluationResultController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public EvaluationResultController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public EvaluationResult getEvalulation(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new CalculateEvaluationResultQuery(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, params = {"mostPressing"})
    @ResponseStatus(HttpStatus.OK)
    public EvaluationResult getEvalulationWithMostPressing(@PathVariable UUID id, @RequestParam int mostPressing) throws Exception {
        return queryDispatcher.query(new CalculateEvaluationResultQuery(id, mostPressing));
    }


}

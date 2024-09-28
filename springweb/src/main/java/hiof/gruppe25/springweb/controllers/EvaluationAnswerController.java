package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommand;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="evaluations/answers")
public class EvaluationAnswerController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public EvaluationAnswerController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateEvaluations(@RequestBody UpdateEvaluationCommand command, @PathVariable UUID id) throws Exception {
        command.setEvaluationId(id);
        commandDispatcher.execute(command);
    }

}

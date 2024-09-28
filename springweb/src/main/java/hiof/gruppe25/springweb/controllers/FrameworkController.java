package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources.RetrieveFrameworksFilteredByControlSourcesQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "frameworks")
public class FrameworkController {

    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public FrameworkController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }
    @RequestMapping(value = "", method = RequestMethod.GET,  params = {"sources"})
    @ResponseStatus(HttpStatus.OK)
    public List<Control> retrieveAllFrameworksBySources(@RequestParam List<String> sources) throws Exception {
        RetrieveFrameworksFilteredByControlSourcesQuery query = new RetrieveFrameworksFilteredByControlSourcesQuery(sources);
        return queryDispatcher.query(query);
    }
}

package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQuery;
import hiof.gruppe25.core.queries.retrievecontrolbysourceandframework.RetrieveControlBySourceAndFrameworkQuery;
import hiof.gruppe25.core.queries.retrievedistinctsources.RetrieveDistinctSourcesQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="controls")
public class ControlController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ControlController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Control> retrieveAllControls() throws Exception {
        return queryDispatcher.query(new RetrieveControlQuery());
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = {"sources", "frameworks"})
    @ResponseStatus(HttpStatus.OK)
    public List<Control> retrieveControlsBySourcesAndFrameworks(@RequestParam List<String> sources, @RequestParam List<String> frameworks) throws Exception {
        RetrieveControlBySourceAndFrameworkQuery query = new RetrieveControlBySourceAndFrameworkQuery(sources, frameworks);
        return queryDispatcher.query(query);
    }

    @RequestMapping(value = "/sources/distinct", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> retrieveAllDistinctSources() throws Exception {
        return queryDispatcher.query(new RetrieveDistinctSourcesQuery());
    }

}

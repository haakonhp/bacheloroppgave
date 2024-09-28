package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.commands.createcorporation.CreateCorporationCommand;
import hiof.gruppe25.core.commands.deletecorporation.DeleteCorporationCommand;
import hiof.gruppe25.core.commands.updatecorporation.UpdateCorporationCommand;
import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.queries.retrievecorporationbyid.RetrieveCorporationByIdQuery;
import hiof.gruppe25.core.queries.retrievecorporations.RetrieveCorporationQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="corporations")
public class CorporationController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CorporationController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCorporation(@RequestBody CreateCorporationCommand command) throws Exception {
        commandDispatcher.execute(command);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateCorporationById(@RequestBody UpdateCorporationCommand command) throws Exception {
        commandDispatcher.execute(command);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCorporationById(@PathVariable UUID id) throws Exception {
        commandDispatcher.execute((new DeleteCorporationCommand(id)));
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Corporation> retrieveAllCorporations() throws Exception {
        return queryDispatcher.query(new RetrieveCorporationQuery());
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Corporation retrieveCorporationById(@PathVariable UUID id) throws Exception {
        return queryDispatcher.query(new RetrieveCorporationByIdQuery(id));
    }
}
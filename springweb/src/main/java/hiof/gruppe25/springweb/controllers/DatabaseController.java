package hiof.gruppe25.springweb.controllers;

import hiof.gruppe25.core.commands.applybackup.ApplyBackupCommand;
import hiof.gruppe25.core.commands.applydocumentupdate.ApplyDocumentUpdateCommand;
import hiof.gruppe25.core.queries.createbackup.CreateBackupQuery;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="database")
public class DatabaseController {
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DatabaseController(CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @RequestMapping(value = "backup", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> retrieveBackup() throws Exception {
        HttpHeaders responseHeaders = new HttpHeaders();
        byte[] result = queryDispatcher.query(new CreateBackupQuery());
        responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=backup.sql");

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }
    // https://stackoverflow.com/questions/39748536/spring-upload-non-multipart-file-as-a-stream
    @RequestMapping(path="backup", method = RequestMethod.POST, consumes = "application/x-sql")
    @ResponseStatus(HttpStatus.OK)
    public void applyBackup(HttpServletRequest request) {
        try {
            byte[] resource = request.getInputStream().readAllBytes();
            commandDispatcher.execute(new ApplyBackupCommand(resource));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(path="document", method = RequestMethod.POST, consumes = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @ResponseStatus(HttpStatus.OK)
    public void applyDocument(HttpServletRequest request) {
        try {
            byte[] resource = request.getInputStream().readAllBytes();
            commandDispatcher.execute(new ApplyDocumentUpdateCommand(resource));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

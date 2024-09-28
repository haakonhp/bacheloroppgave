package hiof.gruppe25.core.commands.applydocumentupdate;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.results.ControlResult;
import hiof.gruppe25.core.models.source.Source;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.core.persistenceinterfaces.iDocumentHandler;
import hiof.gruppe25.core.persistenceinterfaces.iFrameworkRepository;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplyDocumentUpdateCommandHandler implements iCommandHandler {
    private final iDocumentHandler documentHandler;
    private final iUnitOfWork unitOfWork;
    private final iControlRepository controlRepository;
    private final iFrameworkRepository frameworkRepository;
    private final iRepository<Source> sourceRepository;

    public ApplyDocumentUpdateCommandHandler(iDocumentHandler documentHandler, iUnitOfWork unitOfWork, iControlRepository controlRepository, iFrameworkRepository frameworkRepository, iRepository<Source> sourceRepository) {
        this.documentHandler = documentHandler;
        this.unitOfWork = unitOfWork;
        this.controlRepository = controlRepository;
        this.frameworkRepository = frameworkRepository;
        this.sourceRepository = sourceRepository;
    }

    public void execute(ApplyDocumentUpdateCommand command) {
        try {
            Path tempFile = Files.createTempFile("Bacheloroppgave-", ".tmp");
            Files.write(tempFile, command.fileResource);

            ControlResult result = documentHandler.parseControlsArrayXLSX(tempFile);
            frameworkRepository.mergeAll(result.getFrameworks());
            sourceRepository.mergeAll(result.getSources());
            controlRepository.mergeAll(result.getControls());

            unitOfWork.saveChanges();
            unitOfWork.close();
            unitOfWork.resetConnections();
            Files.delete(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(iCommand command) throws Exception {
        execute((ApplyDocumentUpdateCommand) command);
    }
}

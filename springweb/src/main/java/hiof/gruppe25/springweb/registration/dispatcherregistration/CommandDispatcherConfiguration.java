package hiof.gruppe25.springweb.registration.dispatcherregistration;

import hiof.gruppe25.core.commands.applybackup.ApplyBackupCommand;
import hiof.gruppe25.core.commands.applybackup.ApplyBackupCommandHandler;
import hiof.gruppe25.core.commands.applydocumentupdate.ApplyDocumentUpdateCommand;
import hiof.gruppe25.core.commands.applydocumentupdate.ApplyDocumentUpdateCommandHandler;
import hiof.gruppe25.core.commands.createcorporation.CreateCorporationCommand;
import hiof.gruppe25.core.commands.createcorporation.CreateCorporationCommandHandler;
import hiof.gruppe25.core.commands.createreport.CreateReportCommand;
import hiof.gruppe25.core.commands.createreport.CreateReportCommandHandler;
import hiof.gruppe25.core.commands.deleteanalysis.DeleteEvaluationCommand;
import hiof.gruppe25.core.commands.deleteanalysis.DeleteEvaluationCommandHandler;
import hiof.gruppe25.core.commands.deletecorporation.DeleteCorporationCommand;
import hiof.gruppe25.core.commands.deletecorporation.DeleteCorporationCommandHandler;
import hiof.gruppe25.core.commands.updatecorporation.UpdateCorporationCommand;
import hiof.gruppe25.core.commands.updatecorporation.UpdateCorporationCommandHandler;
import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommand;
import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommandHandler;
import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;

public class CommandDispatcherConfiguration {
    public static void configureCommandDispatcher() {
        CommandDispatcher.register(CreateCorporationCommand.class, CreateCorporationCommandHandler.class);
        CommandDispatcher.register(CreateReportCommand.class, CreateReportCommandHandler.class);
        CommandDispatcher.register(UpdateEvaluationCommand.class, UpdateEvaluationCommandHandler.class);
        CommandDispatcher.register(UpdateCorporationCommand.class, UpdateCorporationCommandHandler.class);
        CommandDispatcher.register(DeleteCorporationCommand.class, DeleteCorporationCommandHandler.class);
        CommandDispatcher.register(DeleteEvaluationCommand.class, DeleteEvaluationCommandHandler.class);

        CommandDispatcher.register(ApplyBackupCommand.class, ApplyBackupCommandHandler.class);
        CommandDispatcher.register(ApplyDocumentUpdateCommand.class, ApplyDocumentUpdateCommandHandler.class);
    }
}

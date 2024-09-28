package hiof.gruppe25.core.commands.applybackup;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.persistenceinterfaces.iBackupManager;

public class ApplyBackupCommandHandler implements iCommandHandler {
    private final iBackupManager backupManager;

    public ApplyBackupCommandHandler(iBackupManager backupManager) {
        this.backupManager = backupManager;
    }

    public void execute(ApplyBackupCommand command) {
        backupManager.applyBackup(command.fileResource);
    }

    @Override
    public void execute(iCommand command) {
        execute((ApplyBackupCommand) command);
    }
}

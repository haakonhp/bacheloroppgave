package hiof.gruppe25.core.queries.createbackup;

import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.persistenceinterfaces.iBackupManager;
import hiof.gruppe25.core.persistenceinterfaces.iControlRepository;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class CreateBackupQueryHandler implements iQueryHandler<byte[], CreateBackupQuery>  {
    private final iBackupManager backupManager;

    public CreateBackupQueryHandler(iBackupManager backupManager) {
        this.backupManager = backupManager;
    }
    public byte[] execute(CreateBackupQuery command) {
        return backupManager.createBackup();
    }
}

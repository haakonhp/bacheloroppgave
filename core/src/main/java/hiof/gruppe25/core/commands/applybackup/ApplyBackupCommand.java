package hiof.gruppe25.core.commands.applybackup;

import hiof.gruppe25.core.commands.definitions.iCommand;

public class ApplyBackupCommand implements iCommand {
    byte[] fileResource;

    public ApplyBackupCommand(byte[] fileResource) {
        this.fileResource = fileResource;
    }
}

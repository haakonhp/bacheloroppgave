package hiof.gruppe25.core.commands.applydocumentupdate;

import hiof.gruppe25.core.commands.definitions.iCommand;

public class ApplyDocumentUpdateCommand implements iCommand {
    byte[] fileResource;
    public ApplyDocumentUpdateCommand(byte[] fileResource) {
        this.fileResource = fileResource;
    }
}

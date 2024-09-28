package hiof.gruppe25.core.commands.createreport;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;

public class CreateReportCommandHandler implements iCommandHandler {
    @Override
    public void execute(iCommand command) throws Exception {
        System.out.println(command);
    }
}

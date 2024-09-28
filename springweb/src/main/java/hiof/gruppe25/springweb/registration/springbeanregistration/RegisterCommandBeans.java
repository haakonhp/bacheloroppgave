package hiof.gruppe25.springweb.registration.springbeanregistration;

import hiof.gruppe25.core.commands.applybackup.ApplyBackupCommandHandler;
import hiof.gruppe25.core.commands.applydocumentupdate.ApplyDocumentUpdateCommandHandler;
import hiof.gruppe25.core.commands.createcorporation.CreateCorporationCommandHandler;
import hiof.gruppe25.core.commands.createreport.CreateReportCommandHandler;
import hiof.gruppe25.core.commands.deleteanalysis.DeleteEvaluationCommandHandler;
import hiof.gruppe25.core.commands.deletecorporation.DeleteCorporationCommandHandler;
import hiof.gruppe25.core.commands.updatecorporation.UpdateCorporationCommandHandler;
import hiof.gruppe25.core.commands.updateevaluations.UpdateEvaluationCommandHandler;
import org.springframework.context.support.GenericApplicationContext;

public class RegisterCommandBeans {
    public static void registerCommands(GenericApplicationContext ctx) {
        ctx.registerBean(UpdateCorporationCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(CreateReportCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(CreateCorporationCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(DeleteCorporationCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(DeleteEvaluationCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(UpdateEvaluationCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(ApplyDocumentUpdateCommandHandler.class, Scopes::prototypeScoped);
        ctx.registerBean(ApplyBackupCommandHandler.class, Scopes::prototypeScoped);
    }
}

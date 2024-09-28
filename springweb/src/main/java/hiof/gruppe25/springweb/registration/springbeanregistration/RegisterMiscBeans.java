package hiof.gruppe25.springweb.registration.springbeanregistration;

import hiof.gruppe25.persistence.utilities.MySQLBackupManager;
import org.springframework.context.support.GenericApplicationContext;
import parsers.DocumentHybridParser;
import parsers.XLSXToCSVParser;

public class RegisterMiscBeans {
    public static void registerMisc(GenericApplicationContext ctx) {
        ctx.registerBean(MySQLBackupManager.class, Scopes::prototypeScoped);
        ctx.registerBean(DocumentHybridParser.class, Scopes::prototypeScoped);
        ctx.registerBean(XLSXToCSVParser.class, Scopes::prototypeScoped);
    }
}

package hiof.gruppe25.springweb.registration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;

import hiof.gruppe25.springweb.dispatchers.CommandDispatcher;
import hiof.gruppe25.springweb.dispatchers.QueryDispatcher;
import hiof.gruppe25.springweb.registration.springbeanregistration.RegisterCommandBeans;
import hiof.gruppe25.springweb.registration.springbeanregistration.RegisterMiscBeans;
import hiof.gruppe25.springweb.registration.springbeanregistration.RegisterQueryBeans;
import hiof.gruppe25.springweb.registration.springbeanregistration.RegisterRepositoryBeans;
import org.springframework.context.support.GenericApplicationContext;

public class SpringBeanRegistration {
    public static void configure(GenericApplicationContext ctx) {
        ctx.registerBean(CommandDispatcher.class, () -> new CommandDispatcher(type -> (iCommandHandler) ctx.getBean(type)));
        ctx.registerBean(QueryDispatcher.class, () -> new QueryDispatcher(type -> (iQueryHandler<?,?>) ctx.getBean(type)));
        ctx.registerBean(Hibernate5Module.class, () -> {
            Hibernate5Module module = new Hibernate5Module();
            module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
            return module;
        });

        RegisterRepositoryBeans.registerRepositories(ctx);
        RegisterCommandBeans.registerCommands(ctx);
        RegisterQueryBeans.registerQueries(ctx);
        RegisterMiscBeans.registerMisc(ctx);
    }
}

package hiof.gruppe25.springweb.registration.springbeanregistration;

import hiof.gruppe25.persistence.repositories.*;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import hiof.gruppe25.persistence.utilities.MySQLBackupManager;
import org.springframework.context.support.GenericApplicationContext;

public class RegisterRepositoryBeans {
    public static void registerRepositories(GenericApplicationContext ctx) {
        ctx.registerBean(JPAUnitOfWork.class, Scopes::requestScoped);
        ctx.registerBean(ControlRepository.class, Scopes::prototypeScoped);
        ctx.registerBean(CorporationRepository.class, Scopes::prototypeScoped);

        ctx.registerBean(FrameworkRepository.class, Scopes::prototypeScoped);
        ctx.registerBean(SourceRepository.class, Scopes::prototypeScoped);
        ctx.registerBean(EvaluationRepository.class, Scopes::prototypeScoped);
        ctx.registerBean(EvaluationAnswerRepository.class, Scopes::prototypeScoped);

        ctx.registerBean(MySQLBackupManager.class, Scopes::prototypeScoped);
    }
}

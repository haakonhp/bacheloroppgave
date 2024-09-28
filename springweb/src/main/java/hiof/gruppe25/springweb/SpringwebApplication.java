package hiof.gruppe25.springweb;

import hiof.gruppe25.springweb.registration.SpringBeanRegistration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import static hiof.gruppe25.springweb.registration.dispatcherregistration.CommandDispatcherConfiguration.configureCommandDispatcher;
import static hiof.gruppe25.springweb.registration.dispatcherregistration.QueryDispatcherConfiguration.configureQueryDispatcher;


@SpringBootApplication()
public class SpringwebApplication {
    public static void main(String[] args) {
        configureCommandDispatcher();
        configureQueryDispatcher();
        runSpring(args);

    }
    public static void runSpring(String[] args) {
        new SpringApplicationBuilder()
                .initializers((ApplicationContextInitializer<GenericApplicationContext>) SpringBeanRegistration::configure)
                .sources(SpringwebApplication.class)
                .run(args);
    }
}




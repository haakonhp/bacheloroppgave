package hiof.gruppe25.springweb.bootup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WarmupHibernateConnection implements CommandLineRunner {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtx;

    // Hibernate requires a large operation on the first call of an arbitrary DB call.
    // Doing this before any user has to interact with it is preferable.
    // RestTemplate is used to provide an HTTP request scope, as unitOfWork is only assigned to this scope.
    @Override
    public void run(String... args) {
        try {
            new RestTemplate().getForEntity(String.format("http://localhost:%d/corporations", webServerAppCtx.getWebServer().getPort()), List.class);
            System.out.println("Completed warmup call for Hibernate connections.");

        } catch (RestClientException e) {
            System.out.println("Error during warmup-call.");
        }
    }
}

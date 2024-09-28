package hiof.gruppe25.springweb.registration.springbeanregistration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.WebApplicationContext;

public class Scopes {
    protected static void prototypeScoped(BeanDefinition beanDefinition) {
        beanDefinition.setScope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE);
    }
    protected static void webSocketScoped(BeanDefinition beanDefinition) {
        beanDefinition.setScope(WebApplicationContext.SCOPE_SESSION);
    }
    protected static void requestScoped(BeanDefinition beanDefinition) {
        beanDefinition.setScope("request");
    }

}

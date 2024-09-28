package hiof.gruppe25.persistence.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Objects;

public class JPAUnitOfWork implements hiof.gruppe25.core.utilities.iUnitOfWork {
    private final Session session;
    private static SessionFactory sessionFactory;
    private final EntityManager entityManager;

    public JPAUnitOfWork() {
        this.session = getSessionFactory().openSession();

        EntityManagerFactory entityManagerFactory = getSession().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        getEntityManager().getTransaction().begin();
    }

    @Override
    public void saveChanges() {
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    public void resetConnections() {
        sessionFactory.close();
    }

    public Session getSession() {
        return session;
    }

    static SessionFactory getSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            return sessionFactory;
        }
        if(System.getenv("SPRING_DATASOURCE_URL") != null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .applySetting("hibernate.connection.url", Objects.requireNonNullElse(System.getenv("SPRING_DATASOURCE_URL"),""))
                    .applySetting("hibernate.connection.username", Objects.requireNonNullElse(System.getenv("SPRING_DATASOURCE_USERNAME"),"spring_user"))
                    .applySetting("hibernate.connection.password", Objects.requireNonNullElse(System.getenv("SPRING_DATASOURCE_PASSWORD"),"IPS@ie6m3!Iqs2"))
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            return sessionFactory;
        }
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }
}


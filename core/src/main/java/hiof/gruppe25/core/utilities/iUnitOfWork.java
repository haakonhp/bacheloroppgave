package hiof.gruppe25.core.utilities;

public interface iUnitOfWork {
    void saveChanges();
    void close();
    void resetConnections();
}

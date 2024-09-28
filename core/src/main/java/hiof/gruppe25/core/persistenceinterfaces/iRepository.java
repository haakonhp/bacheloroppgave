package hiof.gruppe25.core.persistenceinterfaces;

import java.util.List;
import java.util.UUID;

public interface iRepository<T> {
    T getById(int id);
    T getById(UUID id);
    void add(T element);
    void delete(int id);
    void delete(UUID UUID);
    public void merge(T element);

    public void mergeAll(List<T> typeList);

    List<T> getAll();

}

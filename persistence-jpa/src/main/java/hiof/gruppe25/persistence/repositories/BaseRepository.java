package hiof.gruppe25.persistence.repositories;

import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.UUID;

public class BaseRepository<T> implements iRepository<T> {
    protected final JPAUnitOfWork unitOfWork;
    private final Class<T> elementType;

    private final int BATCH_SIZE = 50;

    public BaseRepository(JPAUnitOfWork unitOfWork, Class<T> elementType) {
        this.unitOfWork = unitOfWork;
        this.elementType = elementType;
    }

    public T getById(int id) {
        return unitOfWork.getEntityManager().find(elementType, id);
    }

    public T getById(UUID id) {
        return unitOfWork.getEntityManager().find(elementType, id);
    }

    public void add(T element) {
        unitOfWork.getEntityManager().persist(element);
    }

    public void addAll(List<T> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            add(typeList.get(i));
            if (i % BATCH_SIZE == 0) {
                unitOfWork.getEntityManager().flush();
                unitOfWork.getEntityManager().clear();
            }
        }
    }

    public void merge(T element) {
        unitOfWork.getEntityManager().merge(element);
    }

    public void mergeAll(List<T> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            merge(typeList.get(i));
            if (i % BATCH_SIZE == 0) {
                unitOfWork.getEntityManager().flush();
                unitOfWork.getEntityManager().clear();
            }
        }
    }

    @Override
    public void delete(int id) {
        T toBeDeleted = unitOfWork.getEntityManager().find(elementType, id);
        unitOfWork.getEntityManager().remove(toBeDeleted);
    }

    @Override
    public void delete(UUID id) {
        T toBeDeleted = unitOfWork.getEntityManager().find(elementType, id);
        unitOfWork.getEntityManager().remove(toBeDeleted);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = unitOfWork.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> controlQuery = criteriaBuilder.createQuery(elementType);
        Root<T> root = controlQuery.from(elementType);

        CriteriaQuery<T> allElements = controlQuery.select(root);

        return unitOfWork.getEntityManager().createQuery(allElements).getResultList();
    }
}

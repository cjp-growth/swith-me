package project.swithme.order.common.persistence;

public interface PersistenceHelper<T> {

    T persist(T entity);

}

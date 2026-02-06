package repository.interfaces;

import java.util.List;

public interface CrudRepository<T, ID> {
    void create(T entity);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void delete(ID id);
}
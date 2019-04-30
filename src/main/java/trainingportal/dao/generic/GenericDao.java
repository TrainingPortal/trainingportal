package trainingportal.dao.generic;

import java.util.List;

public interface GenericDao<T> {
    T findById(Long id);
    void save(T entity);
    void update(T entity);
    void deleteById(Long id);
    List<T> findAll();
}


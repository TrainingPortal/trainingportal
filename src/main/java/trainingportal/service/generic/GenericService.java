package trainingportal.service.generic;

import java.util.List;

public interface GenericService<T> {
    T findById(Long id);
    void save(T entity);
    void update(T entity);
    void deleteById(Long id);
    List<T> findAll();
}

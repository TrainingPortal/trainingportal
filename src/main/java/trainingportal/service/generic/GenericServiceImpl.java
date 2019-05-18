package trainingportal.service.generic;

import org.springframework.beans.factory.annotation.Autowired;
import trainingportal.dao.generic.GenericDao;

import java.util.List;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

    @Autowired
    private GenericDao<T> genericDao;

    @Override
    public T findById(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public void save(T entity) {
        genericDao.save(entity);
    }

    @Override
    public void update(T entity) {
        genericDao.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        genericDao.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return genericDao.findAll();
    }

    @Override
    public List<T> getAllAsPage(int page, int total) {
        if(page != 1){
            page = (page - 1) * total + 1;
        }

        return genericDao.getAllAsPage(page, total);
    }

    protected int getPageNumber(int page, int total){
        if(page != 1){
            page = (page - 1) * total + 1;
        }

        return page;

    }

}

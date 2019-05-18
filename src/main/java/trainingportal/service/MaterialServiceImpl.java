package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.MaterialDao;
import trainingportal.model.Material;

import java.util.List;


@Service("materialService")
@Transactional
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

    @Override
    public int getNumberOfPages(List<Material> users, double total) {
        return 0;
    }

    @Override
    public Material findById(Long MaterialId) {
        return materialDao.findById(MaterialId);
    }

    @Override
    public void save(Material material) {
        materialDao.save(material);
    }

    @Override
    public void update(Material material) {
        Material materialEdit = materialDao.findById(material.getMaterialId());
        if (materialEdit != null) {
            materialEdit.setLessonId(material.getLessonId());
            materialEdit.setMaterialDescription(material.getMaterialDescription());

        }
        materialDao.update(materialEdit);
    }


    @Override
    public void deleteById(Long materialId) {
        materialDao.deleteById(materialId);
    }

    @Override
    public List<Material> findAll() {
        return materialDao.findAll();
    }

    @Override
    public List<Material> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public List<Material> getMaterialLessonId(Long lessonId) {
        return materialDao.getMaterialLessonId(lessonId);
    }

    @Override
    public List<Material> getAllAsPageByLessonId(Long lessonId, int page, int total) {

        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }
        return materialDao.getAllAsPageByLessonId(lessonId, page, total);
    }

    @Override
    public int getPages(Long lessonId, double total) {
        return (int) Math.ceil(materialDao.countAllByLessonId(lessonId) / total);
    }

}


package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.MaterialDaoImpl;
import trainingportal.model.Material;

import java.util.List;


@Service("materialService")
@Transactional
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDaoImpl materialDao;

    @Override
    public List<Material> findAll() {
        return materialDao.findAll();
    }

    @Override
    public List<Material> getAllAsPage(int page, int total) {
        return null;
    }

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
            materialEdit.setMaterialId(material.getMaterialId());
            materialEdit.setMaterialDescription(material.getMaterialDescription());
            materialEdit.setLessonId(material.getLessonId());

        }
        materialDao.update(materialEdit);
    }


    @Override
    public void deleteById(Long materialId) {
        materialDao.deleteById(materialId);
    }
}


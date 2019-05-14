package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.MaterialDaoImpl;
import trainingportal.model.Material;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;


@Service("materialService")
@Transactional
public class MaterialServiceImpl extends GenericServiceImpl<Material> implements MaterialService {

    @Autowired
    private MaterialDaoImpl materialDao;

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
    public List<Material> getMaterialLessonId(Long lessonId) {
        return materialDao.getMaterialLessonId(lessonId);
    }

}


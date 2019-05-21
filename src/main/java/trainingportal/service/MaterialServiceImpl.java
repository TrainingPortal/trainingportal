package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.MaterialDao;
import trainingportal.model.Material;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;


@Service("materialService")
@Transactional
public class MaterialServiceImpl extends GenericServiceImpl<Material> implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

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

    @Override
    public List<Material> getAllAsPageByLessonId(Long lessonId, int page, int total) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page, total);

        return materialDao.getAllAsPageByLessonId(lessonId, page, total);
    }

    @Override
    public int getPages(Long lessonId, double total) {
        return (int) Math.ceil(materialDao.countAllByLessonId(lessonId) / total);
    }

}


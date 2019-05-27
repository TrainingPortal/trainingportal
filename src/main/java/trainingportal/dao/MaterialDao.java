package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Material;

import java.util.List;

public interface MaterialDao extends GenericDao<Material> {
    List<Material> getMaterialLessonId(Long lessonId);

    List<Material> getAllAsPageByLessonId(Long lessonId, int page, int rowsPerPage);

    int countAllByLessonId(Long lessonId);
}

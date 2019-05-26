package trainingportal.service;

import trainingportal.model.Material;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface MaterialService extends GenericService<Material> {
    List<Material> getMaterialLessonId(Long lessonId);

    List<Material> getAllAsPageByLessonId(Long lessonId, int page, int rowsPerPage);

    int getPages(Long lessonId, double rowsPerPage);
}

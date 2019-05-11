package trainingportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainingportal.model.FilesModel;

@Repository
public interface DBFilesRepository extends JpaRepository<FilesModel, String> {
}

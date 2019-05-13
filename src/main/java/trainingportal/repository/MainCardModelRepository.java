package trainingportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainingportal.model.MainCardModel;

@Repository
public interface MainCardModelRepository  extends JpaRepository<MainCardModel, Long> {
}

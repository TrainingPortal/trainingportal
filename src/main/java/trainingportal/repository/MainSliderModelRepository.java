package trainingportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainingportal.model.MainSliderModel;

@Repository
public interface MainSliderModelRepository extends JpaRepository<MainSliderModel, Long> {
}

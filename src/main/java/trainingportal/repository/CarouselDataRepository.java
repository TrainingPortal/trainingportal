package trainingportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainingportal.model.CarouselData;

public interface CarouselDataRepository extends JpaRepository<CarouselData, Integer> {
}

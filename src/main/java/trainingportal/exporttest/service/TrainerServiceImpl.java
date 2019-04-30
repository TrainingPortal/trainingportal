package trainingportal.exporttest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.exporttest.dao.TrainerDaoImpl;
import trainingportal.exporttest.model.Trainer;

import java.util.List;

@Service("trainerService")
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    TrainerDaoImpl trainerRepository;

    @Override
    public List<Trainer> getAllTrainerCourse() {
        return trainerRepository.findAllTrainerCourse();
    }
}

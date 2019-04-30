package trainingportal.exceltest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.exceltest.dao.TrainerDaoImpl;
import trainingportal.exceltest.model.Trainer;

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

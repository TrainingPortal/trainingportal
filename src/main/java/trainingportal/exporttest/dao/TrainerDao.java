package trainingportal.exporttest.dao;

import trainingportal.exceltest.model.Trainer;
import trainingportal.exporttest.model.Trainer;

import java.util.List;

public interface TrainerDao {

    List<Trainer> findAllTrainerCourse();

}

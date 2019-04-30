package trainingportal.exporttest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.exporttest.dao.TrainerDaoImpl;
import trainingportal.exporttest.model.Trainer;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class TrainerController2 {

    @Autowired
    TrainerDaoImpl trainerDao;

    @RequestMapping(value = "trainer/trainerCourses", method = RequestMethod.GET)
    public ModelAndView showAllTrainerCourses(@NotNull ModelAndView model){

        List<Trainer> courses = trainerDao.findAllTrainerCourse();

        model.addObject("trainerAllCourses",courses);
        model.setViewName("trainer/trainerCourses");

        return model;
    }

}

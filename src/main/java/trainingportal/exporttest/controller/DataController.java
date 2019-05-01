package trainingportal.exporttest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.exporttest.dao.DataDaoImpl;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    DataDaoImpl dataDao;

    @RequestMapping(value = "trainer/trainerCourses", method = RequestMethod.GET)
    public ModelAndView showAllTrainerCourses(@NotNull ModelAndView model){

        List courses = dataDao.findFieldsFromTable(new ArrayList<>(), "s");

        model.addObject("trainerAllCourses",courses);
        model.setViewName("trainer/trainerCourses");

        return model;
    }

}

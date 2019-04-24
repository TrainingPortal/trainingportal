package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.service.CourseServiceImpl;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;
    //used fo old realisation
//    @Autowired
//    CourseDAOImpl courseDAO;

    @RequestMapping(value = "/course_create")
    public ModelAndView showCoursesList(Long courseId, ModelAndView modelAndView) {
        List<Course> courseList = courseService.CoursesList();
//        List<Course> courseList = courseService.getAllCoursesById(courseId);
//        Course courseList = courseService.findCourseById(courseId);
        modelAndView.addObject("courseList", courseList);
        modelAndView.setViewName("courseCreator/course_create");
        return modelAndView;
    }


}

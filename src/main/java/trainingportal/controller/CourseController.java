package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainingportal.dao.CourseDAOImpl;
import trainingportal.model.Course;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseDAOImpl courseDAO;

    @RequestMapping(value = "/course_create", method = RequestMethod.GET)
    public String showAllCourses(Model model) {
        List<Course> list = courseDAO.getCoursAll();
        model.addAttribute("courseAll", list);
        return "admin/course_create";
    }


}

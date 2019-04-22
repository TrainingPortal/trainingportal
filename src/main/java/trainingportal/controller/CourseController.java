package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        return "CourseCreator/course_create";
    }


    @RequestMapping(value = "/edit-course", method = RequestMethod.GET)
    public ModelAndView updateCourseById(Course course, ModelAndView model, RedirectAttributes redirect) {
        Course courseUpdate = courseDAO.findCourseById(course.getId());
        if (courseUpdate != null) {
            courseUpdate.setId(course.getId());
            courseUpdate.setName(course.getName());
            courseUpdate.setCourse_level(course.getCourse_level());
            courseUpdate.setStatus(course.getStatus());
            courseUpdate.setDate_start(course.getDate_start());
            courseUpdate.setDate_end(course.getDate_end());
            courseUpdate.setGroup_number(course.getGroup_number());
            courseUpdate.setMin_number(course.getMin_number());
            courseUpdate.setDescription(course.getDescription());
            courseUpdate.setTrainer(course.getTrainer());
        }
        courseDAO.updateCourse(courseUpdate);
        redirect.addFlashAttribute("successMessage", "Course edited");
        model.setViewName("redirect:/course_create");
        return model;
    }

    @RequestMapping(value = "/course-delete-by-id", method = RequestMethod.GET)
    public ModelAndView deleteCourseById(Long Id, ModelAndView model, RedirectAttributes redirect) {
        courseDAO.deleteCourseById(Id);
        redirect.addFlashAttribute("successMessage", "Course deleted successfully");
        model.setViewName("redirect:/course_create");
        return model;
    }
}

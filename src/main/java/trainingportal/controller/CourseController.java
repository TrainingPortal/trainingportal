package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView showAllCourses(ModelAndView model, Long Id) {
//    public String showAllCourses(Model model) {
        List<Course> course = courseDAO.getCoursAll();
//        model.addAttribute("courseAll", list);
        model.addObject("courseAll", course);
        model.setViewName("CourseCreator/course_create");
        return model;
    }

    @RequestMapping("/course-add")
    public ModelAndView addCourse(ModelAndView model) {

        model.addObject("courseAdd", new Course());
        model.setViewName("CourseCreator/course_add");

        return model;
    }

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST)
    public ModelAndView saveCourse(Course course, ModelAndView model, RedirectAttributes redir) {

        courseDAO.save(course, course.getId());
        model.setViewName("CourseCreator/course_add");

        return model;
    }

    @RequestMapping(value = {"/edit-course-{id}"}, method = RequestMethod.GET)
    public ModelAndView editCourseById(@PathVariable("id") Long id, Course course, BindingResult bindingResult, ModelAndView model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.setViewName("redirect:/course_create");
            return model;
        } else {
            Course courseEdit = courseDAO.findCourseById(course.getId());
            if (courseEdit != null) {
                courseEdit.setName(course.getName());
                courseEdit.setCourse_level(course.getCourse_level());
                courseEdit.setStatus(course.getStatus());
                courseEdit.setDate_start(course.getDate_start());
                courseEdit.setDate_end(course.getDate_end());
                courseEdit.setGroup_number(course.getGroup_number());
                courseEdit.setMin_number(course.getMin_number());
                courseEdit.setDescription(course.getDescription());
                courseEdit.setTrainer(course.getTrainer());
                courseEdit.setId(course.getId());
            }
            courseDAO.editCourseById(courseEdit);
            model.addObject("course", course);
            model.addObject("edit", true);
            model.setViewName("CourseCreator/edit_course_by_id");
            return model;
        }
    }

    @RequestMapping(value = "/course-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteCourseById(@PathVariable("id") Long Id, ModelAndView model, RedirectAttributes redirect) {
        Course course = courseDAO.findCourseById(Id);
        courseDAO.deleteCourseById(Id);
        redirect.addFlashAttribute("successMessage", "Course with" + " " + course.getId() +
                " " + "deleted successfully");
        model.setViewName("redirect:/course_create");
        return model;
    }
}

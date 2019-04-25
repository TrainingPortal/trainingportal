package trainingportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Course;
import trainingportal.service.CourseServiceImpl;

import java.util.List;

@Controller
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }
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

    @RequestMapping("/course-add")
    public ModelAndView addCourse(ModelAndView model) {

        model.addObject("course", new Course());
        model.setViewName("courseCreator/course_add");

        return model;
    }

    @RequestMapping(value = "courseSave", method = RequestMethod.POST)
    public ModelAndView saveCourse(Course course, ModelAndView model) {

        courseService.saveCourse(course);
//        courseDAO.save(course);
        model.setViewName("courseCreator/course_add");

        return model;
    }

    @RequestMapping(value = {"/edit-course-{id}"}, method = RequestMethod.GET)
    public ModelAndView editCourseBase(@PathVariable("id") Long courseId, ModelAndView modelAndView) {
        Course course = courseService.findCourseById(courseId);
        modelAndView.addObject("course", course);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("courseCreator/edit_course_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-course-{id}"}, method = RequestMethod.POST)
    public ModelAndView editCourseById(Course courseEdit, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("courseCreator/edit_course_by_id");
            return modelAndView;
        } else {
            courseService.editCourse(courseEdit);
            modelAndView.addObject("courseEdit", courseEdit);
            modelAndView.setViewName("redirect:/course_create");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/course-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteCourseById(@PathVariable("id") Long courseId, ModelAndView model, RedirectAttributes redirect) {
        courseService.deleteCourseById(courseId);
        redirect.addFlashAttribute("successMessage", "course deleted successfully");
        model.setViewName("redirect:/course_create");
        return model;
    }


}

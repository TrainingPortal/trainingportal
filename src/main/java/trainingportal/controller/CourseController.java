package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CourseServiceImpl courseService;

    @RequestMapping(value = "/course_create")
    public ModelAndView showCoursesList(Long courseId, ModelAndView modelAndView) {
        List<Course> courseList = courseService.findAll();
//        List<Course> courseList = courseService.getAllCoursesById(courseId);
//        Course courseList = courseService.findCourseById(courseId);
        modelAndView.addObject("courseList", courseList);
        modelAndView.setViewName("courseCreator/course_create");
        return modelAndView;
    }

    @RequestMapping(value = "/course-add", method = RequestMethod.GET)
    public ModelAndView addCourse(ModelAndView modelAndView) {

        modelAndView.addObject("course", new Course());
        modelAndView.setViewName("courseCreator/course_add");

        return modelAndView;
    }

    @RequestMapping(value = "course-save", method = RequestMethod.POST)
    public ModelAndView saveCourse(Course course, ModelAndView modelAndView) {
        courseService.save(course);
        modelAndView.setViewName("redirect:/course_create");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-course-{id}"}, method = RequestMethod.GET)
    public ModelAndView editCourseBase(@PathVariable("id") Long courseId, ModelAndView modelAndView) {
        Course course = courseService.findById(courseId);
        modelAndView.addObject("course", course);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("courseCreator/edit_course_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-course-{id}"}, method = RequestMethod.POST)
    public ModelAndView editCourseById(Course course, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("courseCreator/edit_course_by_id");
            return modelAndView;
        } else {
            courseService.update(course);
            modelAndView.setViewName("redirect:/course_create");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/course-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteCourseById(@PathVariable("id") Long courseId, ModelAndView model, RedirectAttributes redirect) {
        courseService.deleteById(courseId);

        redirect.addFlashAttribute("successMessage", "course deleted successfully");

        model.setViewName("redirect:/course_create");
        return model;
    }
}

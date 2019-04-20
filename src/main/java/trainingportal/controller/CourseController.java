package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.service.CourseServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping(value = {"/", "list"}, method = RequestMethod.GET)
    public ModelAndView getAllCourse() {
        ModelAndView modelAndView = new ModelAndView();
        List<Course> list = courseService.getAllCourse();

        modelAndView.addObject("course_list", list);
        modelAndView.setViewName("course_list");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{Id}", method = RequestMethod.GET)
    public ModelAndView editCourse(@PathVariable Integer Id) {
        ModelAndView modelAndView = new ModelAndView();

        Course course = courseService.findCourseById(Id);
        modelAndView.addObject("courseForm", course);

        modelAndView.setViewName("course_form");
        return modelAndView;
    }

    @RequestMapping(value = "/add}", method = RequestMethod.GET)
    public ModelAndView addCourse() {
        ModelAndView modelAndView = new ModelAndView();

        Course course = new Course();
        modelAndView.addObject("courseForm", course);

        modelAndView.setViewName("course_form");
        return modelAndView;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    private ModelAndView saveOrUpdate(@ModelAttribute("courseForm") Course course) {
        if (course.getId() != null) {
            courseService.updateCourse(course);
        } else {
            courseService.addCourse(course);
        }
        return new ModelAndView("redirect:/course/list");
    }

    @RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public ModelAndView deleteCourse(@PathVariable("Id") int courseId) {
        courseService.deleteCourse(courseId);

        return new ModelAndView("redirect:/course/list");
    }


}

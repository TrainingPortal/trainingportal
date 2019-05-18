package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.model.User;
import trainingportal.service.CourseService;
import trainingportal.service.ProfileService;
import trainingportal.service.UserService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    ProfileService profileService;

    @RequestMapping("/profile_page/{userId}")
    ModelAndView profilePage(@PathVariable("userId") Long id, ModelAndView model){

        User user = userService.findById(id);
        User manager = profileService.initManager(user);
        List<Course> courseList = courseService.findCoursesByUserId(id);

        for(Course course : courseList){
            course.setTrainer(userService.findById(course.getTrainerId()));
        }
        String role = profileService.getReadableRole(user);

        model.addObject("manager", manager);
        model.addObject("courseList", courseList);
        model.addObject("role", role);
        model.addObject("user", user);
        model.setViewName("frontend/profile_page");

        return model;
    }
}

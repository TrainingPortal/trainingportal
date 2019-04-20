package trainingportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String welcomePage(Model model) {

        return "frontend/index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
 
        return "user/login";
    }

    @GetMapping("/profilepage")
    String profilePage(Model model){

        return "frontend/profilepage";
    }

    @GetMapping("/coursepage")
    String coursePage(Model model){

        return "frontend/coursepage";
    }

    @GetMapping("/instructorpage")
    String instructorPage(Model model){

        return "frontend/instructorpage";
    }

    @GetMapping("/helppage")
    String helpPage(Model model){

        return "frontend/helppage";
    }

    @GetMapping("/course_create")
    String courseCreate(Model model) {

        return "admin/course_create";
    }
}
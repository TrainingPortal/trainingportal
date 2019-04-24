package trainingportal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import trainingportal.utils.WebUtils;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String welcomePage(Model model) {

        return "frontend/index";
    }

    @GetMapping({"/admin"})
    public String admin(Model model) {

        return "frontend/admin";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        return "user/login";
    }

    @GetMapping("/profilepage")
    String profilePage(Model model, Principal principal){

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);

        System.out.println(userInfo);

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
    
    @GetMapping("/tempMenu")
    String tempMenu(Model model){

        return "frontend/tempMenu";
    }
    
    @GetMapping("/tempCreateGroupPage")
    String tempCreateGroupPage(Model model){

        return "frontend/tempCreateGroupPage";
    }
    
    @GetMapping("/tempGroupsPage")
    String tempGroupsPage(Model model){

        return "frontend/tempGroupsPage";
    }
}
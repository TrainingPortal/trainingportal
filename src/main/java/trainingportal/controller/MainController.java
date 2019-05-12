package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.MainSliderModel;
import trainingportal.repository.MainSliderModelRepository;
import trainingportal.service.MainSliderService;
import trainingportal.utils.WebUtils;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    MainSliderService mainSliderService;

    @GetMapping({"/", "/index"})
    public ModelAndView welcomePage(ModelAndView modelAndView) {

        List<MainSliderModel> sliderList = mainSliderService.getAll();

        modelAndView.addObject("sliderList", sliderList);
        modelAndView.setViewName("frontend/index");
        return modelAndView;
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

}

package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.MainCardModel;
import trainingportal.model.MainSliderModel;
import trainingportal.service.MainCardService;
import trainingportal.service.MainSliderService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainSliderService mainSliderService;

    @Autowired
    private MainCardService mainCardService;

    @GetMapping({"/", "/index"})
    public ModelAndView welcomePage(ModelAndView modelAndView) {

        List<MainSliderModel> sliderList = mainSliderService.getAll();
        ArrayList<Integer> indicatorList = mainSliderService.getSlideIndicators();
        List<MainCardModel> cardList = mainCardService.getAll();


        modelAndView.addObject("sliderList", sliderList);
        modelAndView.addObject("indicatorList", indicatorList);
        modelAndView.addObject("cardList", cardList);
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

    @GetMapping("/website_settings")
    public ModelAndView settingsPage(ModelAndView modelAndView){

        modelAndView.setViewName("/manageSite/website_settings");
        return modelAndView;
    }

}

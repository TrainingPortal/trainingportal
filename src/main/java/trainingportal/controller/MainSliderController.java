package trainingportal.controller;


import trainingportal.service.MainSliderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@RestController
public class MainSliderController {

    private static final Logger logger = LoggerFactory.getLogger(MainSliderController.class);

    @Autowired
    private MainSliderService mainSliderService;


    @GetMapping("/manage_main_slider")
    public ModelAndView manageMainSlider(ModelAndView modelAndView){

        modelAndView.setViewName("manageSite/manage_main_slider");
        return modelAndView;
    }

    @PostMapping("/file-save")
    public ModelAndView uploadData(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String url, ModelAndView modelAndView) throws IOException {
        mainSliderService.storeData(file, name, url);


        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }


}
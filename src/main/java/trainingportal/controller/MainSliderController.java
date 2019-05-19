package trainingportal.controller;


import trainingportal.model.MainSliderModel;
import trainingportal.service.MainSliderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@RestController
public class MainSliderController {
    @Autowired
    private MainSliderService mainSliderService;

    @GetMapping("/manage_main_slider")
    public ModelAndView manageMainSlider(ModelAndView modelAndView){

        List<MainSliderModel> dataList = mainSliderService.getAll();

        modelAndView.addObject("dataList", dataList);
        modelAndView.setViewName("manageSite/manage_main_slider");
        return modelAndView;
    }

    @PostMapping("/slider-save")
    public ModelAndView uploadData(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String url, ModelAndView modelAndView) throws IOException {
        mainSliderService.storeData(file, name, url);


        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }

    /*@GetMapping("/edit-data-{id}")
    public ModelAndView editMainSlider(@PathVariable("id") int dataId, ModelAndView modelAndView){



    }*/

    @GetMapping("/slider-delete-by-{id}")
    public ModelAndView editMainSlider(@PathVariable("id") Long dataId, ModelAndView modelAndView){

        mainSliderService.deleteById(dataId);

        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }




}
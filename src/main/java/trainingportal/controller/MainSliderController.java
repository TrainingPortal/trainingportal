package trainingportal.controller;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public ModelAndView uploadData(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String url, ModelAndView modelAndView,  RedirectAttributes redirect) throws IOException {
        String message = mainSliderService.storeData(file, name, url);


        redirect.addFlashAttribute("message", message);
        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }

    @GetMapping("/slider-delete-by-{id}")
    public ModelAndView deleteMainSlider(@PathVariable("id") Long dataId, ModelAndView modelAndView){

        mainSliderService.deleteById(dataId);

        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }

    @PostMapping("/slider-edit")
    public ModelAndView editData(@RequestParam Long sliderId, @RequestParam MultipartFile editFile, @RequestParam String editName, @RequestParam String editUrl, ModelAndView modelAndView) throws IOException {
        mainSliderService.editById(sliderId, editFile, editName, editUrl);


        modelAndView.setViewName("redirect:/manage_main_slider");
        return modelAndView;
    }



}
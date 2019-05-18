package trainingportal.controller;


import trainingportal.model.MainCardModel;
import trainingportal.service.MainCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@RestController
public class MainCardController {

    private final MainCardService mainCardService;

    @Autowired
    public MainCardController(MainCardService mainCardService) {
        this.mainCardService = mainCardService;
    }


    @GetMapping("/manage_main_card")
    public ModelAndView manageMainCard(ModelAndView modelAndView){

        List<MainCardModel> dataList = mainCardService.getAll();

        modelAndView.addObject("dataList", dataList);
        modelAndView.setViewName("manageSite/manage_main_card");
        return modelAndView;
    }

    @PostMapping("/card-save")
    public ModelAndView uploadData(@RequestParam MultipartFile file, @RequestParam String title,
                                   @RequestParam String description, @RequestParam String name,
                                   @RequestParam String url, ModelAndView modelAndView) throws IOException {
        mainCardService.storeData(file, title, description, name, url);


        modelAndView.setViewName("redirect:/manage_main_card");
        return modelAndView;
    }

    /*@GetMapping("/edit-data-{id}")
    public ModelAndView editMainSlider(@PathVariable("id") int dataId, ModelAndView modelAndView){



    }*/

    @GetMapping("/card-delete-by-{id}")
    public ModelAndView editMainSlider(@PathVariable("id") Long dataId, ModelAndView modelAndView){

        mainCardService.deleteById(dataId);

        modelAndView.setViewName("redirect:/manage_main_card");
        return modelAndView;
    }




}
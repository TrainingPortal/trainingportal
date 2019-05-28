package trainingportal.controller;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    @Autowired
    private MainCardService mainCardService;

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
                                   @RequestParam String url, ModelAndView modelAndView, RedirectAttributes redirect) throws IOException {
        boolean message = mainCardService.storeData(file, title, description, name, url);

        redirect.addFlashAttribute("message", message);
        modelAndView.setViewName("redirect:/manage_main_card");
        return modelAndView;
    }

    @PostMapping("/card-edit")
    public ModelAndView editData(@RequestParam Long cardId, @RequestParam MultipartFile editFile,
                                 @RequestParam String editTitle, @RequestParam String editText,
                                 @RequestParam String editBtnName, @RequestParam String editUrl, ModelAndView modelAndView,
                                 RedirectAttributes redirect) throws IOException {

        boolean message = mainCardService.editById(cardId, editFile, editTitle, editText, editBtnName, editUrl);

        redirect.addFlashAttribute("message", message);
        modelAndView.setViewName("redirect:/manage_main_card");
        return modelAndView;
    }

    @GetMapping("/card-delete-by-{id}")
    public ModelAndView editMainSlider(@PathVariable("id") Long dataId, ModelAndView modelAndView){

        mainCardService.deleteById(dataId);

        modelAndView.setViewName("redirect:/manage_main_card");
        return modelAndView;
    }




}
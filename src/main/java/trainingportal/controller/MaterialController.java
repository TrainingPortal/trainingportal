package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Material;
import trainingportal.service.LessonService;
import trainingportal.service.MaterialService;

import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    LessonService lessonService;

    @RequestMapping("/material_lesson")
    public ModelAndView showMaterialListOfLessons(Long id, ModelAndView modelAndView) {

        List<Material> MaterialOfLesson = materialService.getMaterialLessonId(id);

//        Lesson lesson =  lessonService.findById(id);
//        modelAndView.addObject("MaterialOfLesson", lesson);
//        modelAndView.addObject("id", id);

        modelAndView.addObject("MaterialOfLesson", MaterialOfLesson);
        modelAndView.setViewName("materialCreator/material_lesson");

        return modelAndView;
    }

    @RequestMapping(value = "/material-add", method = RequestMethod.GET)
    public ModelAndView addMaterial(ModelAndView modelAndView) {

        modelAndView.addObject("material", new Material());
        modelAndView.setViewName("materialCreator/material_add");

        return modelAndView;
    }

    @RequestMapping(value = "material-save-{lesson_id}", method = RequestMethod.POST)
    public ModelAndView saveMaterial(@PathVariable("lesson_id") Long lessonId, Material material, ModelAndView modelAndView) {
        materialService.save(material);

        modelAndView.addObject("lesson_id", lessonId);
        modelAndView.setViewName("redirect:/material_lesson");
        return modelAndView;
    }

    @RequestMapping(value = {"edit-material-{materialId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editMaterialBase(@PathVariable("materialId") Long materialId, ModelAndView modelAndView) {
        Material material = materialService.findById(materialId);
        modelAndView.addObject("material", material);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("materialCreator/edit_material_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"edit-material-{materialId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editMaterialById(@PathVariable("id") Long lessonId, Material material,
                                         BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("materialCreator/edit_material_by_id");
            return modelAndView;
        } else {
            materialService.update(material);
            modelAndView.addObject("id", lessonId);
            modelAndView.setViewName("redirect:/material_lesson");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/material-delete-by-{materialId}-{id}", method = RequestMethod.GET)
    public ModelAndView deleteMaterialById(@PathVariable("materialId") Long materialId,
                                           @PathVariable("id") Long lessonId, ModelAndView model, RedirectAttributes redirect) {
        materialService.deleteById(materialId);

        redirect.addFlashAttribute("successMessage", "material deleted successfully");
        model.addObject("id", lessonId);
        model.setViewName("redirect:/material_lesson");
        return model;
    }


}



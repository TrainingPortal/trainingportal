package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Lesson;
import trainingportal.model.Material;
import trainingportal.service.LessonService;
import trainingportal.service.MaterialService;

import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private LessonService lessonService;

    private static final int ROWS_LIMIT = 10;
//    @RequestMapping("/material_lesson")
//    public ModelAndView showMaterialListOfLessons(Long id, ModelAndView modelAndView) {
//
//        List<Material> MaterialOfLesson = materialService.getMaterialLessonId(id);
//
////        Lesson lesson =  lessonService.findById(id);
////        modelAndView.addObject("MaterialOfLesson", lesson);
////        modelAndView.addObject("id", id);
//
//        modelAndView.addObject("MaterialOfLesson", MaterialOfLesson);
//        modelAndView.setViewName("materialCreator/material_lesson");
//
//        return modelAndView;
//    }


    @RequestMapping("/material_lesson/{page}/{lessonId}")
    public ModelAndView showMaterialListOfLessons(@PathVariable("page") int page,
                                                  @PathVariable("lessonId") Long id,
             ModelAndView modelAndView) {

//        List<Material> MaterialOfLesson = materialService.getMaterialLessonId(id);
        List<Material> materialListOfLesson = materialService.getAllAsPageByLessonId(id, page, ROWS_LIMIT);

        Lesson lesson =  lessonService.findById(id);
        modelAndView.addObject("materialOfLesson", lesson);

        modelAndView.addObject("pages", materialService.getPages(id, ROWS_LIMIT));
        modelAndView.addObject("id", id);
        modelAndView.addObject("materialListOfLesson", materialListOfLesson);
        modelAndView.addObject("currentUrl", "material_lesson");
        modelAndView.setViewName("materialCreator/material_lesson");
        return modelAndView;
    }

    @RequestMapping(value = "/material-add-{lessonId}", method = RequestMethod.GET)
    public ModelAndView addMaterial(@PathVariable Long lessonId,
                                    ModelAndView modelAndView) {

        Material material = new Material();
        material.setLessonId(lessonId);

        modelAndView.addObject("material", material);
        modelAndView.setViewName("materialCreator/material_add");

        return modelAndView;
    }

    @RequestMapping(value = "material-save", method = RequestMethod.POST)
    public ModelAndView saveMaterial(@RequestParam("lessonId") Long lessonId,
                                     Material material, ModelAndView modelAndView) {

        materialService.save(material);
        modelAndView.addObject("lesson_id", lessonId);
        modelAndView.setViewName("redirect:/material_lesson/1/" + lessonId);
        return modelAndView;
    }

    @RequestMapping(value = {"edit-material-{materialId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editMaterialBase(@PathVariable("materialId") Long materialId,
                                         @PathVariable("id") Long id,ModelAndView modelAndView) {

        Material material = materialService.findById(materialId);
        modelAndView.addObject("material", material);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("materialCreator/edit_material_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"edit-material-{materialId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editMaterialById(@PathVariable("id") Long id, @PathVariable("materialId") Long materialId,
                                         Material material, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("materialCreator/edit_material_by_id");
            return modelAndView;
        } else {
            materialService.update(material);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/material_lesson/1/" + id);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/material-delete-by/{materialId}/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMaterialById(@PathVariable("materialId") Long materialId,
                                           @PathVariable("id") Long id, ModelAndView model) {
        materialService.deleteById(materialId);

        model.addObject("id", id);
        model.setViewName("redirect:/material_lesson/1/" + id);
        return model;
    }


}



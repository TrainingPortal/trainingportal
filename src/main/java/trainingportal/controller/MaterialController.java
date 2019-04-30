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
import trainingportal.service.MaterialServiceImpl;

import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    MaterialServiceImpl materialService;

    @RequestMapping(value = "material_create")
    public ModelAndView showMaterialsList(Long Id, ModelAndView modelAndView) {
        List<Material> materialList = materialService.findAll();
        modelAndView.addObject("materialList", materialList);
        modelAndView.setViewName("materialCreator/material_create");
        return modelAndView;
    }

    @RequestMapping(value = "/material-add", method = RequestMethod.GET)
    public ModelAndView addMaterial(ModelAndView modelAndView) {

        modelAndView.addObject("material", new Material());
        modelAndView.setViewName("materialCreator/material_add");

        return modelAndView;
    }

    @RequestMapping(value = "material-save", method = RequestMethod.POST)
    public ModelAndView saveMaterial(Material material, ModelAndView modelAndView) {
        materialService.save(material);
        modelAndView.setViewName("redirect:/material_create");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-material-{id}"}, method = RequestMethod.GET)
    public ModelAndView editMaterialBase(@PathVariable("id") Long materialId, ModelAndView modelAndView) {
        Material material = materialService.findById(materialId);
        modelAndView.addObject("material", material);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("materialCreator/edit_material_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-material-{id}"}, method = RequestMethod.POST)
    public ModelAndView editMaterialById(Material material, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("materialCreator/edit_material_by_id");
            return modelAndView;
        } else {
            materialService.update(material);
            modelAndView.setViewName("redirect:/material_create");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/material-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteMaterialById(@PathVariable("id") Long MaterialId, ModelAndView model, RedirectAttributes redirect) {
        materialService.deleteById(MaterialId);

        redirect.addFlashAttribute("successMessage", "material deleted successfully");

        model.setViewName("redirect:/material_create");
        return model;
    }


}



package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Group;
import trainingportal.service.GroupServiceImpl;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @GetMapping("/group_create")
    public ModelAndView showGroupsList(Long groupId, ModelAndView modelAndView) {
        List<Group> groupList = groupService.GroupsList();
//        List<Group> groupList = groupService.getAllGroupsById(groupId);
//        Group groupList = groupService.findGroupById(groupId);
        modelAndView.addObject("groupList", groupList);
        modelAndView.setViewName("groupCreator/group_create");
        return modelAndView;
    }

    @GetMapping("/group-add")
    public ModelAndView addGroup(ModelAndView modelAndView) {

        modelAndView.addObject("group", new Group());
        modelAndView.setViewName("groupCreator/group_add");

        return modelAndView;
    }

    @PostMapping("/group-save")
    public ModelAndView saveGroup(Group group, ModelAndView modelAndView) {
        groupService.saveGroup(group);
        modelAndView.setViewName("redirect:/group_create");
        return modelAndView;
    }

    @GetMapping({"/edit-group-{id}"})
    public ModelAndView editGroupBase(@PathVariable("id") Long groupId, ModelAndView modelAndView) {
        Group group = groupService.findGroupById(groupId);
        modelAndView.addObject("group", group);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("groupCreator/edit_group_by_id");

        return modelAndView;
    }

    @PostMapping({"/edit-group-{id}"})
    public ModelAndView editGroupById(Group group, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("groupCreator/edit_group_by_id");
            return modelAndView;
        } else {
            groupService.editGroup(group);
            modelAndView.setViewName("redirect:/group_create");
            return modelAndView;
        }
    }

    @GetMapping("/group-delete-by-{id}")
    public ModelAndView deleteGroupById(@PathVariable("id") Long groupId, ModelAndView model, RedirectAttributes redirect) {
        groupService.deleteGroupById(groupId);

        redirect.addFlashAttribute("successMessage", "Group deleted successfully");

        model.setViewName("redirect:/group_create");
        return model;
    }


}

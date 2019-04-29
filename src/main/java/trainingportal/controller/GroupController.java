package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Group;
import trainingportal.service.GroupServiceImpl;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @RequestMapping(value = "/group_create")
    public ModelAndView showGroupsList(Long groupId, ModelAndView modelAndView) {
        List<Group> groupList = groupService.GroupsList();
//        List<Group> groupList = groupService.getAllGroupsById(groupId);
//        Group groupList = groupService.findGroupById(groupId);
        modelAndView.addObject("groupList", groupList);
        modelAndView.setViewName("groupCreator/group_create");
        return modelAndView;
    }

    @RequestMapping(value = "/group-add", method = RequestMethod.GET)
    public ModelAndView addGroup(ModelAndView modelAndView) {

        modelAndView.addObject("group", new Group());
        modelAndView.setViewName("groupCreator/group_add");

        return modelAndView;
    }

    @RequestMapping(value = "group-save", method = RequestMethod.POST)
    public ModelAndView saveGroup(Group group, ModelAndView modelAndView) {
        groupService.saveGroup(group);
        modelAndView.setViewName("redirect:/group_create");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-group-{id}"}, method = RequestMethod.GET)
    public ModelAndView editGroupBase(@PathVariable("id") Long groupId, ModelAndView modelAndView) {
        Group group = groupService.findGroupById(groupId);
        modelAndView.addObject("group", group);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("groupCreator/edit_group_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-group-{id}"}, method = RequestMethod.POST)
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

    @RequestMapping(value = "/group-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteGroupById(@PathVariable("id") Long groupId, ModelAndView model, RedirectAttributes redirect) {
        groupService.deleteGroupById(groupId);

        redirect.addFlashAttribute("successMessage", "Group deleted successfully");

        model.setViewName("redirect:/group_create");
        return model;
    }


}

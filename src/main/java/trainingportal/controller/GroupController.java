package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.*;
import trainingportal.service.CourseServiceImpl;
import trainingportal.service.GroupServiceImpl;
import trainingportal.universalexportcreator.dao.DataDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    CourseServiceImpl courseService;

    private static final int ROWS_LIMIT = 10;

    @RequestMapping(value = "/group_create/{page}")
    public ModelAndView showGroupsList(@PathVariable("page") int page, Long groupId, ModelAndView modelAndView) {

        List<Group> groupList = groupService.getAllAsPage(page, ROWS_LIMIT);

        for(Group group : groupList){
            group.setCourse(courseService.findById(group.getCourseId()));
            group.setStatus(groupService.findStatusById(group.getStatusId()));
        }
        modelAndView.addObject("groupList", groupList);
        modelAndView.addObject("pages", groupService.getPages(ROWS_LIMIT));
        modelAndView.setViewName("groupCreator/group_create");
        modelAndView.addObject("currentUrl", "group_create");
        return modelAndView;
    }

    @GetMapping("/group-add")
    public ModelAndView addGroup(ModelAndView modelAndView) {

        modelAndView.addObject("group", new Group());

        List<Course> courses = courseService.findAll();
        List<GroupStatus> statuses = groupService.getStatusList();

        modelAndView.addObject("courses", courses);
        modelAndView.addObject("statuses", statuses);
        modelAndView.setViewName("groupCreator/group_add");

        return modelAndView;
    }

    @PostMapping("/group-save")
    public ModelAndView saveGroup(Group group, ModelAndView modelAndView) {
        groupService.saveGroup(group);
        modelAndView.setViewName("redirect:/group_create/1");
        return modelAndView;
    }

    @GetMapping({"/edit-group-{id}"})
    public ModelAndView editGroupBase(@PathVariable("id") Long groupId, ModelAndView modelAndView) {
        Group group = groupService.findGroupById(groupId);

        List<Course> courses = courseService.findAll();
        List<GroupStatus> statuses = groupService.getStatusList();

        modelAndView.addObject("courses", courses);
        modelAndView.addObject("statuses", statuses);
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
            modelAndView.setViewName("redirect:/group_create/1");
            return modelAndView;
        }
    }

    @GetMapping("/group-delete-by-{id}")
    public ModelAndView deleteGroupById(@PathVariable("id") Long groupId, ModelAndView model, RedirectAttributes redirect) {
        groupService.deleteGroupById(groupId);

        redirect.addFlashAttribute("successMessage", "Group deleted successfully");

        model.setViewName("redirect:/group_create/1");
        return model;
    }

    @Autowired
    public DataDaoImpl dataDao;

    @RequestMapping(value = "/group-download-all-groups", method = RequestMethod.GET)
    public ModelAndView downloadAllTrainers(ModelAndView model, RedirectAttributes redir){

        List list = new ArrayList();
        list.add("name");
        list.add("capacity");
        list.add("course_id");
        list.add("status_id");

        List<List> courses = dataDao.findFieldsFromTable(list, "groups","allGroups","table");

//        String fromFile = "/Users/mrlova/Downloads/log.txt";
//        String toFile = "/Users/mrlova/Downloads/log.txt";
//
//        try {
//            FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return model;
    }


}

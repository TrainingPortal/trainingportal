package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Course;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.service.CourseServiceImpl;
import trainingportal.service.GroupServiceImpl;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    CourseServiceImpl courseService;

    private static final int ROWS_LIMIT = 10;
//old method
//    @RequestMapping(value = "/group_create/{page}")
//    public ModelAndView showGroupsList(@PathVariable("page") int page, Long groupId, ModelAndView modelAndView) {
//
//        List<Group> groupList = groupService.getAllAsPage(page, ROWS_LIMIT);
//
//        for(Group group : groupList){
//            group.setCourse(courseService.findById(group.getCourseId()));
//            group.setStatus(groupService.findStatusById(group.getStatusId()));
//        }
//        modelAndView.addObject("groupList", groupList);
//        modelAndView.addObject("pages", groupService.getPages(ROWS_LIMIT));
//        modelAndView.setViewName("groupCreator/group_create");
//        modelAndView.addObject("currentUrl", "group_create");
//        return modelAndView;
//    }

    @RequestMapping("/group_create/{page}/{courseId}")
    public ModelAndView showLessonListOfCourse(@PathVariable("page") int page,
                                               @PathVariable("courseId") Long id,
                                               ModelAndView modelAndView) {

        List<Group> groupList = groupService.getAllAsPageByCourseId(id ,page, ROWS_LIMIT);

        for(Group group : groupList){
            group.setCourse(courseService.findById(group.getCourseId()));
            group.setStatus(groupService.findStatusById(group.getStatusId()));
        }

        Course course = courseService.findById(id);
        modelAndView.addObject("courseGroup", course);

        modelAndView.addObject("pages", groupService.getPages(id, ROWS_LIMIT));
        modelAndView.addObject("id", id);
        modelAndView.addObject("groupList", groupList);
        modelAndView.addObject("currentUrl", "group_create");
        modelAndView.setViewName("groupCreator/group_create");

        return modelAndView;
    }

    @GetMapping("/group-add-{courseId}")
    public ModelAndView addGroup(@PathVariable Long courseId, ModelAndView modelAndView) {

        Group group = new Group();
        group.setCourseId(courseId);

        modelAndView.addObject("group", group);

        List<Course> courses = courseService.findAll();
        List<GroupStatus> statuses = groupService.getStatusList();

        modelAndView.addObject("courses", courses);
        modelAndView.addObject("statuses", statuses);
        modelAndView.setViewName("groupCreator/group_add");

        return modelAndView;
    }

    @PostMapping("/group-save")
    public ModelAndView saveGroup(@RequestParam("courseId") Long courseId, Group group, ModelAndView modelAndView) {
        groupService.saveGroup(group);
        modelAndView.setViewName("redirect:/group_create/1/" + courseId);
        return modelAndView;
    }

    @GetMapping({"/edit-group-{groupId}-{id}"})
    public ModelAndView editGroupBase(@PathVariable("groupId") Long groupId,
                                      @PathVariable("id") Long id,ModelAndView modelAndView) {
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

    @PostMapping({"/edit-group-{groupId}-{id}"})
    public ModelAndView editGroupById(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId,
            Group group, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("groupCreator/edit_group_by_id");
            return modelAndView;
        } else {
            groupService.editGroup(group);
            modelAndView.setViewName("redirect:/group_create/1/" + id);
            return modelAndView;
        }
    }

    @GetMapping("/group-delete-by/{groupId}/{id}")
    public ModelAndView deleteGroupById(@PathVariable("groupId") Long groupId,
                                        @PathVariable("id") Long id,ModelAndView model, RedirectAttributes redirect) {
        groupService.deleteGroupById(groupId);

        redirect.addFlashAttribute("successMessage", "Group deleted successfully");

        model.setViewName("redirect:/group_create/1/" + id);
        return model;
    }
}

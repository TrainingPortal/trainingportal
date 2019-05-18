package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Course;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.security.UserSecurity;
import trainingportal.service.CourseService;
import trainingportal.service.GroupService;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserSecurity userSecurity;

    private static final int ROWS_LIMIT = 10;

    @RequestMapping("/group_create/{page}/{courseId}")
    public ModelAndView showLessonListOfCourse(@PathVariable("page") int page,
                                               @PathVariable("courseId") Long id,
                                               ModelAndView modelAndView) {

        List<Group> groupList = groupService.getGroupsPage(id, page, ROWS_LIMIT,
                userSecurity.getLoggedInUserId(), userSecurity.getLoggedInUserRole());

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

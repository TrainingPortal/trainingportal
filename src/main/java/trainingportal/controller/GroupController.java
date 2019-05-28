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
import trainingportal.model.User;
import trainingportal.security.UserSecurity;
import trainingportal.service.CourseService;
import trainingportal.service.GroupService;
import trainingportal.service.UserService;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private UserService userService;

    private static final int ROWS_PER_PAGE = 10;

    @RequestMapping("/group_create/{page}/{courseId}")
    public ModelAndView showLessonListOfCourse(@PathVariable("page") int page,
                                               @PathVariable("courseId") Long id,
                                               ModelAndView modelAndView) {

        List<Group> groupList = groupService.getGroupsPage(id, page, ROWS_PER_PAGE,
                userSecurity.getLoggedInUserId(), userSecurity.getLoggedInUserRole());

        Course course = courseService.findById(id);
        modelAndView.addObject("courseGroup", course);

        modelAndView.addObject("pages",
                groupService.getPages(id,
                        ROWS_PER_PAGE,
                        userSecurity.getLoggedInUserId(),
                        userSecurity.getLoggedInUserRole()));
        modelAndView.addObject("id", id);
        modelAndView.addObject("groupList", groupList);
        modelAndView.addObject("currentUrl", "group_create");
        modelAndView.setViewName("groupCreator/group_create");

        return modelAndView;
    }

    @GetMapping("/group_users/{page}/{groupId}")
    public ModelAndView showGroupUsersList(@PathVariable("page") int page,
                                               @PathVariable("groupId") Long groupId,
                                               ModelAndView modelAndView) {

        List<User> userList = userService.getUsersByGroupIdAsPage(page, ROWS_PER_PAGE, groupId);

        //Course course = courseService.findById(groupId);
        //modelAndView.addObject("courseGroup", course);

        modelAndView.addObject("pages", userService.getPagesAmountOfUsersByGroupId(groupId, ROWS_PER_PAGE));
        modelAndView.addObject("groupId", groupId);
        modelAndView.addObject("courseId", courseService.findCourseIdByGroupId(groupId).getCourseId());
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("currentUrl", "group_users");
        modelAndView.setViewName("groupCreator/group_users");

        return modelAndView;
    }

    @GetMapping("/add_users_to_group/{groupId}")
    public ModelAndView showAddSubordinates(@PathVariable("groupId") Long groupId, ModelAndView model) {

        List<User> users = userService.findUsersForGroupByGroupId(groupId);

        model.addObject("users", users);

        model.setViewName("groupCreator/add_users_to_group");

        return model;
    }

    @PostMapping("/add_selected_users_to_group/{groupId}")
    public ModelAndView addSelectedSubordinates(@PathVariable("groupId") Long groupId,
                                                @RequestParam(value = "userId", required = false) Long[] userIds,
                                                ModelAndView model, RedirectAttributes redir) {

        String message = userService.assignUsersToGroup(groupId, userIds);

        redir.addFlashAttribute("infoMessage", message);

        model.setViewName("redirect:/group_users/1/" + groupId);

        return model;
    }

    @GetMapping("/release_user_from_group/{userId}/{groupId}")
    public ModelAndView setUserFree(@PathVariable("userId") Long userId,
                                    @PathVariable("groupId") Long groupId,
                                    ModelAndView model, RedirectAttributes redir) {

        User user = userService.findById(userId);

        groupService.deleteFromUserGroupByUserIdAndGroupId(userId, groupId);

        redir.addFlashAttribute("infoMessage",
                "User " + user.getUserName() + " is not a part of this group any more.");
        model.setViewName("redirect:/group_users/1/" + groupId);

        return model;
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
        groupService.save(group);
        modelAndView.setViewName("redirect:/group_create/1/" + courseId);
        return modelAndView;
    }

    @GetMapping({"/edit-group-{groupId}-{id}"})
    public ModelAndView editGroupBase(@PathVariable("groupId") Long groupId,
                                      @PathVariable("id") Long id, ModelAndView modelAndView) {
        Group group = groupService.findById(groupId);

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
            groupService.update(group);
            modelAndView.setViewName("redirect:/group_create/1/" + id);

            return modelAndView;
        }
    }

    @GetMapping("/group-delete-by/{groupId}/{id}")
    public ModelAndView deleteGroupById(@PathVariable("groupId") Long groupId,
                                        @PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirect) {
        groupService.deleteById(groupId);

        redirect.addFlashAttribute("successMessage", "Group deleted successfully");

        model.setViewName("redirect:/group_create/1/" + id);
        return model;
    }
}

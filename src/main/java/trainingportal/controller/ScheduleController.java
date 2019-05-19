package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Group;
import trainingportal.model.Lesson;
import trainingportal.model.Schedule;
import trainingportal.service.GroupService;
import trainingportal.service.LessonService;
import trainingportal.service.ScheduleService;

import java.util.List;

@Controller
public class ScheduleController {

    private static final int ROWS_LIMIT = 10;
    private final ScheduleService scheduleService;
    private final GroupService groupService;
    private final LessonService lessonService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, GroupService groupService, LessonService lessonService) {
        this.scheduleService = scheduleService;
        this.groupService = groupService;
        this.lessonService = lessonService;
    }

    @RequestMapping("/schedule_create/{page}/{groupId}")
    public ModelAndView showScheduleListOfGroup(@PathVariable("page") int page,
                                               @PathVariable("groupId") Long id,
                                               ModelAndView modelAndView) {
        List<Schedule> scheduleOfGroup = scheduleService.getAllAsPageByGroupId(id, page , ROWS_LIMIT);

        Group group = groupService.findById(id);
        modelAndView.addObject("groupSchedule",group);

        modelAndView.addObject("pages", scheduleService.getPages(id,ROWS_LIMIT));
        modelAndView.addObject("id", id);
        modelAndView.addObject("scheduleOfGroup", scheduleOfGroup);
        modelAndView.addObject("currentUrl", "schedule_create");
        modelAndView.setViewName("schedule/schedule_create");

        return modelAndView;
    }

    @RequestMapping("/schedule_for_user/{page}/{groupId}")
    public ModelAndView showScheduleForUserOfGroup(@PathVariable("page") int page,
                                                @PathVariable("groupId") Long id,
                                                ModelAndView modelAndView) {

        List<Schedule> scheduleForUser = scheduleService.getAllAsPageByGroupId(id, page , ROWS_LIMIT);

        modelAndView.addObject("pages", scheduleService.getPages(id,ROWS_LIMIT));
        modelAndView.addObject("id", id);
        modelAndView.addObject("scheduleForUser", scheduleForUser);
        modelAndView.addObject("currentUrl", "schedule_for_user");
        modelAndView.setViewName("schedule/schedule_for_user");

        return modelAndView;
    }

    @RequestMapping(value = "/schedule-add-{groupId}", method = RequestMethod.GET)
    public ModelAndView addSchedule(@PathVariable Long groupId, ModelAndView modelAndView) {

        Schedule schedule = new Schedule();
        schedule.setScheduleGroupId(groupId);

        List<Lesson> lessons = lessonService.findAll();

        modelAndView.addObject("lessons", lessons);
        modelAndView.addObject("schedule", schedule);
        modelAndView.setViewName("schedule/schedule_add");
        return modelAndView;
    }

    @RequestMapping(value = "schedule-save", method = RequestMethod.POST)
    public ModelAndView saveSchedule(@RequestParam("groupId") Long groupId,
                                     Schedule schedule, ModelAndView modelAndView) {

        scheduleService.save(schedule);
        modelAndView.addObject("groupId", groupId);
        modelAndView.setViewName("redirect:/schedule_create/1/" + groupId);
        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-edit-{scheduleId}-{groupId}"}, method = RequestMethod.GET)
    public ModelAndView editScheduleBase(@PathVariable("scheduleId") Long scheduleId,
                                       @PathVariable("groupId") Long id, ModelAndView modelAndView) {

        Schedule schedule = scheduleService.findById(scheduleId);
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("schedule/schedule_edit");
        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-edit-{scheduleId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editScheduleById(@PathVariable("id") Long id,
                                       @PathVariable("scheduleId") Long scheduleId,
                                       Schedule schedule, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("schedule/schedule_edit");
            return modelAndView;
        } else {
            scheduleService.update(schedule);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/schedule_create/1/" + id);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/schedule-delete/{scheduleId}/{id}", method = RequestMethod.GET)
    public ModelAndView deleteScheduleById(@PathVariable("scheduleId") Long scheduleId,
                                         @PathVariable("id") Long id, ModelAndView model) {
        scheduleService.deleteById(scheduleId);
//        redirect.addFlashAttribute("successMessage", "schedule deleted successfully");
        model.setViewName("redirect:/schedule_create/1/" + id);
        return model;
    }

}

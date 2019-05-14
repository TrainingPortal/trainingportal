package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.*;
import trainingportal.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceTypeService attendanceTypeService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/attendance")
    public ModelAndView get(ModelAndView modelAndView,
                             Authentication authentication){

        Long trainerId = ((LoggedInUser) authentication.getPrincipal()).getId();

        User trainer = userService.findById(trainerId);
        List<Course> courses = courseService.findByTrainerId(trainerId);

        modelAndView.addObject("trainer", trainer);
        modelAndView.addObject("courses", courses);

        modelAndView.setViewName("attendance/main");

        return modelAndView;
    }

    @GetMapping(value = "groups/{courseId}")
    public ModelAndView getGroups(
            ModelAndView modelAndView,
            @PathVariable("courseId") Long courseId
    ){
        List<Group> groups = groupService.findAllByCourseId(courseId);

        modelAndView.addObject("groups", groups);
        modelAndView.setViewName("attendance/groups");

        return modelAndView;
    }

    @GetMapping(value = "/presense/{groupId}")
    public ModelAndView getPresense(
            ModelAndView modelAndView,
            @PathVariable("groupId") Long groupId
    ){

        Schedule schedule = scheduleService.findByGroupId(groupId);
        List<User> users = userService.findAllByGroup(groupId);
        List<AttendanceType> attendanceTypes = attendanceTypeService.getAllAttendanceList();
        List<Attendance> attendances = new ArrayList<Attendance>();
        Lesson lesson = lessonService.getLessonByScheduleId(schedule.getSheduleId());


        for (int i = 0; i < users.size(); i++) {
            Attendance attendance = new Attendance();
            attendance.setScheduleId(schedule.getSheduleId());
            attendance.setUser(users.get(i));
            attendance.setUserId(users.get(i).getUserId());
            attendances.add(attendance);
        }

        AttendanceForm attendanceForm = new AttendanceForm();
        attendanceForm.setAttendances(attendances);

        modelAndView.addObject("attendanceTypes",attendanceTypes);
        modelAndView.addObject("attendances", attendanceForm);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("users", users);
        modelAndView.addObject("schedule", schedule);
        modelAndView.setViewName("attendance/presense");

        return modelAndView;
    }

    @PostMapping(value = "/submit-attendance")
    public ModelAndView submitAttendance(
            ModelAndView model,
            @ModelAttribute AttendanceForm attendances
            ){

        for (Attendance attendance:attendances.getAttendances()) {
            attendanceService.save(attendance);
        }

        model.setViewName("redirect:/attendance");
        return model;
    }
}
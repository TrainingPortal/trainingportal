package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.*;
import trainingportal.security.UserSecurity;
import trainingportal.service.*;

import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private UserSecurity userSecurity;
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


    @GetMapping(value = "/myCourses")
    public ModelAndView get(ModelAndView modelAndView){

        Long trainerId = userSecurity.getLoggedInUserId();
        User trainer = userService.findById(trainerId);
        List<Course> courses = courseService.findByTrainerId(trainerId);

        modelAndView.addObject("trainer", trainer);
        modelAndView.addObject("courses", courses);

        modelAndView.setViewName("attendance/main");

        return modelAndView;
    }

    @GetMapping(value = "myGroups/{courseId}")
    public ModelAndView getGroups(
            ModelAndView modelAndView,
            @PathVariable("courseId") Long courseId
    ){
        List<Group> groups = groupService.findAllByCourseId(courseId);

        modelAndView.addObject("groups", groups);
        modelAndView.setViewName("attendance/groups");

        return modelAndView;
    }

    @GetMapping(value = "myScheduleForGroup/{groupId}")
    public ModelAndView getSchedules(
            ModelAndView modelAndView,
            @PathVariable("groupId") Long groupId
    ){
        List<Schedule> schedules = scheduleService.getSchedules(groupId);
        Group group = groupService.findById(groupId);

        modelAndView.addObject("schedules", schedules);
        modelAndView.addObject("group", group);

        modelAndView.setViewName("attendance/schedules");

        return modelAndView;
    }

    @GetMapping(value = "/presence/{groupId}/{scheduleId}")
    public ModelAndView getPresence(
            ModelAndView modelAndView,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("groupId") Long groupId
    ){

        Schedule schedule = scheduleService.findById(scheduleId);
        List<User> users = userService.findAllByGroup(groupId);
        List<AttendanceType> attendanceTypes = attendanceTypeService.getAllAttendanceList();
        Lesson lesson = lessonService.getLessonByScheduleId(schedule.getScheduleId());
        AttendanceForm attendanceForm = attendanceService.getAttendanceListWithStudents(schedule,users);

        modelAndView.addObject("attendanceTypes",attendanceTypes);
        modelAndView.addObject("attendances", attendanceForm);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("users", users);
        modelAndView.addObject("schedule", schedule);

        modelAndView.setViewName("attendance/presence");

        return modelAndView;
    }

    @PostMapping(value = "/submit-attendance")
    public ModelAndView submitAttendance(
            ModelAndView model,
            @ModelAttribute AttendanceForm attendances
            ){

       attendanceService.saveAll(attendances);

        model.setViewName("redirect:/myCourses");
        return model;
    }

    @GetMapping(value = "mySubordinates")
    public ModelAndView getSubordinatesAttendance(
            ModelAndView modelAndView
    ){
        Long managerId = userSecurity.getLoggedInUserId();
        // Find all subordinates of the manager by manager's id
        List<Attendance> attendances = attendanceService.getSubordinatesAttendanceByManager(managerId);

        modelAndView.addObject("attendances", attendances);
        modelAndView.setViewName("attendance/subordinatesAttendances");

        return modelAndView;
    }
}
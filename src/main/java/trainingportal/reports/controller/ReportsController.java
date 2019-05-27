package trainingportal.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.AttendanceType;
import trainingportal.model.Course;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.reports.dao.exception.DataDaoExceptions;
import trainingportal.reports.download.Loader;
import trainingportal.reports.service.Filter;
import trainingportal.reports.service.ReportsCreate;
import trainingportal.service.AttendanceTypeService;
import trainingportal.service.CourseService;
import trainingportal.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class ReportsController {

    @Autowired
    private Loader loader;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendanceTypeService attendanceTypeService;

    @Autowired
    private ReportsCreate report;

    @Autowired
    private Filter filter;

    @RequestMapping(value = "report/download", method = RequestMethod.GET)
    public ModelAndView download(@NotNull ModelAndView model) {
        return model;
    }

    @RequestMapping(value = "report/downloadTrainer", method = RequestMethod.GET)
    public ModelAndView downloadTrainer(@NotNull ModelAndView model) {

        List<User> trainers = userService.findAllEnabledByRole(Role.TRAINER);

        if (trainers.isEmpty()){
            model.setViewName("report/Error");
            return model;
        }else {
            model.addObject("trainers", trainers);
            return model;
        }
    }

    @RequestMapping(value = "report/downloadLevel", method = RequestMethod.GET)
    public ModelAndView downloadLevel(@NotNull ModelAndView model) {

        List<Course> courseLevels = courseService.findAll();
        List<String> allCourseList = filter.filterAllCourseList(courseLevels);

        if (allCourseList.isEmpty()){
            model.setViewName("report/Error");
            return model;
        }else {
            model.addObject("allCourses", allCourseList);
            return model;
        }
    }

    @RequestMapping(value = "report/downloadAttendance", method = RequestMethod.GET)
    public ModelAndView downloadAttendance(@NotNull ModelAndView model) {

        List<AttendanceType> allTypes = attendanceTypeService.getAllAttendanceList();

        if (allTypes.isEmpty()){
            model.setViewName("report/Error");
            return model;
        }else {
            model.addObject("allTypes", allTypes);
            return model;
        }
    }

    @RequestMapping(value = "report/downloadTrainer/{trainerId}", method = RequestMethod.GET)
    public ResponseEntity downloadTrainerFile(@PathVariable("trainerId") Long trainerId) {

        report.createNewTrainerReport(trainerId);
        return loader.downloadFile("Trainer.xlsx");

    }

    @RequestMapping(value = "report/downloadLevel/{levelName}", method = RequestMethod.GET)
    public ResponseEntity downloadLevelFile(@PathVariable("levelName") String levelName) {

        report.createNewLevelReport(levelName);
        return loader.downloadFile("Level.xlsx");
    }

    @RequestMapping(value = "report/downloadAttendance/{attendanceId}", method = RequestMethod.GET)
    public ResponseEntity downloadAttendanceFile(@PathVariable("attendanceId") Long attendanceId) {

        report.createNewAttendanceReport(attendanceId);
        return loader.downloadFile("Attendance.xlsx");
    }
}

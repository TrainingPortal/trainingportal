package trainingportal.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.*;
import trainingportal.reports.reportslist.Reports;
import trainingportal.service.AttendanceTypeService;
import trainingportal.service.CourseService;
import trainingportal.service.UserService;
import trainingportal.reports.download.Download;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {


    @Autowired
    private Download download;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendanceTypeService attendanceTypeService;

    @Autowired
    private Reports report;

    @RequestMapping(value = "data/download", method = RequestMethod.GET)
    public ModelAndView download(@NotNull ModelAndView model){ return model; }

    @RequestMapping(value = "data/downloadTrainer", method = RequestMethod.GET)
    public ModelAndView downloadTrainer(@NotNull ModelAndView model){

        List<User> trainers = userService.findAllEnabledByRole(Role.TRAINER);

        model.addObject("trainers",trainers);
        return model;
    }

    @RequestMapping(value = "data/downloadLevel", method = RequestMethod.GET)
    public ModelAndView downloadLevel(@NotNull ModelAndView model){

        List<Course> courseLevels = courseService.findAll();

        List<String> allCourse = new ArrayList();
        List<String> repeated = new ArrayList();
        List<Boolean> truee = new ArrayList();

        //Select all uniq course name and set it in allCourse
        if (!courseLevels.isEmpty()) {
            for (Course courseLevel : courseLevels) {
                if (!repeated.isEmpty()) {
                    for (int i = 0; i < repeated.size(); i++) {
                        if (courseLevel.getCourseLevel().equals(repeated.get(i))) {
                            truee.add(new Boolean(true));
                        }
                    }
                    if (truee.isEmpty()){
                        allCourse.add(courseLevel.getCourseLevel());
                        repeated.add(courseLevel.getCourseLevel());
                    }else
                        truee.clear();
                }else {
                    allCourse.add(courseLevel.getCourseLevel());
                    repeated.add(courseLevel.getCourseLevel());
                }
            }
        } else
            throw new NullPointerException();

        model.addObject("allCourses",allCourse);
        return model;
    }

    @RequestMapping(value = "data/downloadAttendance", method = RequestMethod.GET)
    public ModelAndView downloadAttendance(@NotNull ModelAndView model){

        List<AttendanceType> allTypes = attendanceTypeService.getAllAttendanceList();

        model.addObject("allTypes",allTypes);
        return model;
    }

    @RequestMapping(value = "data/downloadTrainer/{trainerId}", method = RequestMethod.GET)
    public ResponseEntity downloadTrainerFile(@PathVariable("trainerId") Long trainerId){

        if(report.createNewTrainerReport(trainerId)){
            return download.downloadFile("Trainer.xlsx");
        }
        return null;
    }

    @RequestMapping(value = "data/downloadLevel/{levelName}", method = RequestMethod.GET)
    public ResponseEntity downloadLevelFile(@PathVariable("levelName") String levelName){

        if (report.createNewLevelReport(levelName)){
            return download.downloadFile("Level.xlsx");
        }
        return null;
    }

    @RequestMapping(value = "data/downloadAttendance/{attendanceId}", method = RequestMethod.GET)
    public ResponseEntity downloadAttendanceFile(@PathVariable("attendanceId") Long attendanceId){

        if (report.createNewAttendanceReport(attendanceId)){
            return download.downloadFile("Attendance.xlsx");
        }
        return null;
    }
}

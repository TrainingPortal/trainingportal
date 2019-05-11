package trainingportal.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.reports.service.DataService;
import trainingportal.service.AttendanceType;
import trainingportal.service.CourseService;
import trainingportal.service.UserService;
import trainingportal.reports.download.Download;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private Download download;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendanceType attendanceType;

    @RequestMapping(value = "data/download", method = RequestMethod.GET)
    public ModelAndView download(@NotNull ModelAndView model){
        return model;
    }

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

        List<String> allTypes = attendanceType.getAllReasonsList();
        model.addObject("allTypes",allTypes);
        return model;
    }

    @RequestMapping(value = "data/downloadTrainer/{trainerId}", method = RequestMethod.GET)
    public ResponseEntity downloadTrainerFile(@PathVariable("trainerId") Long trainerId){

        if (createNewTrainerReport(trainerId)){
            return download.downloadFile("Trainer.xlsx");
        }
        return null;
    }

    @RequestMapping(value = "data/downloadLevel/{levelName}", method = RequestMethod.GET)
    public ResponseEntity downloadLevelFile(@PathVariable("levelName") String levelName){

        if (createNewLevelReport(levelName)){
            return download.downloadFile("Level.xlsx");
        }
        return null;
    }

    @RequestMapping(value = "data/downloadAttendance/{attendance_type}", method = RequestMethod.GET)
    public ResponseEntity downloadAttendanceFile(@PathVariable("attendance_type") String attendance_type){

        if (createNewAttendanceReport(attendance_type)){
            return download.downloadFile("Attendance.xlsx");
        }
        return null;
    }

    private boolean createNewTrainerReport(long trainerId){

        List list = new ArrayList();
        list.add("Trainer Name");
        list.add("Course Name");
        list.add("Course Level");
        list.add("Course Status");

        //Select
        String sql = "SELECT DISTINCT users.name as \"Trainer Name\", course.name as \"Course Name\", course.course_level as \"Course Level\", course_status.name_status as \"Course Status\" \n" +
                "FROM course INNER JOIN course_status ON course.course_status_id = course_status.id INNER JOIN users ON course.trainer_id = users.role_id WHERE users.user_id =  " + trainerId;

        List<List> courses = dataService.getMultiFieldsFromTables(list, sql,"Trainer","table");
        return true;
    }

    private boolean createNewLevelReport(String levelName){

        List list = new ArrayList();
        list.add("User Name");
        list.add("Role");
        list.add("Email");
        list.add("Course Name");
        list.add("Group Name");

        //Choose by users who is managers or employee that have availability enter to course and course level = to ......
        String sql = "SELECT DISTINCT users.name as \"User Name\", email as \"Email\", course.name as \"Course Name\", course.course_level as \"Course Level\" \n" +
                "FROM users INNER JOIN desired_period ON users.user_id = desired_period.user_id INNER JOIN course ON desired_period.course_id = course.course_id\n" +
                "WHERE ( users.role_id = 2 OR users.role_id = 4 ) AND course.course_level = " + "\'" + levelName + "\'";

        List<List> courses = dataService.getMultiFieldsFromTables(list, sql,"Level","table");
        return true;
    }

    private boolean createNewAttendanceReport(String attendance_type){

        List list = new ArrayList();
        list.add("User Name");
        list.add("Lesson Date");
        list.add("Lesson Name");
        list.add("Group Name");
        list.add("Course Name");
        list.add("Status");

        //Choose by attendance for only active or stopped course where attendance type = to ......
        String sql = "SELECT users.name as \"User Name\", schedule.date_lesson as \"Lesson Date\", lesson.lesson_name as \"Lesson Name\", type, groups.name as \"Group Name\", course.name as \"Course Name\", course_status.name_status as \"Status\"  \n" +
                "FROM attendance INNER JOIN users ON attendance.user_id = users.user_id INNER JOIN schedule ON attendance.schedule_id = schedule.id \n" +
                "LEFT OUTER JOIN attendance_type ON attendance.type_id = attendance_type.id \n" +
                "INNER JOIN lesson ON schedule.lesson_id = lesson.lesson_id LEFT OUTER JOIN groups ON schedule.group_id = groups.id\n" +
                "LEFT OUTER JOIN course ON groups.course_id = course.course_id INNER JOIN course_status ON course.course_status_id = course_status.id\n" +
                "WHERE ( course_status.id = 1 OR course_status.id = 3 ) AND attendance_type.id = " + "\'" + attendance_type + "\'";

        List<List> courses = dataService.getMultiFieldsFromTables(list, sql,"Attendance","table");
        return true;
    }
}

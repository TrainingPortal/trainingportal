package trainingportal.reports.reportslist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import trainingportal.reports.service.DataService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Reports {

    @Autowired
    DataService dataService;

    public boolean createNewTrainerReport(Long trainerId){

        List list = new ArrayList();
        list.add("Trainer Name");
        list.add("Course Name");
        list.add("Course Level");
        list.add("Course Status");

        //Select
        String sql = "SELECT DISTINCT users.name as \"Trainer Name\", course.name as \"Course Name\", course.course_level as \"Course Level\", course_status.name_status as \"Course Status\" \n" +
                "FROM course INNER JOIN course_status ON course.course_status_id = course_status.id INNER JOIN users ON course.trainer_id = users.role_id WHERE users.user_id =  " + trainerId;

        dataService.getMultiFieldsFromTables(list, sql,"Trainer","table");
        return true;
    }

    public boolean createNewLevelReport(String levelName){

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

        dataService.getMultiFieldsFromTables(list, sql,"Level","table");
        return true;
    }

    public boolean createNewAttendanceReport(Long attendanceId){

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
                "WHERE ( course_status.id = 1 OR course_status.id = 3 ) AND attendance_type.id = " + attendanceId;

        dataService.getMultiFieldsFromTables(list, sql,"Attendance","table");
        return true;
    }
}

package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsSQLQuery {

    @Autowired
    private ReportsService reportsService;

    private static List<String> listWithTrainers = new ArrayList();
    private static List<String> listWithReports = new ArrayList();
    private static List<String> listWithLevels = new ArrayList();

    //Select
    private static final String sqlForTrainers = "SELECT DISTINCT users.name as \"Trainer Name\", course.name as \"Course Name\", course.course_level as \"Course Level\", course_status.name_status as \"Course Status\" \n" +
                                                 "FROM course INNER JOIN course_status ON course.course_status_id = course_status.id INNER JOIN users ON course.trainer_id = users.role_id WHERE users.user_id =  ";

    //Choose by users who is managers or employee that have availability enter to course and course level = to ......
    private static final String sqlForReports = "SELECT DISTINCT users.name as \"User Name\", email as \"Email\", course.name as \"Course Name\", course.course_level as \"Course Level\" \n" +
                                                "FROM users INNER JOIN desired_period ON users.user_id = desired_period.user_id INNER JOIN course ON desired_period.course_id = course.course_id\n" +
                                                "WHERE ( users.role_id = 2 OR users.role_id = 4 ) AND course.course_level = ";

    //Choose by attendance for only active or stopped course where attendance type = to ......
    private static final String sqlForLevels = "SELECT users.name as \"User Name\", Schedule.date_lesson as \"Lesson Date\", lesson.lesson_name as \"Lesson Name\", type, groups.name as \"Group Name\", course.name as \"Course Name\", course_status.name_status as \"Status\"  \n" +
                                               "FROM attendance INNER JOIN users ON attendance.user_id = users.user_id INNER JOIN Schedule ON attendance.schedule_id = Schedule.id \n" +
                                               "LEFT OUTER JOIN attendance_type ON attendance.type_id = attendance_type.id \n" +
                                               "INNER JOIN lesson ON Schedule.lesson_id = lesson.lesson_id LEFT OUTER JOIN groups ON Schedule.group_id = groups.id\n" +
                                               "LEFT OUTER JOIN course ON groups.course_id = course.course_id INNER JOIN course_status ON course.course_status_id = course_status.id\n" +
                                               "WHERE ( course_status.id = 1 OR course_status.id = 3 ) AND attendance_type.id = ";

    public ReportsSQLQuery() {

        listWithTrainers.clear();
        listWithTrainers.add("Trainer Name");
        listWithTrainers.add("Course Name");
        listWithTrainers.add("Course Level");
        listWithTrainers.add("Course Status");

        listWithReports.clear();
        listWithReports.add("User Name");
        listWithReports.add("Role");
        listWithReports.add("Email");
        listWithReports.add("Course Name");
        listWithReports.add("Group Name");

        listWithLevels.clear();
        listWithLevels.add("User Name");
        listWithLevels.add("Lesson Date");
        listWithLevels.add("Lesson Name");
        listWithLevels.add("Group Name");
        listWithLevels.add("Course Name");
        listWithLevels.add("Status");

    }

    public boolean createNewTrainerReport(Long trainerId){

        String sql = sqlForTrainers + trainerId;
        reportsService.getMultiFieldsFromTables(listWithTrainers, sql,"Trainer","table");
        return true;
    }

    public boolean createNewLevelReport(String levelName){

        String sql = sqlForReports + "\'" + levelName + "\'";
        reportsService.getMultiFieldsFromTables(listWithReports, sql,"Level","table");
        return true;
    }

    public boolean createNewAttendanceReport(Long attendanceId){

        String sql = sqlForLevels + attendanceId;
        reportsService.getMultiFieldsFromTables(listWithLevels, sql,"Attendance","table");
        return true;
    }
}

package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsSQLQuery {

    @Autowired
    private ReportsService reportsService;

    private static List<String> listWithTrainers = new ArrayList<>();
    private static List<String> listWithLevels = new ArrayList<>();
    private static List<String> listWithAttendance = new ArrayList<>();

    //Select all trainer to monitor active course in trainer by user_id
    private static final String sqlForTrainers = "SELECT users.name as \"Trainer Name\", users.email as \"Email\", roles.name as \"Role\", course.name as \"Course Name\", course.course_level as \"Course Level\", course_status.name_status as \"Course Status\"  \n" +
                                                 "FROM users INNER JOIN roles ON users.role_id = roles.role_id LEFT OUTER JOIN course ON users.user_id = course.trainer_id LEFT OUTER JOIN course_status ON course.course_status_id = course_status.id \n" +
                                                 "WHERE users.role_id = 3 AND users.user_id = ";


    //Select by users who is managers or employee that have availability enter to the course and course level = to ......
    private static final String sqlForLevels = "SELECT DISTINCT users.name as \"User Name\", email as \"Email\", course.name as \"Course Name\", course.course_level as \"Course Level\" \n" +
                                                "FROM users LEFT OUTER JOIN desired_period ON users.user_id = desired_period.user_id LEFT OUTER JOIN course ON desired_period.course_id = course.course_id\n" +
                                                "WHERE ( users.role_id = 2 OR users.role_id = 4 ) AND course.course_level = ";

    //Select by attendance for only active or stopped course where attendance type = to ......
    private static final String sqlForAttendance = "SELECT users.name as \"User Name\", Schedule.date_lesson as \"Lesson Date\", lesson.lesson_name as \"Lesson Name\", type, groups.name as \"Group Name\", course.name as \"Course Name\", course_status.name_status as \"Status\"  \n" +
                                               "FROM attendance INNER JOIN users ON attendance.user_id = users.user_id INNER JOIN Schedule ON attendance.schedule_id = Schedule.id \n" +
                                               "LEFT OUTER JOIN attendance_type ON attendance.type_id = attendance_type.id \n" +
                                               "INNER JOIN lesson ON Schedule.lesson_id = lesson.lesson_id LEFT OUTER JOIN groups ON Schedule.group_id = groups.id\n" +
                                               "LEFT OUTER JOIN course ON groups.course_id = course.course_id INNER JOIN course_status ON course.course_status_id = course_status.id\n" +
                                               "WHERE ( course_status.id = 1 OR course_status.id = 3 ) AND attendance_type.id = ";

    public ReportsSQLQuery() {
        //According the sql query for Trainers
        listWithTrainers.clear();
        listWithTrainers.add("Trainer Name");
        listWithTrainers.add("Email");
        listWithTrainers.add("Role");
        listWithTrainers.add("Course Name");
        listWithTrainers.add("Course Level");
        listWithTrainers.add("Course Status");

        //According the sql query for Reports
        listWithReports.clear();
        listWithReports.add("User Name");
        listWithReports.add("Role");
        listWithReports.add("Email");
        listWithReports.add("Course Name");
        listWithReports.add("Group Name");

        //According the sql query for Levels
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

        String sql = sqlForLevels + "\'" + levelName + "\'";
        reportsService.getMultiFieldsFromTables(listWithLevels, sql,"Level","table");
        return true;
    }

    public boolean createNewAttendanceReport(Long attendanceId){

        String sql = sqlForAttendance + attendanceId;
        reportsService.getMultiFieldsFromTables(listWithAttendance, sql,"Attendance","table");
        return true;
    }

    private static void setListWithAttendance(){
        //According the sql query for Levels
        listWithAttendance.clear();
        listWithAttendance.add("User Name");
        listWithAttendance.add("Lesson Date");
        listWithAttendance.add("Lesson Name");
        listWithAttendance.add("Group Name");
        listWithAttendance.add("Course Name");
        listWithAttendance.add("Status");
    }

    private static void setListWithLevels(){
        //According the sql query for Reports
        listWithLevels.clear();
        listWithLevels.add("User Name");
        listWithLevels.add("Email");
        listWithLevels.add("Course Name");
        listWithLevels.add("Course Level");
    }

    private static void setListWithTrainers(){
        //According the sql query for Trainers
        listWithTrainers.clear();
        listWithTrainers.add("Trainer Name");
        listWithTrainers.add("Email");
        listWithTrainers.add("Role");
        listWithTrainers.add("Course Name");
        listWithTrainers.add("Course Level");
        listWithTrainers.add("Course Status");
    }
}

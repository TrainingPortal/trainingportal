package trainingportal.reports.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsMapper implements RowMapper<List<Object>> {

    //Select all trainer to monitor active course in trainer by user_id
    public static final String SQL_FOR_TRAINERS =
            "SELECT users.name as \"Trainer Name\", users.email as \"Email\", roles.name as \"Role\", course.name as \"Course Name\", " +
                    "course.course_level as \"Course Level\", course_status.name_status as \"Course Status\"  \n" +
                    "FROM users LEFT OUTER JOIN roles ON users.role_id = roles.role_id LEFT OUTER JOIN course ON users.role_id = course.trainer_id " +
                    "LEFT OUTER JOIN course_status ON course.course_status_id = course_status.id \n" +
                    "WHERE users.role_id = 3 AND users.user_id = ";


    //Choose by users who is managers or employee that have availability enter to course and course level = to ......
    public static final String SQL_FOR_LEVELS =
            "SELECT DISTINCT users.name as \"User Name\", email as \"Email\", course.name as \"Course Name\", " +
                    "course.course_level as \"Course Level\" \n" +
                    "FROM users INNER JOIN desired_period ON users.user_id = desired_period.user_id " +
                    "INNER JOIN course ON desired_period.course_id = course.course_id\n" +
                    "WHERE ( users.role_id = 2 OR users.role_id = 4 ) AND course.course_level = ";

    //Choose by attendance for only active or stopped course where attendance type = to ......
    public static final String SQL_FOR_ATTENDANCE =
            "SELECT users.name as \"User Name\", Schedule.date_lesson as \"Lesson Date\", lesson.lesson_name as \"Lesson Name\", " +
                    "type, groups.name as \"Group Name\", course.name as \"Course Name\", course_status.name_status as \"Status\"  \n" +
                    "FROM attendance INNER JOIN users ON attendance.user_id = users.user_id INNER JOIN Schedule ON attendance.schedule_id = Schedule.id \n" +
                    "LEFT OUTER JOIN attendance_type ON attendance.type_id = attendance_type.id \n" +
                    "INNER JOIN lesson ON Schedule.lesson_id = lesson.lesson_id LEFT OUTER JOIN groups ON Schedule.group_id = groups.id\n" +
                    "LEFT OUTER JOIN course ON groups.course_id = course.course_id INNER JOIN course_status ON course.course_status_id = course_status.id\n" +
                    "WHERE ( course_status.id = 1 OR course_status.id = 3 ) AND attendance_type.id = ";

    private List<String> fields;
    private int fieldNumber;

    @Override
    public List<Object> mapRow(ResultSet resultSet, int i){

        List<Object> localList = new ArrayList();

        try {
            if (resultSet.getObject(fields.get(fieldNumber)) != null){
                Object cName = resultSet.getObject(fields.get(fieldNumber));
                localList.add(cName.toString());
            }else
                localList.add("null");

        } catch (SQLException e) {
            //todo Logger
        }
        return localList;
    }

    public ReportsMapper(List<String> fields, int fieldNumber){
        this.fields = fields;
        this.fieldNumber = fieldNumber;
    }
}

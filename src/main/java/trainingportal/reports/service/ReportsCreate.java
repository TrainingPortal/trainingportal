package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.reports.mapper.ReportsMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsCreate {

    @Autowired
    private ReportsService reportsService;

    private static List<String> listWithTrainers = new ArrayList<>();
    private static List<String> listWithReports = new ArrayList<>();
    private static List<String> listWithLevels = new ArrayList<>();

    public ReportsCreate() {

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

        String sql = ReportsMapper.SQL_FOR_TRAINERS + trainerId;
        reportsService.getMultiFieldsFromTables(listWithTrainers, sql,"Trainer","table");
        return true;
    }

    public boolean createNewLevelReport(String levelName){

        String sql = ReportsMapper.SQL_FOR_REPORTS + "\'" + levelName + "\'";
        reportsService.getMultiFieldsFromTables(listWithReports, sql,"Level","table");
        return true;
    }

    public boolean createNewAttendanceReport(Long attendanceId){

        String sql = ReportsMapper.SQL_FOR_LEVELS + attendanceId;
        reportsService.getMultiFieldsFromTables(listWithLevels, sql,"Attendance","table");
        return true;
    }
}

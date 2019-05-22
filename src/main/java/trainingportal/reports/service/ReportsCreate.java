package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.reports.dao.exception.DataDaoExceptions;
import trainingportal.reports.mapper.ReportsMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsCreate {

    @Autowired
    private ReportsService reportsService;

    @Autowired
    private ReportExport reportExport;

    private static List<String> listWithTrainers = new ArrayList<>();
    private static List<String> listWithLevels = new ArrayList<>();
    private static List<String> listWithAttendance = new ArrayList<>();

    public ReportsCreate() {
        trainerFieldsSetter();
        levelFieldsSetter();
        attendanceFieldsSetter();
    }

    public void createNewTrainerReport(Long trainerId){

        List<List> allColList = reportsService.getMultiFieldsFromTables(listWithTrainers, ReportsMapper.SQL_FOR_TRAINERS + trainerId);

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allColList, listWithTrainers);

        //Use Export For our List<List> formed data
        reportExport.useExport("Trainer", "table", allColWithName);
    }

    public void createNewLevelReport(String levelName){

        List<List> allColList = reportsService.getMultiFieldsFromTables(listWithLevels, ReportsMapper.SQL_FOR_LEVELS + "\'" + levelName + "\'");

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allColList, listWithLevels);

        //Use Export For our List<List> formed data
        reportExport.useExport("Level", "table", allColWithName);
    }

    public void createNewAttendanceReport(Long attendanceId){

        List<List> allColList = reportsService.getMultiFieldsFromTables(listWithAttendance, ReportsMapper.SQL_FOR_ATTENDANCE + attendanceId);

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allColList, listWithAttendance);

        //Use Export For our List<List> formed data
        reportExport.useExport("Attendance", "table", allColWithName);
    }

    private void trainerFieldsSetter(){
        //According the sql query for Trainers
        listWithTrainers.clear();
        listWithTrainers.add("Trainer Name");
        listWithTrainers.add("Email");
        listWithTrainers.add("Role");
        listWithTrainers.add("Course Name");
        listWithTrainers.add("Course Level");
        listWithTrainers.add("Course Status");
    }

    private void levelFieldsSetter(){
        //According the sql query for Reports
        listWithLevels.clear();
        listWithLevels.add("User Name");
        listWithLevels.add("Role");
        listWithLevels.add("Email");
        listWithLevels.add("Course Name");
        listWithLevels.add("Group Name");
    }

    private void attendanceFieldsSetter(){
        //According the sql query for Levels
        listWithAttendance.clear();
        listWithAttendance.add("User Name");
        listWithAttendance.add("Lesson Date");
        listWithAttendance.add("Lesson Name");
        listWithAttendance.add("Group Name");
        listWithAttendance.add("Course Name");
        listWithAttendance.add("Status");
    }

    private List<List> addNameToList(List<List> allCol, List<String> fields) {

        if (!(allCol.isEmpty()) && !(fields.isEmpty())) {

            List<List> finalAllCol = new ArrayList<>();
            List localCol;

            for (int i = 0; i < allCol.size(); i++) {
                localCol = new ArrayList();
                localCol.add(fields.get(i));
                for (int j = 0; j < allCol.get(i).size(); j++) {
                    localCol.add(allCol.get(i).get(j));
                }
                finalAllCol.add(localCol);
            }
            return finalAllCol;
        } else
            throw new DataDaoExceptions("Input list OR list's is Empty");
    }
}

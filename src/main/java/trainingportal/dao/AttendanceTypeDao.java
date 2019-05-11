package trainingportal.dao;

import trainingportal.model.Attendance;

import java.util.List;

public interface AttendanceTypeDao {

    List<Attendance> findAllAttendanceList();
}

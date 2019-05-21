package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;

import java.util.List;

public interface AttendanceTypeDao extends GenericDao<AttendanceType> {

    List<AttendanceType> findAllAttendanceList();
}

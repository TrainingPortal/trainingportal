package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Attendance;

import java.util.List;

public interface AttendanceDao extends GenericDao<Attendance> {
    List<Attendance> findAttendancesByScheduleId(Long scheduleId);
    List<Attendance> getSubordinatesAttendancesByManager(Long managerId);
}

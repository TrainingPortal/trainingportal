package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;

import java.util.List;

public interface AttendanceDao extends GenericDao<Attendance> {
    List<Attendance> getAllAsPage(int page, int total);
}

package trainingportal.service;

import org.springframework.security.core.Authentication;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceForm;
import trainingportal.model.Schedule;
import trainingportal.model.User;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface AttendanceService extends GenericService<Attendance> {
    void saveAll(AttendanceForm attendances);
    List<Attendance> getSubordinatesAttendanceByManager(Long managerId);
    AttendanceForm getAttendanceListWithStudents(Schedule schedule, List<User> users);
}

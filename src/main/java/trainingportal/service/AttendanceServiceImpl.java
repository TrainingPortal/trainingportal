package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import trainingportal.dao.AttendanceDao;
import trainingportal.model.*;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.Collections;
import java.util.List;

@Repository
public class AttendanceServiceImpl extends GenericServiceImpl<Attendance> implements AttendanceService {

    private final AttendanceDao attendanceDao;
    private final UserService userService;
    private final AttendanceTypeService attendanceTypeService;
    private final ScheduleService scheduleService;

    @Autowired
    public AttendanceServiceImpl(AttendanceDao attendanceDao, UserService userService, AttendanceTypeService attendanceTypeService, ScheduleService scheduleService) {
        this.attendanceDao = attendanceDao;
        this.userService = userService;
        this.attendanceTypeService = attendanceTypeService;
        this.scheduleService = scheduleService;
    }

    @Override
    public void saveAll(AttendanceForm attendances) {
        for (Attendance attendance:attendances.getAttendances()) {
            save(attendance);
        }
    }

    @Override
    public List<Attendance> getSubordinatesAttendanceByManager(Long managerId) {
        List<Attendance> attendances = attendanceDao.getSubordinatesAttendancesByManager(managerId);
        for (Attendance attendance:
                attendances) {
            attendance.setUser(userService.findById(attendance.getUserId()));
            attendance.setAttendanceType(attendanceTypeService.findById(attendance.getAttendanceId()));
            attendance.setSchedule(scheduleService.findById(attendance.getScheduleId()));
        }

        return attendances;
    }

    @Override
    public AttendanceForm getAttendanceListWithStudents(Schedule schedule, List<User> users) {
        List<Attendance> attendances = Collections.emptyList();
        for (int i = 0; i < users.size(); i++) {
            Attendance attendance = new Attendance();
            attendance.setScheduleId(schedule.getScheduleId());
            attendance.setUser(users.get(i));
            attendance.setUserId(users.get(i).getUserId());
            attendances.add(attendance);
        }

        AttendanceForm attendanceForm = new AttendanceForm();
        attendanceForm.setAttendances(attendances);

        return attendanceForm;
    }
}

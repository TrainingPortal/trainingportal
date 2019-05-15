package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.AttendanceTypeDao;
import trainingportal.model.Attendance;

import java.util.List;

@Service
public class AttendanceTypeImpl implements trainingportal.service.AttendanceType {

    @Autowired
    AttendanceTypeDao attendanceTypeDao;

    @Override
    public List<Attendance> getAllAttendanceList() {
        List<Attendance> allType = attendanceTypeDao.findAllAttendanceList();
        return allType;
    }
}

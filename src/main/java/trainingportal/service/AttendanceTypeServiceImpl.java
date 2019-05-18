package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.AttendanceTypeDao;
import trainingportal.model.AttendanceType;

import java.util.List;

@Service
public class AttendanceTypeServiceImpl implements AttendanceTypeService {

    @Autowired
    private AttendanceTypeDao attendanceTypeDao;

    @Override
    public List<AttendanceType> getAllAttendanceList() {
        List<AttendanceType> allType = attendanceTypeDao.findAllAttendanceList();
        return allType;
    }
}

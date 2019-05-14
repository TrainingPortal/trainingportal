package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.AttendanceTypeDaoImpl;
import trainingportal.model.AttendanceType;

import java.util.List;

@Service
public class AttendanceTypeServiceImpl implements AttendanceTypeService {

    @Autowired
    AttendanceTypeDaoImpl attendanceTypeDao;

    @Override
    public List<AttendanceType> getAllAttendanceList() {
        List<AttendanceType> allType = attendanceTypeDao.findAllAttendanceList();
        return allType;
    }
}

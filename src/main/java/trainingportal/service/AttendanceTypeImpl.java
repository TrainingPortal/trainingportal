package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.AttendanceTypeDaoImpl;

import java.util.List;

@Service
public class AttendanceTypeImpl implements AttendanceType {

    @Autowired
    AttendanceTypeDaoImpl attendanceTypeDao;

    @Override
    public List<String> getAllReasonsList() {
        List<String> allType = attendanceTypeDao.findAllReasonsList();
        return allType;
    }
}

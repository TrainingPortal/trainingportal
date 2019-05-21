package trainingportal.service;

import trainingportal.model.AttendanceType;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface AttendanceTypeService extends GenericService<AttendanceType> {

    List<AttendanceType> getAllAttendanceList();
}

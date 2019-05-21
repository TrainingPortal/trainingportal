package trainingportal.model;

public class AttendanceType {
    private Long attendanceTypeId;
    private String type;

    public AttendanceType(Long attendanceTypeId, String type) {
        this.attendanceTypeId = attendanceTypeId;
        this.type = type;
    }

    public Long getAttendanceTypeId() {
        return attendanceTypeId;
    }

    public void setAttendanceTypeId(Long attendanceTypeId) {
        this.attendanceTypeId = attendanceTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

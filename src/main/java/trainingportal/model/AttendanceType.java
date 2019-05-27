package trainingportal.model;

public class AttendanceType {
    private Long attendanceId;
    private String attendanceType;

    public AttendanceType(Long attendanceId, String attendanceType) {
        this.attendanceId = attendanceId;
        this.attendanceType = attendanceType;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }
}

package trainingportal.model;

public class Attendance {

    private int attendanceId;
    private String attendanceType;

    public Attendance(int attendanceId, String attendanceType) {
        this.attendanceId = attendanceId;
        this.attendanceType = attendanceType;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }
}

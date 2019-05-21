package trainingportal.model;

public class Attendance {

    private Long attendanceId;
    private User user;
    private Long userId;
    private Long attendanceTypeId;
    private AttendanceType attendanceType;
    private Schedule schedule;
    private Long scheduleId;
    public Attendance(){

    }

    public Attendance(Long attendanceId, Long userId, Long attendanceTypeId, Long scheduleId) {
        this.attendanceId = attendanceId;
        this.userId = userId;
        this.attendanceTypeId = attendanceTypeId;
        this.scheduleId = scheduleId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAttendanceTypeId() {
        return attendanceTypeId;
    }

    public void setAttendanceTypeId(Long attendanceTypeId) {
        this.attendanceTypeId = attendanceTypeId;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }
}
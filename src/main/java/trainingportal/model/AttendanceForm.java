package trainingportal.model;

import java.util.List;

public class AttendanceForm {
    private List<Attendance> attendance;

    public List<Attendance> getAttendances() {
        return attendance;
    }

    public void setAttendances(List<Attendance> attendance) {
        this.attendance = attendance;
    }
}
package trainingportal.model;

import java.sql.Date;

public class Schedule {
    private Long sheduleId;
    private Long sheduleGroupId;
    private Date sheduleDate;
    private Long sheduleLessonId;

    public Schedule() {
    }

    public Schedule(Long sheduleId, Long sheduleGroupId, Date sheduleDate, Long sheduleLessonId) {
        this.sheduleId = sheduleId;
        this.sheduleGroupId = sheduleGroupId;
        this.sheduleDate = sheduleDate;
        this.sheduleLessonId = sheduleLessonId;
    }

    public Long getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(Long sheduleId) {
        this.sheduleId = sheduleId;
    }

    public Long getSheduleGroupId() {
        return sheduleGroupId;
    }

    public void setSheduleGroupId(Long sheduleGroupId) {
        this.sheduleGroupId = sheduleGroupId;
    }

    public Date getSheduleDate() {
        return sheduleDate;
    }

    public void setSheduleDate(Date sheduleDate) {
        this.sheduleDate = sheduleDate;
    }

    public Long getSheduleLessonId() {
        return sheduleLessonId;
    }

    public void setSheduleLessonId(Long sheduleLessonId) {
        this.sheduleLessonId = sheduleLessonId;
    }
}

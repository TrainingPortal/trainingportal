package trainingportal.model;

import java.sql.Date;

public class Schedule {
    private Long scheduleId;
    private Long scheduleGroupId;
    private Group scheduleGroup;
    private Date scheduleDate;
    private Long scheduleLessonId;

    public Schedule() {
    }

    public Schedule(Long scheduleId, Long scheduleGroupId, Date scheduleDate, Long scheduleLessonId) {
        this.scheduleId = scheduleId;
        this.scheduleGroupId = scheduleGroupId;
        this.scheduleDate = scheduleDate;
        this.scheduleLessonId = scheduleLessonId;
    }

    public Group getScheduleGroup() {
        return scheduleGroup;
    }

    public void setScheduleGroup(Group scheduleGroup) {
        this.scheduleGroup = scheduleGroup;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleGroupId() {
        return scheduleGroupId;
    }

    public void setScheduleGroupId(Long scheduleGroupId) {
        this.scheduleGroupId = scheduleGroupId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Long getScheduleLessonId() {
        return scheduleLessonId;
    }

    public void setScheduleLessonId(Long scheduleLessonId) {
        this.scheduleLessonId = scheduleLessonId;
    }
}

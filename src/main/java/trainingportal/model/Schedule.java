package trainingportal.model;

import java.sql.Date;

public class Schedule {
    private Long scheduleId;
    private Group group;
    private Long groupId;
    private Date date;
    private Lesson lesson;
    private Long lessonId;

    public Schedule(Long scheduleId, Long groupId, Date date, Long lessonId) {
        this.scheduleId = scheduleId;
        this.groupId = groupId;
        this.date = date;
        this.lessonId = lessonId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }
}

package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Lesson {

    @NotNull
    private Long lessonId;
    @Size(min = 1, max = 40)
    @NotNull
    private String lessonName;
    @Size(min = 1, max = 30)
    private String lessonDescription;
    @Size(min = 10, max = 120)
    @NotNull
    private double lessonDuration;
    @NotNull
    private String lessonDate;
    @NotNull
    private long homeworkId;
    @Size(min = 1, max = 30)
    @NotNull
    private long groupId;

    @Size(min = 1, max = 30)

    public Lesson() {
    }

    public Lesson(@NotNull Long lessonId, @Size(min = 1, max = 40) @NotNull String lessonName, @Size(min = 1, max = 30) String lessonDescription, @Size(min = 10, max = 90) @NotNull double lessonDuration, @NotNull String lessonDate, @NotNull long homeworkId, @Size(min = 1, max = 30) @NotNull long groupId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonDuration = lessonDuration;
        this.lessonDate = lessonDate;
        this.homeworkId = homeworkId;
        this.groupId = groupId;
    }

    public void setId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public double getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(double lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
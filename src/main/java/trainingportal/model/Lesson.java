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
    private Double lessonDuration;
    @NotNull
    private String lessonDate;
    @NotNull
    private Long homeworkId;
    @Size(min = 1, max = 30)

    @NotNull
    private Long groupId;

    public Lesson() {
    }

    public Lesson(@NotNull Long lessonId, @Size(min = 1, max = 40) @NotNull String lessonName, @Size(min = 1, max = 30) String lessonDescription, @Size(min = 10, max = 120) @NotNull Double lessonDuration, @NotNull String lessonDate, @NotNull Long homeworkId, @Size(min = 1, max = 30) @NotNull Long groupId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonDuration = lessonDuration;
        this.lessonDate = lessonDate;
        this.homeworkId = homeworkId;
        this.groupId = groupId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
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

    public Double getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(Double lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
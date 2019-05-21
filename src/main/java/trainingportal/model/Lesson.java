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
    private Long courseId;
    @NotNull
    private int lessonNumber;
    public Lesson() {}


    public Lesson(Long lessonId, String lessonName, String lessonDescription, Double lessonDuration, Long homeworkId, Long courseId, int lessonNumber) {
    }

    public Lesson(@NotNull Long lessonId, @Size(min = 1, max = 40) @NotNull String lessonName, @Size(min = 1, max = 30) String lessonDescription, @Size(min = 10, max = 120) @NotNull Double lessonDuration, @NotNull Long courseId, @NotNull int lessonNumber) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonDuration = lessonDuration;
        this.courseId = courseId;
        this.lessonNumber = lessonNumber;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

}
package trainingportal.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Course {

    private Long courseId;

    @Size(min = 1, max = 70, message = "Please enter course name")
    private String courseName;

    @NotEmpty(message = "Please enter courseLevel")
    private String courseLevel;

    @NotNull
    private Long courseStatus;

    @NotNull
    private int minNumber;

    @NotNull
    private int maxNumber;

    private String description;

    private Long trainerId;

    private User trainer;

    private CourseStatus status;

    public Course() {
    }

    public Course(Long courseId, @Size(min = 1, max = 70, message = "Please enter course name") String courseName, @NotEmpty(message = "Please enter courseLevel") String courseLevel, @NotNull Long courseStatus, @NotNull int minNumber, @NotNull int maxNumber, String description, Long trainerId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseStatus = courseStatus;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.description = description;
        this.trainerId = trainerId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Long getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Long courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}

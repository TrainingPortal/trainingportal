package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Course {

    private Long id;
    @Size(min = 1, max = 40)
    @NotNull
    private String courseName;
    @Size(min = 1, max = 30)
    @NotNull
    private String courseLevel;
    @Size(min = 2, max = 10)
    @NotNull
    private String courseStatus;
    @NotNull
    private String dateStart;
    @NotNull
    private String dateEnd;
    @NotNull
    private int groupNumber;
    @NotNull
    private int minNumber;
    @Size(min = 1, max = 100)
    @NotNull
    private String description;
    @Size(min = 1, max = 30)
    @NotNull
    private String trainer;

    public Course(Long id, @Size(min = 1, max = 40) @NotNull String courseName, @Size(min = 1, max = 30) @NotNull String courseLevel, @Size(min = 2, max = 10) @NotNull String courseStatus, @NotNull String dateStart, @NotNull String dateEnd, @NotNull int groupNumber, @NotNull int minNumber, @Size(min = 1, max = 100) @NotNull String description, @Size(min = 1, max = 30) @NotNull String trainer) {
        this.id = id;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseStatus = courseStatus;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.groupNumber = groupNumber;
        this.minNumber = minNumber;
        this.description = description;
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}

package trainingportal.model;

import javax.validation.constraints.Size;

public class Course {

    private Long courseId;

    @Size(min = 1, max = 40, message = "Please enter course name")
    private String courseName;

    @Size(min = 1, max = 30, message = "Please enter course level")
    private String courseLevel;

    @Size(min = 1, max = 30, message = "Please enter course status")
    private String courseStatus;

    @Size(min = 1, max = 30, message = "Please enter date start")
    private String dateStart;

    @Size(min = 1, max = 30, message = "Please enter date end")
    private String dateEnd;

    @Size(min = 1, max = 10000000, message = "Please enter group number")
    private int groupNumber;

    @Size(min = 1, max = 1000000000, message = "Please enter min number")
    private int minNumber;

    private String description;

    @Size(min = 1, max = 1000000000, message = "Please enter Trainer")
    private String courseTrainer;

    public Course() {
    }

    public Course(Long courseId, String courseName, String courseLevel, String courseStatus, String dateStart, String dateEnd, int groupNumber, int minNumber, String description, String courseTrainer) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseStatus = courseStatus;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.groupNumber = groupNumber;
        this.minNumber = minNumber;
        this.description = description;
        this.courseTrainer = courseTrainer;
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

    public String getCourseTrainer() {
        return courseTrainer;
    }

    public void setCourseTrainer(String courseTrainer) {
        this.courseTrainer = courseTrainer;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseLevel='" + courseLevel + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", groupNumber=" + groupNumber +
                ", minNumber=" + minNumber +
                ", description='" + description + '\'' +
                ", courseTrainer='" + courseTrainer + '\'' +
                '}';
    }
}

package trainingportal.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Course {
    @NotNull
    private Long id;
    @Size(min = 1, max = 40)
    @NotEmpty(message = "enter course name")
    private String name;
    @Size(min = 1, max = 30)
    @NotEmpty(message = "enter course level")
    private String course_level;
    @Size(min = 2, max = 10)
    @NotEmpty(message = "enter status of course open/closed")
    private String status;
    @NotEmpty(message = "enter date when course start")
    private String date_start;
    @NotEmpty(message = "enter date when course end")
    private String date_end;
    @NotEmpty(message = "enter group number")
    private Long group_number;
    @NotEmpty(message = "minimum number of people is 1")
    private Long min_number;
    @Size(min = 1, max = 100)
    @NotEmpty(message = "describe course if you want to")
    private String description;
    @Size(min = 1, max = 30)
    @NotEmpty(message = "enter trainer name")
    private String trainer;

    public Course() {
    }

    public Course(@NotNull Long id, @Size(min = 1, max = 40) @NotEmpty(message = "enter course name") String name, @Size(min = 1, max = 30) @NotEmpty(message = "enter course level") String course_level, @Size(min = 2, max = 10) @NotEmpty(message = "enter status of course open/closed") String status, @NotEmpty(message = "enter date when course start") String date_start, @NotEmpty(message = "enter date when course end") String date_end, @NotEmpty(message = "enter group number") Long group_number, @NotEmpty(message = "minimum number of people is 1") Long min_number, @Size(min = 1, max = 100) @NotEmpty(message = "describe course if you want to") String description, @Size(min = 1, max = 30) @NotEmpty(message = "enter trainer name") String trainer) {
        this.id = id;
        this.name = name;
        this.course_level = course_level;
        this.status = status;
        this.date_start = date_start;
        this.date_end = date_end;
        this.group_number = group_number;
        this.min_number = min_number;
        this.description = description;
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_level() {
        return course_level;
    }

    public void setCourse_level(String course_level) {
        this.course_level = course_level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public Long getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Long group_number) {
        this.group_number = group_number;
    }

    public Long getMin_number() {
        return min_number;
    }

    public void setMin_number(Long min_number) {
        this.min_number = min_number;
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
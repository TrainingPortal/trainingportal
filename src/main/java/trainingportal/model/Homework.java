package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class Homework {
    @NotNull
    private Long homeworkId;

    @Size(min = 1, max = 40)
    @NotNull
    private String homeworkName;

    @Size(min = 1, max = 40)
    @NotNull
    private Date homeworkDeadlineDate;

    public Homework() {
    }

    public Homework(@NotNull Long homeworkId, @Size(min = 1, max = 40) @NotNull String homeworkName, @Size(min = 1, max = 40) @NotNull Date homeworkDeadlineDate) {
        this.homeworkId = homeworkId;
        this.homeworkName = homeworkName;
        this.homeworkDeadlineDate = homeworkDeadlineDate;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public Date getHomeworkDeadlineDate() {
        return homeworkDeadlineDate;
    }

    public void setHomeworkDeadlineDate(Date homeworkDeadlineDate) {
        this.homeworkDeadlineDate = homeworkDeadlineDate;
    }
}

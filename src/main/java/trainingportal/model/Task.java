package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Task {
    @NotNull
    private Long taskId;
    @Size(min = 1, max = 40)
    @NotNull
    private String taskDescription;
    @Size(min = 1, max = 500)
    private long homeworkId;

    @Size(min = 1, max = 40)

    public Task(@NotNull Long taskId, @Size(min = 1, max = 40) @NotNull String taskDescription, @Size(min = 1, max = 500) long homeworkId) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.homeworkId = homeworkId;
    }

    @Size(min = 10, max = 220)
    @NotNull


    public Task() {

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }
}

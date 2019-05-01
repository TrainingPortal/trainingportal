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
    private Long homeworkId;

    public Task() {

    }

    public Task(@NotNull Long taskId, @Size(min = 1, max = 40) @NotNull String taskDescription, @Size(min = 1, max = 500) Long homeworkId) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.homeworkId = homeworkId;
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

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }
}

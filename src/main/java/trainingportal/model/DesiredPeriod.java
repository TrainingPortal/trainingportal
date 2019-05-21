package trainingportal.model;

import javax.validation.constraints.NotNull;


public class DesiredPeriod {
    @NotNull
    private Long desiredPeriodId;
    @NotNull
    private Long userId;
    @NotNull
    private Long courseId;

    public DesiredPeriod() {
    }

    public DesiredPeriod(@NotNull Long desiredPeriodId, @NotNull Long userId, @NotNull Long courseId) {
        this.desiredPeriodId = desiredPeriodId;
        this.userId = userId;
        this.courseId = courseId;
    }

    public Long getDesiredPeriodId() {
        return desiredPeriodId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setDesiredPeriodId(Long desiredPeriodId) {
        this.desiredPeriodId = desiredPeriodId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

}

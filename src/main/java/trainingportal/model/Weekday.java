package trainingportal.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Weekday {
    @NotNull
    private Long weekdayId;

    @NotNull
    private String dayName;

    @NotNull
    private Date timeStart;

    @NotNull
    private Date timeEnd;

    @NotNull
    private Long desiredPeriodId;

    public Weekday() {
    }

    public Weekday(@NotNull Long weekdayId, @NotNull String dayName, @NotNull Date timeStart, @NotNull Date timeEnd, @NotNull Long desiredPeriodId) {
        this.weekdayId = weekdayId;
        this.dayName = dayName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.desiredPeriodId = desiredPeriodId;
    }

    public Long getWeekdayId() {
        return weekdayId;
    }

    public String getDayName() {
        return dayName;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public Long getPeriodId() {
        return desiredPeriodId;
    }

    public void setWeekdayId(Long weekdayId) {
        this.weekdayId = weekdayId;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setPeriodId(Long desiredPeriodId) {
        this.desiredPeriodId = desiredPeriodId;
    }
}
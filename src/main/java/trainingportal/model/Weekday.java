package trainingportal.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Weekday {
    @NotNull
    private Long weekdayId;

    @NotNull
    private String dayName;

    @NotNull
    private String timeStart;

    @NotNull
    private String timeEnd;

    @NotNull
    private Long periodId;

    public Weekday() {
    }

    public Weekday(@NotNull Long weekdayId, @NotNull String dayName, @NotNull String timeStart, @NotNull String timeEnd, @NotNull Long periodId) {
        this.weekdayId = weekdayId;
        this.dayName = dayName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.periodId = periodId;
    }

    public Long getWeekdayId() {
        return weekdayId;
    }

    public String getDayName() {
        return dayName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setWeekdayId(Long weekdayId) {
        this.weekdayId = weekdayId;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }
}
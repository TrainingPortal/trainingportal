package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Weekday {
    @NotNull
    private Long weekdayId;

    @NotNull
    private String dayName;
    @Size(min = 5, max = 25)

    @NotNull
    private String timeStart;
    @Size(min = 8, max = 15)

    @NotNull
    private String timeEnd;
    @Size(min = 8, max = 15)

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
package trainingportal.model;

public class InfoDesk {
    private Long infoDeskId;
    private Long employeeId;
    private String infoDeskDescription;
    private Long infoDeskStatusId;

    public InfoDesk() {
    }

    public InfoDesk(Long infoDeskId, Long employeeId, String infoDeskDescription, Long infoDeskStatusId) {
        this.infoDeskId = infoDeskId;
        this.employeeId = employeeId;
        this.infoDeskDescription = infoDeskDescription;
        this.infoDeskStatusId = infoDeskStatusId;
    }

    public Long getInfoDeskId() {
        return infoDeskId;
    }

    public void setInfoDeskId(Long infoDeskId) {
        this.infoDeskId = infoDeskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getInfoDeskDescription() {
        return infoDeskDescription;
    }

    public void setInfoDeskDescription(String infoDeskDescription) {
        this.infoDeskDescription = infoDeskDescription;
    }

    public Long getInfoDeskStatusId() {
        return infoDeskStatusId;
    }

    public void setInfoDeskStatusId(Long infoDeskStatusId) {
        this.infoDeskStatusId = infoDeskStatusId;
    }
}

package trainingportal.model;

public class InfoDesk {
    private Long infoDeskId;
    private Long employeeId;
    private String infoDeskDescription;
    private Long infoDeskStatusId;
    private User senderName;
    private String sender;
    private QuestionStatus questionStatus;

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

    public User getSenderName() {
        return senderName;
    }

    public void setSenderName(User senderName) {
        this.senderName = senderName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

    @Override
    public String toString() {
        return "InfoDeskService{" +
                "infoDeskId=" + infoDeskId +
                ", employeeId=" + employeeId +
                ", infoDeskDescription='" + infoDeskDescription + '\'' +
                ", infoDeskStatusId=" + infoDeskStatusId +
                ", senderName=" + senderName +
                ", sender='" + sender + '\'' +
                ", questionStatus=" + questionStatus +
                '}';
    }

}

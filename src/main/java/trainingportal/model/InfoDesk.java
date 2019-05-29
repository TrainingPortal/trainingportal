package trainingportal.model;

public class InfoDesk {
    private Long infoDeskId;
    private Long employeeId;
    private String infoDeskQuestion;
    private String infoDeskAnswer;
    private Long infoDeskStatusId;
    private User senderName;
    private QuestionStatus questionStatus;

    public InfoDesk() {
    }

    public InfoDesk(Long infoDeskId, Long employeeId, String infoDeskQuestion, String infoDeskAnswer, Long infoDeskStatusId) {
        this.infoDeskId = infoDeskId;
        this.employeeId = employeeId;
        this.infoDeskQuestion = infoDeskQuestion;
        this.infoDeskAnswer = infoDeskAnswer;
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

    public String getInfoDeskQuestion() {
        return infoDeskQuestion;
    }

    public void setInfoDeskQuestion(String infoDeskQuestion) {
        this.infoDeskQuestion = infoDeskQuestion;
    }

    public String getInfoDeskAnswer() {
        return infoDeskAnswer;
    }

    public void setInfoDeskAnswer(String infoDeskAnswer) {
        this.infoDeskAnswer = infoDeskAnswer;
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

    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

}

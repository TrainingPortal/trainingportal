package trainingportal.model;

public class QuestionStatus {
   private Long questionId;
   private String questionStatusName;

    public QuestionStatus() {
    }

    public QuestionStatus(Long questionId, String questionStatusName) {
        this.questionId = questionId;
        this.questionStatusName = questionStatusName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionStatusName() {
        return questionStatusName;
    }

    public void setQuestionStatusName(String questionStatusName) {
        this.questionStatusName = questionStatusName;
    }
}

package trainingportal.model;

public class MainCardModel {

    private Long mainCardId;

    private String filesName;

    private String filesType;

    private byte[] filesData;

    private String cardTitle;

    private String cardText;

    private String buttonName;

    private String cardUrl;

    private String filesDataString;

    protected MainCardModel() {
    }

    public MainCardModel(Long mainCardId, String filesName, String filesType, byte[] filesData, String cardTitle, String cardText, String buttonName, String cardUrl) {
        this.mainCardId = mainCardId;
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
        this.cardTitle = cardTitle;
        this.cardText = cardText;
        this.buttonName = buttonName;
        this.cardUrl = cardUrl;
    }

    public Long getMainCardId() {
        return mainCardId;
    }

    public void setMainCardId(Long mainCardId){
        this.mainCardId = mainCardId;
    }

    public String getFilesName() {
        return filesName;
    }

    public void setFilesName(String filesName) {
        this.filesName = filesName;
    }

    public String getFilesType() {
        return filesType;
    }

    public void setFilesType(String filesType) {
        this.filesType = filesType;
    }

    public byte[] getFilesData(){
        return filesData;
    }

    public void setFilesData(byte[] filesData){
        this.filesData = filesData;
    }

    public String getCardTitle(){
        return cardTitle;
    }

    public void setCardTitle(){
        this.cardTitle = cardTitle;
    }

    public String getCardText(){
        return cardText;
    }

    public void setCardText(){
        this.cardTitle = cardText;
    }

    public String getButtonName(){
        return buttonName;
    }

    public void setButtonName(String buttonName){
        this.buttonName = buttonName;
    }

    public String getCardUrl(){
        return cardUrl;
    }

    public void setCardUrl(String buttonUrl){
        this.cardUrl = cardUrl;
    }

    public void setFilesDataString(String filesDataString){
        this.filesDataString = filesDataString;
    }

    public String getFilesDataString(){
        return filesDataString;
    }

}

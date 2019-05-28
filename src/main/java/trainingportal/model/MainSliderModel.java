package trainingportal.model;

public class MainSliderModel {

    private Long mainSliderId;

    private String filesName;

    private String filesType;

    private byte[] filesData;

    private String buttonName;

    private String buttonUrl;

    private String captionHeader;

    private String captionText;

    private int slideInterval;

    private String filesDataString;

    protected MainSliderModel() {
    }

    public MainSliderModel(Long mainSliderId, String filesName, String filesType, byte[] filesData, String buttonName,
                           String buttonUrl, String captionHeader, String captionText, int slideInterval) {
        this.mainSliderId = mainSliderId;
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
        this.buttonName = buttonName;
        this.buttonUrl = buttonUrl;
        this.captionHeader = captionHeader;
        this.captionText = captionText;
        this.slideInterval = slideInterval;
    }

    public Long getMainSliderId() {
        return mainSliderId;
    }

    /*public void setFilesId(int mainSliderId) {
        this.mainSliderId = mainSliderId;
    }*/

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

    public String getButtonName(){
        return buttonName;
    }

    public void setButtonName(String buttonName){
        this.buttonName = buttonName;
    }

    public String getButtonUrl(){
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl){
        this.buttonUrl = buttonUrl;
    }

    public void setFilesDataString(String filesDataString){
        this.filesDataString = filesDataString;
    }

    public String getFilesDataString(){
        return filesDataString;
    }

    public String getCaptionHeader(){
        return captionHeader;
    }

    public void setCaptionHeader(String captionHeader){
        this.captionHeader = captionHeader;
    }

    public String getCaptionText(){
        return captionText;
    }

    public void setCaptionText(String captionText){
        this.captionText = captionText;
    }

    public int getSlideInterval(){
        return slideInterval;
    }

    public void setSlideInterval(int slideInterval){
        this.slideInterval = slideInterval;
    }

}

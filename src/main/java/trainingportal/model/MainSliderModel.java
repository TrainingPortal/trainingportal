package trainingportal.model;

//@Entity
//@Table(name = "main_slider")
public class MainSliderModel {

//    @Id
//    @GeneratedValue
//    @Column(name = "main_slider_id")
    private Long mainSliderId;
//    @Column(name = "files_name")
    private String filesName;
//    @Column(name = "files_type")
    private String filesType;
//    @Lob
//    @Column(name = "files_data")
    private byte[] filesData;
//    @Column(name = "button_name")
    private String buttonName;
//    @Column (name = "button_url")
    private String buttonUrl;

    private String filesDataString;

    protected MainSliderModel() {
    }

    public MainSliderModel(Long mainSliderId, String filesName, String filesType, byte[] filesData, String buttonName, String buttonUrl) {
        this.mainSliderId = mainSliderId;
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
        this.buttonName = buttonName;
        this.buttonUrl = buttonUrl;
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

}

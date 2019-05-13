package trainingportal.model;

import javax.persistence.*;

@Entity
@Table(name = "main_cards")
public class MainCardModel {

    @Id
    @GeneratedValue
    @Column(name = "main_card_id")
    private Long mainCardId;
    @Column(name = "files_name")
    private String filesName;
    @Column(name = "files_type")
    private String filesType;
    @Lob
    @Column(name = "files_data")
    private byte[] filesData;
    @Column(name = "card_title")
    private String cardTitle;
    @Column(name = "card_text")
    private String cardText;
    @Column(name = "button_name")
    private String buttonName;
    @Column (name = "card_url")
    private String cardUrl;

    private String filesDataString;

    protected MainCardModel() {
    }

    public MainCardModel(String filesName, String filesType, byte[] filesData, String cardTitle, String cardText, String buttonName, String cardUrl) {
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
        this.cardTitle = cardTitle;
        this.cardText = cardText;
        this.buttonName = buttonName;
        this.cardUrl = cardUrl;
    }

    public Long getMainSliderId() {
        return mainCardId;
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

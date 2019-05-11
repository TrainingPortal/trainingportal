package trainingportal.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FilesModel {

    @Id
    @Column
    private String filesId;
    @Column
    private String filesName;
    @Column
    private String filesType;
    @Lob
    @Column
    private byte[] filesData;

    protected FilesModel() {
    }

    public FilesModel(String filesName, String filesType, byte[] filesData) {
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
    }

    public String getFilesId() {
        return filesId;
    }

    /*public void setFilesId(int filesId) {
        this.filesId = filesId;
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

}

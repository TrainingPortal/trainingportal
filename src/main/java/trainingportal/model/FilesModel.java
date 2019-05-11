package trainingportal.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FilesModel {

    @Id
    @GeneratedValue
    @Column(name = "files_id")
    private Long filesId;
    @Column(name = "files_name")
    private String filesName;
    @Column(name = "files_type")
    private String filesType;
    @Lob
    @Column(name = "files_data")
    private byte[] filesData;

    protected FilesModel() {
    }

    public FilesModel(String filesName, String filesType, byte[] filesData) {
        this.filesName = filesName;
        this.filesType = filesType;
        this.filesData = filesData;
    }

    public Long getFilesId() {
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

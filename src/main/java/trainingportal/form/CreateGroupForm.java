package trainingportal.form;

/**
 *
 * @author Vitalii Chernetskyi
 */
public class CreateGroupForm {
     
    private String groupName;
    private int groupCapacity;
  
    public CreateGroupForm() {
  
    }
  
    public CreateGroupForm(String groupName, int groupCapacity) {
        this.groupName = groupName;
        this.groupCapacity = groupCapacity;
    }
  
    public String getGroupName() {
        return groupName;
    }
  
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
  
    public int getGroupCapacity() {
        return groupCapacity;
    }
  
    public void setGroupCapacity(int groupCapacity) {
        this.groupCapacity = groupCapacity;
    }
  
     
}

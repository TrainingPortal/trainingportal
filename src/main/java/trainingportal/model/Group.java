package trainingportal.model;

/**
 *
 * @author Vitalii Chernetskyi
 */
public class Group {
    
    private int id;
    private String name;
    private int trainer_id;
    private int capacity;
    
    public Group(int id, String name, int trainer_id, int capacity){
        super();
        this.id = id;
        this.name = name;
        this.trainer_id = trainer_id;
        this.capacity = capacity;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getTrainer_id(){
        return trainer_id;
    }
    
    public void setTrainer_id(int trainer_id){
        this.trainer_id = trainer_id;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
}

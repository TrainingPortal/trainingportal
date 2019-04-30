package trainingportal.exceltest.model;

import java.util.ArrayList;

public class Trainer {

    private ArrayList<String> courseName;

    public ArrayList<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(ArrayList<String> courseName) {
        this.courseName = courseName;
    }

    public Trainer(){}

    public Trainer(ArrayList<String> courseName) {
        this.courseName = courseName;
    }

}

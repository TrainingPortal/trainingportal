package trainingportal.reports.service;

import org.springframework.stereotype.Service;
import trainingportal.model.Course;

import java.util.ArrayList;
import java.util.List;

@Service
public class Filter {

    private List<String> repeatedList = new ArrayList();
    private List<Boolean> listOfTrue = new ArrayList();
    private List<String> allCourseList = new ArrayList();

    public List<String> getFilteredAllCoureList(List<Course> courseLevels){

        clearAllLists();

        //Select all uniq course name and set it in allCourse
        if (!courseLevels.isEmpty()) {
            for (Course courseLevel : courseLevels) {
                if (!repeatedList.isEmpty()) {
                    for (String s : repeatedList) {
                        if (courseLevel.getCourseLevel().equals(s)) {
                            listOfTrue.add(true);
                        }
                    }
                    if (listOfTrue.isEmpty()) {
                        allCourseList.add(courseLevel.getCourseLevel());
                        repeatedList.add(courseLevel.getCourseLevel());
                    } else
                        listOfTrue.clear();
                } else {
                    allCourseList.add(courseLevel.getCourseLevel());
                    repeatedList.add(courseLevel.getCourseLevel());
                }
            }
        } else
            throw new NullPointerException();

        return allCourseList;
    }

    private void clearAllLists(){
        repeatedList.clear();
        listOfTrue.clear();
        allCourseList.clear();
    }

}

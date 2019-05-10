package trainingportal.universalexportcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.CourseService;
import trainingportal.service.UserService;
import trainingportal.universalexportcreator.download.Download;
import trainingportal.universalexportcreator.service.DataServiceImpl;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    public DataServiceImpl dataService;

    @Autowired
    Download download;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "trainer/trainerCourses", method = RequestMethod.GET)
    public ModelAndView downloadTrainer(@NotNull ModelAndView model){

        List<User> trainers = userService.findAllEnabledByRole(Role.TRAINER);

        List<Course> courseLevels = courseService.findAll();

        List allCourse = new ArrayList();
        List repeated = new ArrayList();
        List truee = new ArrayList();

        //Select all uniq course name and set it in allCourse
        if (!courseLevels.isEmpty()) {
            for (Course courseLevel : courseLevels) {
                if (!repeated.isEmpty()) {
                    for (int i = 0; i < repeated.size(); i++) {
                        if (courseLevel.getCourseLevel().equals(repeated.get(i))) {
                           truee.add(new Boolean(true));
                        }
                    }
                    if (truee.isEmpty()){
                        allCourse.add(courseLevel.getCourseLevel());
                        repeated.add(courseLevel.getCourseLevel());
                    }else
                        truee.clear();
                }else {
                    allCourse.add(courseLevel.getCourseLevel());
                    repeated.add(courseLevel.getCourseLevel());
                }
            }
        } else
            throw new NullPointerException();

        model.addObject("trainers",trainers);
        model.addObject("allCourses",allCourse);

        return model;

    }

    @RequestMapping(value = "trainer/trainerCoursesDownload/{trainerId}", method = RequestMethod.GET)
    public ResponseEntity downloadTrainerFile(@PathVariable("trainerId") Long trainerId){

        System.out.println(trainerId);

        if (createNewTrainerReport(trainerId)){
            return download.downloadFile("Trainer.xlsx");
        }

        return null;
    }

    @RequestMapping(value = "trainer/LevelCoursesDownload/{levelName}", method = RequestMethod.GET)
    public ResponseEntity downloadLevelFile(@PathVariable("levelName") String levelName){

        if (createNewLevelReport(levelName)){
            return download.downloadFile("Level.xlsx");
        }

        return null;
    }

    private boolean createNewLevelReport(String levelName){

        List list = new ArrayList();
        list.add("User Name");
        list.add("Role");
        list.add("email");
        list.add("Course Name");
        list.add("Group Name");

        String sql = "SELECT DISTINCT users.name as \"User Name\", roles.name as \"Role\", email, course.name as \"Course Name\", course.course_level as \"Course Level\", groups.name as \"Group Name\"  FROM users INNER JOIN course on users.roleid = course.trainer_id INNER JOIN roles ON roles.roleid = users.roleid LEFT OUTER JOIN groups ON course.courseid = groups.course_id WHERE ( users.roleid = 2 OR users.roleid = 4 ) AND course.course_level = " + "\'" + levelName + "\'";

        List<List> courses = dataService.getMultiFieldsFromTables(list, sql,"Level","table");

        return true;
    }

    private boolean createNewTrainerReport(long trainerId){

        List list = new ArrayList();
        list.add("Trainer Name");
        list.add("Course Name");
        list.add("Course Level");
        list.add("Course Status");

        String sql = "SELECT DISTINCT users.name as \"Trainer Name\", course.name as \"Course Name\", course.course_level as \"Course Level\", coursestatus.name_status as \"Course Status\" FROM course INNER JOIN coursestatus ON course.course_status_id = coursestatus.id INNER JOIN users ON course.trainer_id = users.roleid WHERE ( users.roleid = 2 OR users.roleid = 4 ) AND users.userid = "  + trainerId;

        List<List> courses = dataService.getMultiFieldsFromTables(list, sql,"Trainer","table");

        return true;
    }

}

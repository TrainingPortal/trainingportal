package trainingportal.universalexportcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.universalexportcreator.dao.DataDaoImpl;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    DataDaoImpl dataDao;

    @RequestMapping(value = "trainer/trainerCourses", method = RequestMethod.GET)
    public ModelAndView showDataTable(@NotNull ModelAndView model){

        List list = new ArrayList();
        list.add("empno");
        list.add("ename");
        list.add("job");
        list.add("mgr");
        list.add("hiredate");
        list.add("sal");
        list.add("comm");
        list.add("deptno");

        List<List> courses = dataDao.findFieldsFromTableWithCondition(list, "emp","empTable","table", "deptno = 10 AND sal > 1300");

        model.addObject("trainerAllCourses",courses);
        model.setViewName("trainer/trainerCourses");

        return model;
    }

}

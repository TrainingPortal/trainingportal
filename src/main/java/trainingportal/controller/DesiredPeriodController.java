package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Course;
import trainingportal.model.DesiredPeriod;
import trainingportal.service.*;

import java.util.List;

@Controller
public class DesiredPeriodController {

    @Autowired
    private DesiredPeriodService desiredPeriodService;

    @Autowired
    private CourseService courseService;

    private static final int ROWS_PER_PAGE = 10;

    @RequestMapping("/period_course/{page}/{courseId}")
    public ModelAndView showPeriodListOfCourse(@PathVariable("page") int page,
                                               @PathVariable("courseId") Long id,
                                               ModelAndView modelAndView) {

        List<DesiredPeriod> periodsOfCourse = desiredPeriodService.getPeriodPageByCourseId(page, ROWS_PER_PAGE, id);

        Course course = courseService.findById(id);
        modelAndView.addObject("periodCourse", course);

        modelAndView.addObject("pages", desiredPeriodService.getPages(id, ROWS_PER_PAGE));
        modelAndView.addObject("id", id);
        modelAndView.addObject("periodsOfCourse", periodsOfCourse);
        modelAndView.addObject("currentUrl", "period_course");
        modelAndView.setViewName("periodCreator/period_course");

        return modelAndView;
    }


    @RequestMapping(value = "/period-add-{courseId}", method = RequestMethod.GET)
    public ModelAndView addPeriod(@PathVariable Long courseId, ModelAndView modelAndView) {
        DesiredPeriod desiredPeriod = new DesiredPeriod();
        desiredPeriod.setCourseId(courseId);

        modelAndView.addObject("desiredPeriod", desiredPeriod);
        modelAndView.setViewName("periodCreator/period_add");
        return modelAndView;
    }

    @RequestMapping(value = "period-save", method = RequestMethod.POST)
    public ModelAndView savePeriod(@RequestParam("courseId") Long courseId, DesiredPeriod desiredPeriod, ModelAndView modelAndView) {
        desiredPeriodService.save(desiredPeriod);
        modelAndView.setViewName("redirect:/period_course/1/" + courseId);
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-period-{desiredPeriodId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editPeriodBase(@PathVariable("desiredPeriodId") Long desiredPeriodId,
                                       @PathVariable("id") Long id, ModelAndView modelAndView) {

        DesiredPeriod desiredPeriod = desiredPeriodService.findById(desiredPeriodId);
        modelAndView.addObject("desiredPeriod", desiredPeriod);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("periodCreator/edit_period_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-period-{desiredPeriodId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editPeriodById(@PathVariable("id") Long id, @PathVariable("desiredPeriodId") Long desiredPeriodId,
                                       DesiredPeriod desiredPeriod, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("periodCreator/edit_period_by_id");
            return modelAndView;
        } else {
            desiredPeriodService.update(desiredPeriod);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/period_course/1/" + id);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/period-delete-by/{desiredPeriodId}/{id}", method = RequestMethod.GET)
    public ModelAndView deletePeriodById(@PathVariable("desiredPeriodId") Long desiredPeriodId,
                                         @PathVariable("id") Long id, ModelAndView model) {
        desiredPeriodService.deleteById(desiredPeriodId);

        model.setViewName("redirect:/period_course/1/" + id);
        return model;
    }
}


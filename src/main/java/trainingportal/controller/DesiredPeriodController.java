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
//    @Autowired
//    private DesiredPeriodService desiredPeriodService;
//
//    @Autowired
//    private CourseService courseService;
//
//    private static final int ROWS_PER_PAGE = 10;
//
//
//    @RequestMapping("/period_course")
//    public ModelAndView showPeriodListOfCourses(Long id, ModelAndView modelAndView) {
//
//        List<DesiredPeriod> PeriodOfCourses = desiredPeriodService.getCoursePeriodId(id);
//
//
//        modelAndView.addObject("PeriodOfCourses", PeriodOfCourses);
//        modelAndView.setViewName("periodCreator/period_course");
//
//        return modelAndView;
//    }
//
//
//    /*@RequestMapping("/period_course/{page}/{courseId}")
//    public ModelAndView showMaterialListOfLessons(@PathVariable("page") int page,
//                                                  @PathVariable("courseId") Long id,
//                                                  ModelAndView modelAndView) {
//
////        List<Material> MaterialOfLesson = materialService.getMaterialLessonId(id);
//        List<Course> periodListOfCourses = courseService.getAllAsPageById(id, page, ROWS_PER_PAGE);
//
//        Course course =  courseService.findById(id);
//        modelAndView.addObject("periodListOfCourses", course);
//
//        modelAndView.addObject("pages", desiredPeriodService.getPages(id, ROWS_PER_PAGE));
//        modelAndView.addObject("id", id);
//        modelAndView.addObject("materialListOfLesson", periodListOfCourses);
//        modelAndView.addObject("currentUrl", "period_course");
//        modelAndView.setViewName("periodCreator/period_course");
//        return modelAndView;
//    }*/
//    @RequestMapping(value = "/period-add", method = RequestMethod.GET)
//    public ModelAndView addPeriod(ModelAndView modelAndView) {
//
//        modelAndView.addObject("period", new DesiredPeriod());
//        modelAndView.setViewName("periodCreator/period_add");
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "period-save-{course_id}", method = RequestMethod.POST)
//    public ModelAndView savePeriod(@PathVariable("course_id") Long courseId, DesiredPeriod desiredPeriod, ModelAndView modelAndView) {
//        desiredPeriodService.save(desiredPeriod);
//        modelAndView.addObject("lesson_id", courseId);
//        modelAndView.setViewName("redirect:/period_course");
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/period-delete-by-{desiredPeriodId}-{id}", method = RequestMethod.GET)
//    public ModelAndView deletePeriodById(@PathVariable("desiredPeriodId") Long desiredPeriodId,
//                                           @PathVariable("id") Long courseId, ModelAndView model, RedirectAttributes redirect) {
//        desiredPeriodService.deleteById(desiredPeriodId);
//
//        redirect.addFlashAttribute("successMessage", "period deleted successfully");
//        model.addObject("id", courseId);
//        model.setViewName("redirect:/period_course");
//        return model;
//    }
//
//}
///*@Autowired
//  private WeekdayService weekdayService;
//
//    @Autowired
//    private DesiredPeriodService desiredPeriodService;
//
//    private static final int ROWS_PER_PAGE = 10;
//
//
//    @RequestMapping("/weekday_period/{page}/{desiredPeriodId}")
//    public ModelAndView showWeekdayListOfLessons(@PathVariable("page") int page,
//                                                  @PathVariable("desiredPeriodId") Long id,
//                                                  ModelAndView modelAndView) {
//        List<Weekday> weekdayListOfPeriod = weekdayService.getAllAsPageByPeriodId(id, page, ROWS_PER_PAGE);
//
//        DesiredPeriod desiredPeriod =  desiredPeriodService.findById(id);
//        modelAndView.addObject("weekdayListOfPeriod", desiredPeriod);
//
//        modelAndView.addObject("pages", weekdayService.getPages(id, ROWS_PER_PAGE));
//        modelAndView.addObject("id", id);
//        modelAndView.addObject("weekdayListOfPeriod", weekdayListOfPeriod);
//        modelAndView.addObject("currentUrl", "weekday_period");
//        modelAndView.setViewName("weekdayCreator/weekday_period");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/weekday-add-{desiredPeriodId}", method = RequestMethod.GET)
//    public ModelAndView addWeekday(@PathVariable Long desiredPeriodId,
//                                    ModelAndView modelAndView) {
//
//        Weekday weekday = new Weekday();
//        weekday.setPeriodId(desiredPeriodId);
//
//        modelAndView.addObject("weekday", weekday);
//        modelAndView.setViewName("weekdayCreator/weekday_period");
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "weekday-save", method = RequestMethod.POST)
//    public ModelAndView saveWeekday(@RequestParam("desiredPeriodId") Long desiredPeriodId,
//                                     Weekday weekday, ModelAndView modelAndView) {
//
//        weekdayService.save(weekday);
//        modelAndView.addObject("desired_period_id", desiredPeriodId);
//        modelAndView.setViewName("redirect:/weekday_period/1/" + desiredPeriodId);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = {"edit-weekday-{weekdayId}-{id}"}, method = RequestMethod.GET)
//    public ModelAndView editWeekdayBase(@PathVariable("weekdayId") Long weekdayId,
//                                         @PathVariable("id") Long id,ModelAndView modelAndView) {
//
//        Weekday weekday = weekdayService.findById(weekdayId);
//        modelAndView.addObject("weekday", weekday);
//        modelAndView.addObject("edit", true);
//        modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = {"edit-weekday-{weekdayId}-{id}"}, method = RequestMethod.POST)
//    public ModelAndView editWeekdayById(@PathVariable("id") Long id, @PathVariable("weekdayId") Long weekdayId,
//                                         Weekday weekday, BindingResult bindingResult, ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");
//            return modelAndView;
//        } else {
//            weekdayService.update(weekday);
//            modelAndView.addObject("id", id);
//            modelAndView.setViewName("redirect:/weekday_period/1/" + id);
//            return modelAndView;
//        }
//    }
//
//    @RequestMapping(value = "/weekday-delete-by/{weekdayId}/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteWeekdayById(@PathVariable("weekdayId") Long weekdayId,
//                                           @PathVariable("id") Long id, ModelAndView model) {
//        weekdayService.deleteById(weekdayId);
//
//        model.addObject("id", id);
//        model.setViewName("redirect:/weekday_period/1/" + id);
//        return model;
//    }
//
//
//}*/

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


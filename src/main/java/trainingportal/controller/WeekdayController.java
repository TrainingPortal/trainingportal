package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.DesiredPeriod;
import trainingportal.model.Weekday;
import trainingportal.service.DesiredPeriodService;
import trainingportal.service.WeekdayService;

import java.util.List;

@Controller
public class WeekdayController {

    /*  @Autowired
      private WeekdayService weekdayService;

      @Autowired
      private DesiredPeriodService desiredPeriodService;

      private static final int ROWS_PER_PAGE = 10;



      @RequestMapping("/weekday_period/{page}/{desiredPeriodId}")
      public ModelAndView showMWeekdayListOfPeriods(@PathVariable("page") int page,
                                                    @PathVariable("desiredPeriodId") Long id,
                                                    ModelAndView modelAndView) {
          List<Weekday> periodOfWeekdays = weekdayService.getAllAsPageByPeriodId(id, page, ROWS_PER_PAGE);
          DesiredPeriod desiredPeriod =  desiredPeriodService.findById(id);
          modelAndView.addObject("periodOfWeekdays", desiredPeriod);

          modelAndView.addObject("pages", weekdayService.getPages(id, ROWS_PER_PAGE));
          modelAndView.addObject("id", id);
          modelAndView.addObject("periodOfWeekdays", periodOfWeekdays);
          modelAndView.addObject("currentUrl", "weekday_period");
          modelAndView.setViewName("weekdayCreator/weekday_period");
          return modelAndView;
      }

      @RequestMapping(value = "/weekday-add-{desiredPeriodId}", method = RequestMethod.GET)
      public ModelAndView addWeekday(@PathVariable Long desiredPeriodId,
                                      ModelAndView modelAndView) {

          Weekday weekday = new Weekday();
          weekday.setPeriodId(desiredPeriodId);

          modelAndView.addObject("weekday", weekday);
          modelAndView.setViewName("weekdayCreator/weekday_add");

          return modelAndView;
      }

      @RequestMapping(value = "weekday-save", method = RequestMethod.POST)
      public ModelAndView saveWeekday(@RequestParam("desiredPeriodId") Long desiredPeriodId,
                                       Weekday weekday, ModelAndView modelAndView) {

          weekdayService.save(weekday);
          modelAndView.addObject("desired_period_id", desiredPeriodId);
          modelAndView.setViewName("redirect:/weekday_period/1/" + desiredPeriodId);
          return modelAndView;
      }

      @RequestMapping(value = {"edit-weekday-{desiredPeriodId}-{id}"}, method = RequestMethod.GET)
      public ModelAndView editPeriodBase(@PathVariable("desiredPeriodId") Long weekdayId,
                                           @PathVariable("id") Long id,ModelAndView modelAndView) {

          Weekday weekday = weekdayService.findById(weekdayId);
          modelAndView.addObject("weekday", weekday);
          modelAndView.addObject("edit", true);
          modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");

          return modelAndView;
      }

      @RequestMapping(value = {"edit-weekday-{weekdayId}-{id}"}, method = RequestMethod.POST)
      public ModelAndView editWeekdayById(@PathVariable("id") Long id, @PathVariable("weekdayId") Long weekdayId,
                                           Weekday weekday, BindingResult bindingResult, ModelAndView modelAndView) {
          if (bindingResult.hasErrors()) {
              modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");
              return modelAndView;
          } else {
              weekdayService.update(weekday);
              modelAndView.addObject("id", id);
              modelAndView.setViewName("redirect:/weekday_period/1/" + id);
              return modelAndView;
          }
      }

      @RequestMapping(value = "/weekday-delete-by/{weekdayId}/{id}", method = RequestMethod.GET)
      public ModelAndView deleteWeekdayById(@PathVariable("weekdayId") Long weekdayId,
                                             @PathVariable("id") Long id, ModelAndView model) {
          weekdayService.deleteById(weekdayId);

          model.addObject("id", id);
          model.setViewName("redirect:/weekday_period/1/" + id);
          return model;
      }


  }

  */
    @Autowired
    private WeekdayService weekdayService;

    @Autowired
    private DesiredPeriodService desiredPeriodService;

    private static final int ROWS_PER_PAGE = 10;


    @RequestMapping("/weekday_period/{page}/{periodId}")
    public ModelAndView showWeekdayListOfLessons(@PathVariable("page") int page,
                                                 @PathVariable("periodId") Long id,
                                                 ModelAndView modelAndView) {
        List<Weekday> weekdayListOfPeriod = weekdayService.getAllAsPageByPeriodId(id, page, ROWS_PER_PAGE);

        DesiredPeriod desiredPeriod =  desiredPeriodService.findById(id);
        modelAndView.addObject("desiredPeriod", desiredPeriod);

        modelAndView.addObject("pages", weekdayService.getPages(id, ROWS_PER_PAGE));
        modelAndView.addObject("id", id);
        modelAndView.addObject("weekdayListOfPeriod", weekdayListOfPeriod);
        modelAndView.addObject("currentUrl", "weekday_period");
        modelAndView.setViewName("weekdayCreator/weekday_period");
        return modelAndView;
    }

    @RequestMapping(value = "/weekday-add-{periodId}", method = RequestMethod.GET)
    public ModelAndView addWeekday(@PathVariable Long periodId,
                                   ModelAndView modelAndView) {

        Weekday weekday = new Weekday();
        weekday.setPeriodId(periodId);

        modelAndView.addObject("weekday", weekday);
        modelAndView.setViewName("weekdayCreator/weekday_add");

        return modelAndView;
    }

    @RequestMapping(value = "weekday-save", method = RequestMethod.POST)
    public ModelAndView saveWeekday(@RequestParam("periodId") Long periodId,
                                    Weekday weekday, ModelAndView modelAndView) {

        weekdayService.save(weekday);
        modelAndView.addObject("period_id", periodId);
        modelAndView.setViewName("redirect:/thanks_page");
//                "//1/" + periodId);
        return modelAndView;
    }

    @RequestMapping(value = {"edit-weekday-{weekdayId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editWeekdayBase(@PathVariable("weekdayId") Long weekdayId,
                                        @PathVariable("id") Long id, ModelAndView modelAndView) {

        Weekday weekday = weekdayService.findById(weekdayId);
        modelAndView.addObject("weekday", weekday);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"edit-weekday-{weekdayId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editWeekdayById(@PathVariable("id") Long id, @PathVariable("weekdayId") Long weekdayId,
                                        Weekday weekday, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("weekdayCreator/edit_weekday_by_id");
            return modelAndView;
        } else {
            weekdayService.update(weekday);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/weekday_period/1/" + id);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/weekday-delete-by/{weekdayId}/{id}", method = RequestMethod.GET)
    public ModelAndView deleteWeekdayById(@PathVariable("weekdayId") Long weekdayId,
                                          @PathVariable("id") Long id, ModelAndView model) {
        weekdayService.deleteById(weekdayId);

        model.addObject("id", id);
        model.setViewName("redirect:/weekday_period/1/" + id);
        return model;
    }
    @GetMapping("/thanks_page")
    String ThanksPage(Model model){

        return "weekdayCreator/thanks_page";
    }

}


package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Homework;
import trainingportal.service.HomeworkService;

import java.util.List;

@Controller
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping(value = "/lesson_homework")
    public ModelAndView showHomeworkListOfLesson(Long id, ModelAndView modelAndView) {
        List<Homework> homeworkList = homeworkService.getHomeworkLessonId(id);

        modelAndView.addObject("homeworkList", homeworkList);
        modelAndView.setViewName("homeworkCreator/lesson_homework");
        return modelAndView;
    }

    @RequestMapping(value = "/homework-add", method = RequestMethod.GET)
    public ModelAndView addHomework(ModelAndView modelAndView) {

        modelAndView.addObject("homework", new Homework());
        modelAndView.setViewName("homeworkCreator/homework_add");

        return modelAndView;
    }

    @RequestMapping(value = "homework-save", method = RequestMethod.POST)
    public ModelAndView saveHomework(Homework homework, ModelAndView modelAndView) {
        homeworkService.save(homework);
        modelAndView.setViewName("redirect:/lesson_homework");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-homework-{homeworkId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editHomeworkBase(@PathVariable("homeworkId") Long homeworkId, ModelAndView modelAndView) {
        Homework homework = homeworkService.findById(homeworkId);
        modelAndView.addObject("homework", homework);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("homeworkCreator/edit_homework_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-homework-{homeworkId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editHomeworkById(@PathVariable("id") Long id, Homework homework,
                                         BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("homeworkCreator/edit_homework_by_id");
            return modelAndView;
        } else {
            homeworkService.update(homework);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/lesson_homework");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/homework-delete-by-{homeworkId}-{id}", method = RequestMethod.GET)
    public ModelAndView deleteHomeworkById(@PathVariable("homeworkId") Long HomeworkId,
                                           @PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirect) {
        homeworkService.deleteById(HomeworkId);
        model.addObject("id", id);
        model.setViewName("redirect:/lesson_homework");
        return model;
    }


}


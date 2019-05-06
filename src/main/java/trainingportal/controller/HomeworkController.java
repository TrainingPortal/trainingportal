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
import trainingportal.service.HomeworkServiceImpl;
import trainingportal.service.LessonServiceImpl;

import java.util.List;

@Controller
public class HomeworkController {


    @Autowired
    HomeworkServiceImpl homeworkService;

    @Autowired
    LessonServiceImpl lessonService;

//    @RequestMapping(value = "lesson_homework")
//    public ModelAndView showHomeworksList(Long Id, ModelAndView modelAndView) {
//        List<Homework> homeworkList = homeworkService.findAll();
//        modelAndView.addObject("homeworkList", homeworkList);
//        modelAndView.setViewName("homeworkCreator/lesson_homework");
//        return modelAndView;
//    }

    @RequestMapping(value = "/lesson_homework")
    public ModelAndView showHomeworkListOfLesson(Long id, ModelAndView modelAndView) {
        List<Homework> homeworkList = homeworkService.getHomeworkLessonId(id);

//        Lesson lesson =  lessonService.findById(id);
//        modelAndView.addObject("MaterialOfLesson", lesson);

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

    @RequestMapping(value = {"/edit-homework-{homeworkId}"}, method = RequestMethod.GET)
    public ModelAndView editHomeworkBase(@PathVariable("homeworkId") Long homeworkId, ModelAndView modelAndView) {
        Homework homework = homeworkService.findById(homeworkId);
        modelAndView.addObject("homework", homework);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("homeworkCreator/edit_homework_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-homework-{homeworkId}"}, method = RequestMethod.POST)
    public ModelAndView editHomeworkById(Homework homework, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("homeworkCreator/edit_homework_by_id");
            return modelAndView;
        } else {
            homeworkService.update(homework);
            modelAndView.setViewName("redirect:/lesson_homework");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/homework-delete-by-{homeworkId}", method = RequestMethod.GET)
    public ModelAndView deleteHomeworkById(@PathVariable("homeworkId") Long HomeworkId, ModelAndView model, RedirectAttributes redirect) {
        homeworkService.deleteById(HomeworkId);

        redirect.addFlashAttribute("successMessage", "homework deleted successfully");
        model.setViewName("redirect:/lesson_homework");
        return model;
    }


}


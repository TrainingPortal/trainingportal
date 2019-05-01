package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Lesson;
import trainingportal.service.LessonServiceImpl;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    LessonServiceImpl lessonService;

    @RequestMapping(value = "lesson_create")
    public ModelAndView showLessonsList(Long Id, ModelAndView modelAndView) {
        List<Lesson> lessonList = lessonService.findAll();
        modelAndView.addObject("lessonList", lessonList);
        modelAndView.setViewName("lessonCreator/lesson_create");
        return modelAndView;
    }

    @RequestMapping(value = "/lesson-add", method = RequestMethod.GET)
    public ModelAndView addLesson(ModelAndView modelAndView) {

        modelAndView.addObject("lesson", new Lesson());
        modelAndView.setViewName("lessonCreator/lesson_add");

        return modelAndView;
    }

    @RequestMapping(value = "lesson-save", method = RequestMethod.POST)
    public ModelAndView saveLesson(Lesson lesson, ModelAndView modelAndView) {
        lessonService.save(lesson);
        modelAndView.setViewName("redirect:/lesson_create");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{id}"}, method = RequestMethod.GET)
    public ModelAndView editLessonBase(@PathVariable("id") Long lessonId, ModelAndView modelAndView) {
        Lesson lesson = lessonService.findById(lessonId);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("lessonCreator/edit_lesson_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{id}"}, method = RequestMethod.POST)
    public ModelAndView editLessonById(Lesson lesson, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("lessonCreator/edit_lesson_by_id");
            return modelAndView;
        } else {
            lessonService.update(lesson);
            modelAndView.setViewName("redirect:/lesson_create");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/lesson-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteLessonById(@PathVariable("id") Long lessonId, ModelAndView model, RedirectAttributes redirect) {
        lessonService.deleteById(lessonId);

        redirect.addFlashAttribute("successMessage", "lesson deleted successfully");

        model.setViewName("redirect:/lesson_create");
        return model;
    }


}

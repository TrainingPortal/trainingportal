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
import trainingportal.service.CourseServiceImpl;
import trainingportal.service.LessonServiceImpl;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    LessonServiceImpl lessonService;

    @Autowired
    CourseServiceImpl courseService;

    @RequestMapping(value = "lesson_create")
    public ModelAndView showLessonsList(Long Id, ModelAndView modelAndView) {
        List<Lesson> lessonList = lessonService.findAll();
        modelAndView.addObject("lessonList", lessonList);
        modelAndView.setViewName("lessonCreator/lesson_create");
        return modelAndView;
    }

    //here is new methods, need to think about replacing old methods
//    Old realization not working with redirect
//    @RequestMapping("/course_lessons_{id}")
//    public ModelAndView showLessonListOfCourse(@PathVariable("id") Long courseId, ModelAndView modelAndView) {
//
//        Course course = courseService.findById(courseId);
//
//        List<Lesson> lessonsOfCourse = lessonService.getLessonCourseId(courseId);
//
//        modelAndView.addObject("courseLesson", course);
//        modelAndView.addObject("lessonsOfCourse", lessonsOfCourse);
//        modelAndView.setViewName("lessonCreator/course_lessons");
//
//        return modelAndView;
//    }

    @RequestMapping("/course_lessons")
    public ModelAndView showLessonListOfCourse(Long id, ModelAndView modelAndView) {

//        Course course = courseService.findById(id);

        List<Lesson> lessonsOfCourse = lessonService.getLessonCourseId(id);

//        modelAndView.addObject("courseLesson", course);
        modelAndView.addObject("lessonsOfCourse", lessonsOfCourse);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("lessonCreator/course_lessons");

        return modelAndView;
    }

    @RequestMapping(value = "/lesson-add", method = RequestMethod.GET)
    public ModelAndView addLesson(ModelAndView modelAndView) {

        modelAndView.addObject("lesson", new Lesson());
        modelAndView.setViewName("lessonCreator/lesson_add");

        return modelAndView;
    }

    @RequestMapping(value = "lesson-save", method = RequestMethod.POST)
    public ModelAndView saveLesson(Long id, Lesson lesson, ModelAndView modelAndView) {
        lessonService.save(lesson);
//        Course course = courseService.findById(id);
//        modelAndView.addObject("courseLesson", course);
//        modelAndView.addObject("id", id);
        modelAndView.setViewName("redirect:/course_lessons");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{lessonId}"}, method = RequestMethod.GET)
    public ModelAndView editLessonBase(@PathVariable("lessonId") Long lessonId, ModelAndView modelAndView) {
        Lesson lesson = lessonService.findById(lessonId);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("lessonCreator/edit_lesson_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{id}"}, method = RequestMethod.POST)
    public ModelAndView editLessonById(Long id, Lesson lesson, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("lessonCreator/edit_lesson_by_id");
            return modelAndView;
        } else {
            lessonService.update(lesson);
//            Course course = courseService.findById(id);
//            modelAndView.addObject("courseLesson", course);
//            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/course_lessons");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/lesson-delete-by-{lessonId}", method = RequestMethod.GET)
    public ModelAndView deleteLessonById(@PathVariable("lessonId") Long lessonId, ModelAndView model, RedirectAttributes redirect) {
        lessonService.deleteById(lessonId);

        redirect.addFlashAttribute("successMessage", "lesson deleted successfully");
        model.setViewName("redirect:/course_lessons");
        return model;
    }


}

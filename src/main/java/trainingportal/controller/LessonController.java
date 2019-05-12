package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.model.Lesson;
import trainingportal.service.CourseService;
import trainingportal.service.LessonService;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    LessonService lessonService;

    @Autowired
    CourseService courseService;

    @RequestMapping("/course_lessons")
    public ModelAndView showLessonListOfCourse(Long id, ModelAndView modelAndView) {

        List<Lesson> lessonsOfCourse = lessonService.getLessonCourseId(id);

        Course course = courseService.findById(id);
        modelAndView.addObject("courseLesson", course);
        modelAndView.addObject("id", id);
        modelAndView.addObject("lessonsOfCourse", lessonsOfCourse);
        modelAndView.setViewName("lessonCreator/course_lessons");

        return modelAndView;
    }

    @RequestMapping(value = "/lesson-add", method = RequestMethod.GET)
    public ModelAndView addLesson(ModelAndView modelAndView) {

        modelAndView.addObject("lesson", new Lesson());
        modelAndView.setViewName("lessonCreator/lesson_add");

        return modelAndView;
    }

    @RequestMapping(value = "lesson-save-{lesId}-{courId}", method = RequestMethod.POST)
    public ModelAndView saveLesson(@PathVariable("lesId") Long lessonId,
                                   @PathVariable("courId") Long courseId, Lesson lesson, ModelAndView modelAndView) {
        lessonService.save(lesson);
        modelAndView.addObject("lesId", lessonId);
        modelAndView.addObject("courId", courseId);
        modelAndView.setViewName("redirect:/course_lessons");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{lessonId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editLessonBase(@PathVariable("lessonId") Long lessonId, ModelAndView modelAndView) {
        Lesson lesson = lessonService.findById(lessonId);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("lessonCreator/edit_lesson_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{lessonId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editLessonById(@PathVariable("id") Long id, Lesson lesson, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("lessonCreator/edit_lesson_by_id");
            return modelAndView;
        } else {
            lessonService.update(lesson);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/course_lessons");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/lesson-delete-by-{lessonId}-{id}", method = RequestMethod.GET)
    public ModelAndView deleteLessonById(@PathVariable("lessonId") Long lessonId, @PathVariable("id") Long id, ModelAndView model) {
        lessonService.deleteById(lessonId);
//        redirect.addFlashAttribute("successMessage", "lesson deleted successfully");
        model.addObject("id", id);
        model.setViewName("redirect:/course_lessons");
        return model;
    }


}

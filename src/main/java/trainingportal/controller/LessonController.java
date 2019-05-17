package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Course;
import trainingportal.model.Lesson;
import trainingportal.service.CourseService;
import trainingportal.service.LessonService;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    private static final int ROWS_LIMIT = 10;

    @RequestMapping("/course_lessons/{page}/{courseId}")
    public ModelAndView showLessonListOfCourse(@PathVariable("page") int page,
                                               @PathVariable("courseId") Long id,
                                               ModelAndView modelAndView) {

        //List<Lesson> lessonsOfCourse = lessonService.getLessonCourseId(id);
        List<Lesson> lessonsOfCourse = lessonService.getLessonsPageByCourseId(page, ROWS_LIMIT, id);

        Course course = courseService.findById(id);
        modelAndView.addObject("courseLesson", course);

        modelAndView.addObject("pages", lessonService.getPages(id, ROWS_LIMIT));
        modelAndView.addObject("id", id);
        modelAndView.addObject("lessonsOfCourse", lessonsOfCourse);
        modelAndView.addObject("currentUrl", "course_lessons");
        modelAndView.setViewName("lessonCreator/course_lessons");

        return modelAndView;
    }



    @RequestMapping(value = "/lesson-add-{courseId}", method = RequestMethod.GET)
    public ModelAndView addLesson(@PathVariable Long courseId, ModelAndView modelAndView) {
        Lesson lesson = new Lesson();
        lesson.setCourseId(courseId);

        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("lessonCreator/lesson_add");
        return modelAndView;
    }

    @RequestMapping(value = "lesson-save", method = RequestMethod.POST)
    public ModelAndView saveLesson(@RequestParam("courseId") Long courseId,
                                   Lesson lesson, ModelAndView modelAndView) {

        lessonService.save(lesson);
        modelAndView.setViewName("redirect:/course_lessons/1/" + courseId);
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{lessonId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editLessonBase(@PathVariable("lessonId") Long lessonId,
                                       @PathVariable("id") Long id, ModelAndView modelAndView) {

        Lesson lesson = lessonService.findById(lessonId);
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("lessonCreator/edit_lesson_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-lesson-{lessonId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editLessonById(@PathVariable("id") Long id, @PathVariable("lessonId") Long lessonId,
                                       Lesson lesson, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("lessonCreator/edit_lesson_by_id");
            return modelAndView;
        } else {
            lessonService.update(lesson);
            modelAndView.addObject("id", id);
//            modelAndView.setViewName("redirect:/course_lessons/{id}");
            modelAndView.setViewName("redirect:/course_lessons/1/" + id);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/lesson-delete-by/{lessonId}/{id}", method = RequestMethod.GET)
    public ModelAndView deleteLessonById(@PathVariable("lessonId") Long lessonId,
                                         @PathVariable("id") Long id, ModelAndView model) {
        lessonService.deleteById(lessonId);

//        redirect.addFlashAttribute("successMessage", "lesson deleted successfully");
        model.setViewName("redirect:/course_lessons/1/" + id);
        return model;
    }


}

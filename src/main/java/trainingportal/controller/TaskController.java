package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Task;
import trainingportal.service.HomeworkService;
import trainingportal.service.TaskService;

import java.util.List;

@Controller
public class TaskController {


    @Autowired
    TaskService taskService;

    @Autowired
    HomeworkService homeworkService;

    @RequestMapping(value = "/homework_task")
    public ModelAndView showTaskListOfHomework(Long id, ModelAndView modelAndView) {
        List<Task> taskList = taskService.getTaskLessonById(id);

        modelAndView.addObject("taskList", taskList);
        modelAndView.setViewName("taskCreator/homework_task");
        return modelAndView;
    }

    @RequestMapping(value = "/task-add", method = RequestMethod.GET)
    public ModelAndView addTask(ModelAndView modelAndView) {

        modelAndView.addObject("task", new Task());
        modelAndView.setViewName("taskCreator/task_add");

        return modelAndView;
    }

    @RequestMapping(value = "task-save-{id}", method = RequestMethod.POST)
    public ModelAndView saveTask(@PathVariable("id") Long id, Task task, ModelAndView modelAndView) {
        taskService.save(task);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("redirect:/homework_task");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-task-{taskId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editTaskBase(@PathVariable("taskId") Long taskId, ModelAndView modelAndView) {
        Task task = taskService.findById(taskId);
        modelAndView.addObject("task", task);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("taskCreator/edit_task_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-task-{taskId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editTaskById(@PathVariable("id") Long id, Task task, BindingResult bindingResult,
                                     ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("taskCreator/edit_task_by_id");
            return modelAndView;
        } else {
            taskService.update(task);
            modelAndView.addObject("id", id);
            modelAndView.setViewName("redirect:/homework_task");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/task-delete-by-{taskId}-{id}", method = RequestMethod.GET)
    public ModelAndView deleteTaskById(@PathVariable("taskId") Long taskId,
                                       @PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirect) {

        taskService.deleteById(taskId);
        redirect.addFlashAttribute("successMessage", "task deleted successfully");
        model.addObject("id", id);
        model.setViewName("redirect:/homework_task");
        return model;
    }


}




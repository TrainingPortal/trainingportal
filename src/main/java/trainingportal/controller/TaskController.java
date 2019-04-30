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
import trainingportal.service.TaskServiceImpl;

import java.util.List;

@Controller
public class TaskController {


    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping(value = "task_create")
    public ModelAndView showTasksList(Long Id, ModelAndView modelAndView) {
        List<Task> taskList = taskService.findAll();
        modelAndView.addObject("taskList", taskList);
        modelAndView.setViewName("taskCreator/task_create");
        return modelAndView;
    }

    @RequestMapping(value = "/task-add", method = RequestMethod.GET)
    public ModelAndView addTask(ModelAndView modelAndView) {

        modelAndView.addObject("task", new Task());
        modelAndView.setViewName("taskCreator/task_add");

        return modelAndView;
    }

    @RequestMapping(value = "task-save", method = RequestMethod.POST)
    public ModelAndView saveTask(Task task, ModelAndView modelAndView) {
        taskService.save(task);
        modelAndView.setViewName("redirect:/task_create");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit-task-{id}"}, method = RequestMethod.GET)
    public ModelAndView editTaskBase(@PathVariable("id") Long taskId, ModelAndView modelAndView) {
        Task task = taskService.findById(taskId);
        modelAndView.addObject("task", task);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("taskCreator/edit_task_by_id");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-task-{id}"}, method = RequestMethod.POST)
    public ModelAndView editTaskById(Task task, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("taskCreator/edit_task_by_id");
            return modelAndView;
        } else {
            taskService.update(task);
            modelAndView.setViewName("redirect:/task_create");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/task-delete-by-{id}", method = RequestMethod.GET)
    public ModelAndView deleteTaskById(@PathVariable("id") Long TaskId, ModelAndView model, RedirectAttributes redirect) {
        taskService.deleteById(TaskId);

        redirect.addFlashAttribute("successMessage", "task deleted successfully");

        model.setViewName("redirect:/task_create");
        return model;
    }


}




package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.UserService;
import trainingportal.validators.UserValidator;

import javax.validation.Valid;
import java.util.List;
@Controller
public class TrainerController {
    @Autowired
    private UserService trainerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Show all trainers*/
    @RequestMapping("/trainers")
    public ModelAndView allTrainers(ModelAndView model) {

        List<User> trainers = trainerService.findAllByRole(Role.TRAINER);
        model.addObject("trainers", trainers);
        model.setViewName("trainer/trainers");

        return model;
    }

    @RequestMapping("/trainer-{id}")
    public ModelAndView showTrainer(@PathVariable("id") Long id, ModelAndView model) {

        User trainer= trainerService.findById(id);
        model.addObject("trainer", trainer);
        model.setViewName("trainer/show");
        return model;
    }

    @RequestMapping("/trainer-profile-{id}")
    public ModelAndView showTrainerProfile(@PathVariable("id") Long id, ModelAndView model) {

        User trainer= trainerService.findById(id);
        model.addObject("trainer", trainer);
        model.setViewName("trainer/profile");

        return model;
    }

    @RequestMapping("/trainer-add")
    public ModelAndView addTrainer(ModelAndView model){

        model.addObject("trainer",new User());
        model.setViewName("trainer/add");

        return model;
    }

    @RequestMapping(value = { "/trainer-update-{id}" }, method = RequestMethod.GET)
    public ModelAndView editTrainer(@PathVariable("id") Long id, ModelAndView model) {

        User trainer = trainerService.findById(id);

        trainer.setPassword(bCryptPasswordEncoder.encode(trainer.getPassword()));
        model.addObject("trainer", trainer);
        model.addObject("edit", true);
        model.setViewName("trainer/update");

        return model;
    }

    @RequestMapping(value = { "/trainer-update-{id}" }, method = RequestMethod.POST)
    public ModelAndView updateTrainer(User trainer,BindingResult bindingResult, ModelAndView model, RedirectAttributes redir){
        if(bindingResult.hasErrors())
        {
            model.setViewName("trainer/update");
            return model;
        }
        else {
            trainerService.update(trainer);
            redir.addFlashAttribute("successMessage", "User " + trainer.getUserName() + " "+ trainer.getEmail() + " updated successfully");
            model.setViewName("redirect:/trainers");
            return model;
        }
    }

    @RequestMapping(value = "saveTrainer", method = RequestMethod.POST)
    public ModelAndView saveTrainer(@Valid User trainer, BindingResult bindingResult, ModelAndView model, RedirectAttributes redir){

        if (bindingResult.hasErrors() || !UserValidator.correct(trainer,trainerService)) {
            model.addObject("message", UserValidator.Check(trainer,trainerService));
            model.setViewName("trainer/add");
            return model;
        }
        trainer.setEnabled(1);
        trainer.setPassword(bCryptPasswordEncoder.encode(trainer.getPassword()));
        trainerService.save(trainer, Role.TRAINER);
        redir.addFlashAttribute("successMessage", "User " + trainer.getUserName() + " "+ trainer.getEmail() + " created successfully");
        model.setViewName("redirect:/trainers");

        return model;
    }

    @RequestMapping(value = "/trainer-delete-{id}", method = RequestMethod.GET)
    public ModelAndView deleteTrainer(@PathVariable("id") Long trainerId, ModelAndView model, RedirectAttributes redir){

        User trainer = trainerService.findById(trainerId);
        trainerService.deleteById(trainerId);
        redir.addFlashAttribute("successMessage", "User " + trainer.getUserName() + " "+ trainer.getEmail() + " deleted successfully");
        model.setViewName("redirect:/trainers");
        return model;
    }

    @RequestMapping(value = "/trainers-delete-all", method = RequestMethod.GET)
    public ModelAndView deleteAllTrainers(ModelAndView model, RedirectAttributes redir){

        trainerService.deleteAllByRole(Role.TRAINER);
        redir.addFlashAttribute("successMessage", "All users deleted successfully");
        model.setViewName("redirect:/trainers");

        return model;
    }
}

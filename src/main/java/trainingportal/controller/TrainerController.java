package trainingportal.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.UserService;
import trainingportal.universalexportcreator.dao.DataDaoImpl;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TrainerController {
    @Autowired
    private UserService trainerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final int ROWS_LIMIT = 10;

    /* Show all trainers*/
    @RequestMapping("/trainers/{page}")
    public ModelAndView allTrainers(@PathVariable("page") int page, ModelAndView model) {

        List<User> trainers = trainerService.getAllByRoleAsPage(page, ROWS_LIMIT, Role.TRAINER);
        model.addObject("trainers", trainers);
        model.addObject("pages", trainerService.getPagesByRole(Role.TRAINER, ROWS_LIMIT));
        model.addObject("currentUrl", "trainers");
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

    @GetMapping("/trainer-add")
    public ModelAndView addTrainer(ModelAndView model){

        model.addObject("user",new User());
        model.setViewName("trainer/add");

        return model;
    }

    @RequestMapping(value = { "/trainer-update-{id}" }, method = RequestMethod.GET)
    public ModelAndView editTrainer(@PathVariable("id") Long id, ModelAndView model) {

        User trainer = trainerService.findById(id);

        List<Role> roles = trainerService.getRoles();
        model.addObject("roles", roles);

        Map<Long, String> mapStatus = trainerService.setMapStatus();

        model.addObject("mapStatus", mapStatus);

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
            model.setViewName("redirect:/trainers/1");
            return model;
        }
    }

    @RequestMapping(value = "saveTrainer", method = RequestMethod.POST)
    public ModelAndView saveTrainer(@Valid User user, BindingResult bindingResult, ModelAndView model, RedirectAttributes redir){

        User userExists = trainerService.findByEmail(user.getEmail());

        if (userExists != null) {
            model.addObject("alreadyRegisteredMessage",
                    "Oops! There is already a user registered with the email provided.");
            model.setViewName("trainer/add");
            bindingResult.reject("email");
            return model;
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("trainer/add");
            return model;
        }
        user.setEnabled(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleId(Role.TRAINER);
        trainerService.save(user);
        redir.addFlashAttribute("successMessage", "User " + user.getUserName() + " "+ user.getEmail() + " created successfully");
        model.setViewName("redirect:/trainers/1");

        return model;
    }

    @RequestMapping(value = "/trainer-delete-{id}", method = RequestMethod.GET)
    public ModelAndView deleteTrainer(@PathVariable("id") Long trainerId, ModelAndView model, RedirectAttributes redir){

        User trainer = trainerService.findById(trainerId);
        trainerService.deleteById(trainerId);
        redir.addFlashAttribute("successMessage", "User " + trainer.getUserName() + " "+ trainer.getEmail() + " deleted successfully");
        model.setViewName("redirect:/trainers/1");
        return model;
    }

    @RequestMapping(value = "/trainers-delete-all", method = RequestMethod.GET)
    public ModelAndView deleteAllTrainers(ModelAndView model, RedirectAttributes redir){

        trainerService.deleteAllByRole(Role.TRAINER);
        redir.addFlashAttribute("successMessage", "All users deleted successfully");
        model.setViewName("redirect:/trainers/1");

        return model;
    }

    @Autowired
    public DataDaoImpl dataDao;

    @RequestMapping(value = "/trainer-download-all-trainers", method = RequestMethod.GET)
    public ModelAndView downloadAllTrainers(ModelAndView model, RedirectAttributes redir){

        List list = new ArrayList();
        list.add("name");
        list.add("email");

        List<List> courses = dataDao.findFieldsFromTableWithCondition(list, "users","allTrainers","table", "roleid = 3");

//        String fromFile = "/Users/mrlova/Downloads/log.txt";
//        String toFile = "/Users/mrlova/Downloads/log.txt";
//
//        try {
//            FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return model;
    }
}

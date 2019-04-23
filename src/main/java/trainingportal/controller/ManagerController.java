package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ManagerController {
    @Autowired
    private UserService managerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Show all managers*/
    @RequestMapping("/managers")
    public ModelAndView allManagers(ModelAndView model) {

        List<User> managers = managerService.findAllByRole(Role.MANAGER);
        model.addObject("managers", managers);
        model.setViewName("manager/managers");

        return model;
    }

    @RequestMapping("/manager-{id}")
    public ModelAndView showManager(@PathVariable("id") Long id, ModelAndView model) {

        User manager= managerService.findById(id);
        model.addObject("manager", manager);
        model.setViewName("manager/show");
        return model;
    }

    @RequestMapping("/manager-profile-{id}")
    public ModelAndView showManagerProfile(@PathVariable("id") Long id, ModelAndView model) {

        User manager= managerService.findById(id);
        model.addObject("manager", manager);
        model.setViewName("manager/profile");

        return model;
    }

    @GetMapping("/manager-add")
    public ModelAndView addManager(ModelAndView model){

        model.addObject("user",new User());
        model.setViewName("manager/add");

        return model;
    }

    @RequestMapping(value = { "/manager-update-{id}" }, method = RequestMethod.GET)
    public ModelAndView editManager(@PathVariable("id") Long id, ModelAndView model) {

        User manager = managerService.findById(id);

        manager.setPassword(bCryptPasswordEncoder.encode(manager.getPassword()));
        model.addObject("manager", manager);
        model.addObject("edit", true);
        model.setViewName("manager/update");

        return model;
    }

    @RequestMapping(value = { "/manager-update-{id}" }, method = RequestMethod.POST)
    public ModelAndView updateManager(User manager,BindingResult bindingResult, ModelAndView model, RedirectAttributes redir){
        if(bindingResult.hasErrors())
        {
            model.setViewName("manager/update");
            return model;
        }
        else {
            managerService.update(manager);
            redir.addFlashAttribute("successMessage", "User " + manager.getUserName() + " "+ manager.getEmail() + " updated successfully");
            model.setViewName("redirect:/managers");
            return model;
        }
    }

    @RequestMapping(value = "saveManager", method = RequestMethod.POST)
    public ModelAndView saveManager(@Valid User user, BindingResult bindingResult, ModelAndView model, RedirectAttributes redir){

        User userExists = managerService.findByEmail(user.getEmail());

        if (userExists != null) {
            model.addObject("alreadyRegisteredMessage",
                    "Oops! There is already a user registered with the email provided.");
            model.setViewName("manager/add");
            bindingResult.reject("email");
            return model;
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("manager/add");
            return model;
        }
        user.setEnabled(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        managerService.save(user, Role.MANAGER);
        redir.addFlashAttribute("successMessage", "User " + user.getUserName() + " "+ user.getEmail() + " created successfully");
        model.setViewName("redirect:/managers");

        return model;
    }

    @RequestMapping(value = "/manager-delete-{id}", method = RequestMethod.GET)
    public ModelAndView deleteManager(@PathVariable("id") Long managerId, ModelAndView model, RedirectAttributes redir){

        User manager = managerService.findById(managerId);
        managerService.deleteById(managerId);
        redir.addFlashAttribute("successMessage", "User " + manager.getUserName() + " "+ manager.getEmail() + " deleted successfully");
        model.setViewName("redirect:/managers");
        return model;
    }

    @RequestMapping(value = "/managers-delete-all", method = RequestMethod.GET)
    public ModelAndView deleteAllManagers(ModelAndView model, RedirectAttributes redir){

        managerService.deleteAllByRole(Role.MANAGER);
        redir.addFlashAttribute("successMessage", "All users deleted successfully");
        model.setViewName("redirect:/managers");

        return model;
    }
}

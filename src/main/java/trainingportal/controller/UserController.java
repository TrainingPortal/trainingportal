package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainingportal.model.User;
import trainingportal.service.UserService;
import trainingportal.validators.UserValidator;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String addUser(Model model){

        model.addAttribute("user",new User());

        return "user/registration";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || !UserValidator.correct(user,userService)) {
           model.addAttribute("message", UserValidator.Check(user,userService));
           return "/user/registration";
        }
        userService.saveUser(user);
        model.addAttribute("message", UserValidator.success(user));

        User createdUser = userService.findByEmail(user.getEmail());

        userService.setDefaultRole(createdUser.getUserId());

        model.addAttribute("user",createdUser);

        return "/user/registration";
    }
}
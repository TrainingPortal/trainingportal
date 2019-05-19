package trainingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springemail.Sender;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.UserService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class UserController {


    public final Sender mailSender;

//      private static final String SERVER_NAME = "http://localhost:8080";
    private static final String SERVER_NAME = "http://onetrainingportal-env.w6ev2hpcfm.eu-west-2.elasticbeanstalk.com";

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(@Qualifier("getJavaMailSender") Sender mailSender, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Return registration form template
    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/registration");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult) {

        User userExists = userService.findByEmail(user.getEmail());

        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage",
                    "Oops! There is already a user registered with the email provided.");
            modelAndView.setViewName("user/registration");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/registration");
        } else { // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email
            user.setEnabled(0);

            // Generate random 36-character string token for confirmation link
            user.setToken(UUID.randomUUID().toString());
            //Encode provided password
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoleId(Role.EMPLOYEE);

            userService.save(user);

            mailSender.sendMail(user.getEmail(),
                    "Registration Confirmation",
                    "To confirm your e-mail address, please click the link below:\n"
                            + SERVER_NAME + "/confirmation?token=" + user.getToken());

            modelAndView.addObject("confirmationMessage",
                    "A confirmation e-mail has been sent to " + user.getEmail());
            modelAndView.setViewName("user/registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findByToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("errorMessage",
                    "Oops! This is an invalid confirmation link.");
        } else { // Token found
            user.setEnabled(1);
            userService.confirmRegister(user);
            userService.resetToken(user);

            modelAndView.addObject("successMessage", "Hello, " + user.getUserName()
                    + ", your account has been activated. You can login now.");
        }
        modelAndView.setViewName("user/confirmation");
        return modelAndView;
    }

    @GetMapping("/forgotpassword")
    public ModelAndView forgotPasswordPage(ModelAndView modelAndView, User user) {

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/forgotpassword");

        return modelAndView;
    }

    @PostMapping("/forgotpassword")
    public ModelAndView processForgotPassword(ModelAndView modelAndView, @Valid User user,
                                              BindingResult bindingResult) {

        User userExists = userService.findByEmail(user.getEmail());

        if (userExists == null) {

            modelAndView.addObject("wrongEmail", "Couldn't find your email");
            modelAndView.setViewName("user/forgotpassword");
        } else {
            String token = UUID.randomUUID().toString();
            userService.updateToken(userExists,token);

            userExists.setToken(token);

            mailSender.sendMail(user.getEmail(),
                    "Password recovery",
                    "To change your password, please click the link below:\n"
                    + SERVER_NAME + "/changepassword?token=" + userExists.getToken());

            modelAndView.addObject("sendSuccess",
                    "E-mail for password reset has been sent to " + user.getEmail());
            modelAndView.setViewName("user/forgotpassword");
        }
        return modelAndView;
    }

    @GetMapping("/changepassword")
    public ModelAndView showChangePassword(ModelAndView modelAndView, @RequestParam("token") String token){

        User user = userService.findByToken(token);

        if(user == null){
            modelAndView.addObject("errorMessage",
                    "Oops! This is an invalid confirmation link.");
        } else {
            user.setPassword("");
            modelAndView.addObject("token", user.getToken());
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("user/changepassword");
        return modelAndView;
    }

    @PostMapping("changepassword")
    public ModelAndView changePassword(ModelAndView modelAndView,
                                       @Valid User user, BindingResult bindingResult, RedirectAttributes redir){

        if(bindingResult.hasFieldErrors("password")){
            redir.addFlashAttribute("errorMessage",
                    "Nu ne! You can't make your password empty or shorter than 3 symbols!");
            modelAndView.setViewName("redirect:changepassword?token=" + user.getToken());
            return modelAndView;
        } else {

            //Encode provided password
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.setNewPassword(user.getPassword(), user.getToken());

            userService.resetToken(user);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;
        }
    }
}
package trainingportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.InfoDesk;
import trainingportal.service.InfoDeskService;
import trainingportal.service.UserService;

import java.util.List;

@Controller
public class InfoDeskController {

    @Autowired
    private InfoDeskService infoDeskService;

    @Autowired
    private UserService userService;

    private static final int ROWS_PER_PAGE = 10;

    @RequestMapping("/show_users_with_request/{page}")
    public ModelAndView showListOfUsersThatNeedHelp(@PathVariable("page") int page, Long infoDeskStatusId,
                                                    ModelAndView modelAndView) {
        List<InfoDesk> listOfUsersWithRequest = infoDeskService.getListOfUsersThatNeedHelp(page, ROWS_PER_PAGE);
        for (InfoDesk infoDesk : listOfUsersWithRequest) {
            infoDesk.setSenderName(userService.findById(infoDesk.getEmployeeId()));
            infoDesk.setQuestionStatus(infoDeskService.findStatusById(infoDesk.getInfoDeskStatusId()));
        }

        modelAndView.addObject("pages", infoDeskService.getPages(infoDeskStatusId, ROWS_PER_PAGE));
        modelAndView.addObject("infoDeskStatusId", infoDeskStatusId);
        modelAndView.addObject("listOfUsersWithRequest", listOfUsersWithRequest);
        modelAndView.addObject("currentUrl", "show_users_with_request");
        modelAndView.setViewName("infoDesk/show_users_with_request");

        return modelAndView;
    }

    @RequestMapping("/show_users_with_answer/{page}")
    public ModelAndView showListOfUsersThatHadAnswer(@PathVariable("page") int page, Long infoDeskStatusId,
                                                    ModelAndView modelAndView) {
        List<InfoDesk> listOfUsersThatHadAnswer = infoDeskService.getListOfUsersThatHadAnswer(page, ROWS_PER_PAGE);
        for (InfoDesk infoDesk : listOfUsersThatHadAnswer) {
            infoDesk.setSenderName(userService.findById(infoDesk.getEmployeeId()));
            infoDesk.setQuestionStatus(infoDeskService.findStatusById(infoDesk.getInfoDeskStatusId()));
        }

        modelAndView.addObject("pages", infoDeskService.getPages(infoDeskStatusId, ROWS_PER_PAGE));
        modelAndView.addObject("infoDeskStatusId", infoDeskStatusId);
        modelAndView.addObject("listOfUsersThatHadAnswer", listOfUsersThatHadAnswer);
        modelAndView.addObject("currentUrl", "show_users_with_answer");
        modelAndView.setViewName("infoDesk/show_users_with_answer");

        return modelAndView;
    }

    @RequestMapping("/create_request/{employeeId}")
    public ModelAndView showQuestionsListOfUser(@PathVariable("employeeId") Long employeeId,
                                            Long infoDeskStatusId,
                                                ModelAndView modelAndView) {

        List<InfoDesk> listOFQuestions = infoDeskService.getQuestionsListOfUser(employeeId);
        for (InfoDesk infoDesk : listOFQuestions) {
            infoDesk.setSenderName(userService.findById(infoDesk.getEmployeeId()));
            infoDesk.setQuestionStatus(infoDeskService.findStatusById(infoDesk.getInfoDeskStatusId()));
        }
        modelAndView.addObject("infoDeskStatusId", infoDeskStatusId);
        modelAndView.addObject("listOFQuestions", listOFQuestions);
        modelAndView.addObject("currentUrl", "create_request");
        modelAndView.setViewName("infoDesk/create_request");

        return modelAndView;
    }

    @RequestMapping(value = "/request-add-{infoDeskStatusId}-{employeeId}", method = RequestMethod.GET)
    public ModelAndView addRequest(@PathVariable ("employeeId") Long employeeId, @PathVariable Long infoDeskStatusId,
                                    ModelAndView modelAndView) {

        InfoDesk infoDesk = new InfoDesk();
        infoDesk.setEmployeeId(employeeId);
        infoDesk.setInfoDeskStatusId(infoDeskStatusId);

        modelAndView.addObject("infoDesk", infoDesk);
        modelAndView.setViewName("infoDesk/request_add");
        return modelAndView;
    }

    @RequestMapping(value = "request-save", method = RequestMethod.POST)
    public ModelAndView saveRequest (@RequestParam("employeeId") Long employeeId,
                                     InfoDesk infoDesk, ModelAndView modelAndView) {

        infoDeskService.save(infoDesk);
        modelAndView.setViewName("redirect:/create_request/" + employeeId);
        return modelAndView;
    }

    @RequestMapping(value = {"/request-edit-{infoDeskStatusId}-{employeeId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView editInfoDeskBase(@PathVariable("infoDeskStatusId") Long infoDeskStatusId,
                                         @PathVariable("employeeId") Long employeeId,
                                         @PathVariable("id") Long id, ModelAndView modelAndView) {


        InfoDesk infoDesk = infoDeskService.findById(id);
        infoDesk.setInfoDeskStatusId(infoDeskStatusId);

        modelAndView.addObject("infoDesk", infoDesk);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("infoDesk/request_edit");
        return modelAndView;
    }

    @RequestMapping(value = {"/request-edit-{infoDeskStatusId}-{employeeId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView editInfoDesk (@PathVariable("infoDeskStatusId") Long infoDeskStatusId,
                                      @PathVariable("employeeId") Long employeeId,
                                      @PathVariable("id") Long id,
                                      InfoDesk infoDesk, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("id", id);
            modelAndView.addObject("employeeId", employeeId);
            modelAndView.addObject("infoDeskStatusId", infoDeskStatusId);
            modelAndView.setViewName("infoDesk/request_edit");
            return modelAndView;
        } else {
            infoDeskService.update(infoDesk);
            modelAndView.setViewName("redirect:/show_users_with_answer/1/");
            return modelAndView;
        }
    }

    @RequestMapping(value = {"/reopen-request-{infoDeskStatusId}-{employeeId}-{id}"}, method = RequestMethod.GET)
    public ModelAndView reopenBase(@PathVariable("infoDeskStatusId") Long infoDeskStatusId,
                                         @PathVariable("employeeId") Long employeeId,
                                         @PathVariable("id") Long id, ModelAndView modelAndView) {


        InfoDesk infoDesk = infoDeskService.findById(id);
        infoDesk.setInfoDeskStatusId(infoDeskStatusId);

        modelAndView.addObject("infoDesk", infoDesk);
        modelAndView.addObject("edit", true);
        modelAndView.setViewName("infoDesk/reopen_request");
        return modelAndView;
    }

    @RequestMapping(value = {"/reopen-request-{infoDeskStatusId}-{employeeId}-{id}"}, method = RequestMethod.POST)
    public ModelAndView reopen (@PathVariable("infoDeskStatusId") Long infoDeskStatusId,
                                      @PathVariable("employeeId") Long employeeId,
                                      @PathVariable("id") Long id,
                                      InfoDesk infoDesk, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("id", id);
            modelAndView.addObject("employeeId", employeeId);
            modelAndView.addObject("infoDeskStatusId", infoDeskStatusId);
            modelAndView.setViewName("infoDesk/reopen_request");
            return modelAndView;
        } else {
            infoDeskService.update(infoDesk);
            modelAndView.setViewName("redirect:/create_request/" + employeeId);
            return modelAndView;
        }
    }
}

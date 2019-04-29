package trainingportal.controller;

import java.util.List;

import trainingportal.dao.GroupDAO;
import trainingportal.form.CreateGroupForm;
import trainingportal.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vitalii Chernetskyi
 */
@Controller
public class GroupController {
    
    @Autowired
    private GroupDAO groupDAO;
    
    @RequestMapping(value = "/tempGroupsPage", method = RequestMethod.GET)
    public String showGroups(Model model){
        List<Group> list = groupDAO.getGroups();
        
        model.addAttribute("groupInfos", list);
        
        return "tempGroupsPage";
    }
    
    @RequestMapping(value ="/tempCreateGroupPage", method = RequestMethod.GET)
    public String viewCreateGroupPage(Model model) {
 
        CreateGroupForm form = new CreateGroupForm("Group1", 4);
 
        model.addAttribute("createGroupForm", form);
 
        return "tempCreateGroupPage";
    }
    
    @RequestMapping(value = "/tempCreateGroupPage", method = RequestMethod.POST)
    public String processCreateGroup(Model model, CreateGroupForm createGroupForm) {
 
        System.out.println("Create Group::" + createGroupForm.getGroupName());
 
        
        groupDAO.createGroup(createGroupForm.getGroupName(),
                    createGroupForm.getGroupCapacity());
        
        return "redirect:/tempCreateGroupPage";
    }
    
}

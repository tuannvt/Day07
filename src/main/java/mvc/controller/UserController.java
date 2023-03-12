package mvc.controller;

import mvc.model.Gender;
import mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private static final String[] countries = {"Viet Nam", "United States","Germany"};
    @RequestMapping(value = "/register")
    public String showRegisForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("countries", countries);
        return "regisForm/userForm";
    }
    @RequestMapping(value = "/result")
    public String processUser(User user){
        return "regisForm/userResult";
    }
}

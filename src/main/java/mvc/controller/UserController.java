package mvc.controller;

import mvc.model.Gender;
import mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String processUser(User user, HttpSession session){
        // Save data to session
        session.setAttribute("username", user.getName());
        return "regisForm/userResult";
    }

    @RequestMapping(value = "/session-test")
    public String showSessionTest(Model model, HttpServletRequest request){
        // get data from session
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username", username);
        return "regisForm/session";
    }

    @RequestMapping(value = "/remove-session")
    public String removeSession(Model model, HttpServletRequest request){
        // remove data from session
        request.getSession().removeAttribute("username");

        return "redirect:/sessions";
    }
}

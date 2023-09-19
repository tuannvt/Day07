package mvc.controller;


import mvc.Entity.Gender;
import mvc.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String index() {
			return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String greeting(@RequestParam(name = "person") String person,
                           @RequestParam(name = "country", required = false, defaultValue = "Viet Nam") String country,
                           Model model) {
       model.addAttribute("peronName", person);
	   model.addAttribute("country", country);

		return "helloWorld/greeting";
	}
	private static final String[] countries={"VietNam","United States","Germa-ny"};
	@RequestMapping(value ="/register")
	public String showRegistrationForm(Model model){
		model.addAttribute("user",new User());
		model.addAttribute("genders", Gender.values());
		model.addAttribute("countries",countries);
		return "helloWorld/userForm";
	}
	@RequestMapping(value = "/result")
	public String processUser(User user){
		return "helloWorld/userResul";
	}
}

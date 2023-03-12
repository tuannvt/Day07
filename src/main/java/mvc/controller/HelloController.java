package mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mvc.repository.BookRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

	@RequestMapping("/index")
	public String index() {
			return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String greeting(@RequestParam(name = "person") String person,
                           @RequestParam(name = "country", required = false, defaultValue = "Viet Nam") String country,
                           Model model) {
      //  model.addAttribute("peronName", person);
        //model.addAttribute("country", country);

		return "Hello " + person + " from " + country;
	}
	@RequestMapping(value = "/list_student", method = RequestMethod.GET)
	public List<String> hello(ModelMap modelMap) {
		System.out.println("hihi");
		List<String> listStudent = new ArrayList<>();
		listStudent.add("ehhehe");
		return listStudent;
	}


}

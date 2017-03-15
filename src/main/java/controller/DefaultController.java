package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.UserModel;

@Controller
public class DefaultController {

	@ModelAttribute("UM")
	public UserModel init() {
		return new UserModel();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginGet() {
		return "toppage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String loginPost() {
		return "toppage";
	}
}
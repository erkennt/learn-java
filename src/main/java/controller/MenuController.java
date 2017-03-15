package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {

		Object sessionCheck = session.getAttribute("session");
		if(sessionCheck == null){
			return "redirect:/";
		}

		return "menu";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String loginPost(HttpSession session) {
		Object sessionCheck = session.getAttribute("session");
		if(sessionCheck == null){
			return "redirect:/";
		}

		return "menu";
	}



	// ログアウト (Get)
	@RequestMapping(value = "/menu/logout", method = RequestMethod.GET)
	public String logoutGet(HttpSession session) {
		Object sessionCheck = session.getAttribute("session");
		if(sessionCheck == null){
			return "redirect:/";
		}

		return "menu";
	}

	// ログアウト (Post)
	@RequestMapping(value = "/menu/logout", method = RequestMethod.POST)
	public String logoutPost(HttpSession session) {
		// Sessionを破棄
		session.invalidate();
		return "redirect:/";
	}

}
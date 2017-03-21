package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserDAO;
import model.GameSettingsModel;
import model.UserModel;

@Controller
public class MenuController {

	@ModelAttribute("Game")
	GameSettingsModel init() {
        return new GameSettingsModel();
    }


	@Autowired
	private UserDAO userDAO;



	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String loginGet(HttpSession session, UserModel userModel) {

		Object sessionCheck = session.getAttribute("session");
		if(sessionCheck == null){
			return "redirect:/";
		}
		return "menu";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute("Game") GameSettingsModel gameSettingsModel, UserModel userModel,
			Model model, HttpSession session) {
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
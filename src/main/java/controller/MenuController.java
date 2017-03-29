package controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.GameSettingsModel;

@Controller
public class MenuController {

	@ModelAttribute("Game")
	GameSettingsModel initGame() {
		return new GameSettingsModel();
	}


	@RequestMapping(value = "/menu", method = { GET, POST })
	public String loginPost(@ModelAttribute("Game") GameSettingsModel gameSettingsModel,
		HttpSession session) {

		return "menu";
	}

	// ログアウト (Get)
	@RequestMapping(value = "/menu/logout", method = RequestMethod.GET)
	public String logoutGet(HttpSession session) {
		if (session.getAttribute("session") == null) {
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
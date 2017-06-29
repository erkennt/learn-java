package learn.second.app.common.top;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Users;
import learn.second.domain.service.cards.CardsService;
import learn.second.domain.service.security.CommonUsers;
import learn.second.domain.service.users.UsersService;

@Controller
public class TopController {

	@Autowired
	CardsService cardsService;
	@Autowired
	UsersService usersService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String top(@AuthenticationPrincipal CommonUsers commonUsers, Locale locale, Model model) {
		Map<String, Object> mapDonuts = cardsService.getTopPageItems("Donuts");
		Map<String, Object> mapHighLow = cardsService.getTopPageItems("HighLow");

		model.addAttribute("mapDonuts", mapDonuts);
		model.addAttribute("mapHighLow", mapHighLow);

		return "/common/login";
	}

	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String login(@AuthenticationPrincipal CommonUsers commonUsers, Locale locale, Model model) {

		return "/common/login";
	}

	@RequestMapping(value = "/common/game", method = { RequestMethod.GET, RequestMethod.POST })
	public String game(@AuthenticationPrincipal CommonUsers commonUsers, Locale locale, Model model,
			HttpSession session) {
		Users loginUser = commonUsers.getUser();
		Users users = usersService.getUser(loginUser.getUserId());

		model.addAttribute(users);

		return "/common/game";
	}

	@RequestMapping(value = "/common/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) {
		return "/common/user";
	}

	@RequestMapping(value = "/common/error", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		return "/common/error";
	}

	@RequestMapping(value = "/common/403", method = RequestMethod.GET)
	public String permission(Locale locale, Model model) {
		return "/common/403";
	}
}
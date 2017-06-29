package learn.second.app.admin.top;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Admin;
import learn.second.domain.service.admin.AdminService;
import learn.second.domain.service.security.AdminUsers;

@Controller
public class AdminTopController {

	@Autowired
	AdminService adminService;

	@ModelAttribute("Admin")
	Admin init() {
		return new Admin();
	}

	@RequestMapping(value = "/admin", method = { RequestMethod.POST ,RequestMethod.GET})
	public String admin( Locale locale, Model model,
			HttpSession session) {

		// 管理ユーザーが存在するかチェック
		boolean isEnableAdminUser = adminService.userCount();

		if (isEnableAdminUser == true) {
			return "/admin/login";
		} else {
			return "redirect:/admin_regist";
		}
	}

	@RequestMapping(value = "/admin/menu", method = { RequestMethod.GET, RequestMethod.POST })
	public String game(@AuthenticationPrincipal AdminUsers adminUsers, Locale locale, Model model,
			HttpSession session) {

			Admin users = adminUsers.getUser();
			model.addAttribute(users);

			return "/admin/menu";

	}

	@RequestMapping(value = "/admin/error", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		return "/admin/error";
	}

	@RequestMapping(value = "/admin/403", method = RequestMethod.GET)
	public String permission(Locale locale, Model model) {
		return "/admin/403";
	}
}
package learn.second.app.admin.user.regist;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Admin;
import learn.second.domain.service.admin.AdminService;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.gamesettings.GameSettingsServiceImpl;

@Controller
public class RegistAdminUser {

	@Autowired
	AdminService adminService;

	@Autowired
	InitialDataSettingsService initialDataSettingsService;

	@Autowired
	GameSettingsServiceImpl gameSettingsServiceImpl;

	@ModelAttribute("Admin")
	Admin init() {
		return new Admin();
	}
	@RequestMapping(value = "/admin_regist", method = {RequestMethod.POST, RequestMethod.GET})
	public String regist(Locale locale, Model model, HttpSession session) {

		return "/admin/regist";
	}

	@RequestMapping(value = "/admin_regist_add", method = {RequestMethod.POST, RequestMethod.GET})
	public String add(@ModelAttribute("Admin") Admin AdminForm, Locale locale, Model model, HttpSession session) {

		// adminユーザーの登録
		adminService.insertAdmin(AdminForm.getAdminId(), AdminForm.getPassword());

		// 初期データの生成(固定値)
		initialDataSettingsService.insertInitialInitialDataSettingsAsset("50000");
		gameSettingsServiceImpl.insertGameSettings("Donuts", 30, 20);
		gameSettingsServiceImpl.insertGameSettings("HighLow", 30, 20);

		return "/admin/regist_comp";
	}

}

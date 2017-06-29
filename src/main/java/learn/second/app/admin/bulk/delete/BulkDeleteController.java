package learn.second.app.admin.bulk.delete;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Users;
import learn.second.domain.service.admin.AdminService;
import learn.second.domain.service.cards.CardsService;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.gamelogs.GameLogsService;
import learn.second.domain.service.gamesettings.GameSettingsService;
import learn.second.domain.service.pool.RegisterPoolService;
import learn.second.domain.service.repass.RePasswordPoolService;
import learn.second.domain.service.userdraw.UserDrawWaitService;
import learn.second.domain.service.users.UsersService;

@Controller
public class BulkDeleteController {

	@Autowired
	AdminService adminService;
	@Autowired
	CardsService cardsService;
	@Autowired
	GameLogsService gamelogsService;
	@Autowired
	GameSettingsService gameSettingsService;
	@Autowired
	InitialDataSettingsService initialDataSettingsService;
	@Autowired
	UserDrawWaitService userDrawWaitService;
	@Autowired
	UsersService usersService;
	@Autowired
	RePasswordPoolService rePasswordPoolService;
	@Autowired
	RegisterPoolService  registerPoolService;

	@RequestMapping(value = "/admin/bulk/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String menu(Locale locale, Model model, HttpSession session) {

		return "/admin/bulk/delete";
	}

	@RequestMapping(value = "/admin/bulk/delete/common", method = { RequestMethod.POST })
	public String deleteCommon(Locale locale, Model model, HttpSession session) {
		try {
			// データの削除
			gamelogsService.deleteAll();
			userDrawWaitService.deleteAll();
			userDrawWaitService.deleteAll();
			rePasswordPoolService.deleteAll();
			registerPoolService.deleteAll();
			cardsService.deleteAll();
			usersService.deleteAll();

			// シーケンス番号の初期化
			gamelogsService.resetAutoIncrement();
			cardsService.resetAutoIncrement();
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("up_msg", "Commonデータを削除しました");
		return "/admin/bulk/delete";
	}

	@RequestMapping(value = "/admin/bulk/delete/admin", method = { RequestMethod.POST })
	public String deleteAdmin(Locale locale, Model model, HttpSession session) {

		//ユーザーデータの存在チェック
		List<Users> usersList = usersService.getAll();
		if (usersList.size() < 1) {
			try {
				// データの削除
				gameSettingsService.deleteAll();
				initialDataSettingsService.deleteAll();
				adminService.deleteAll();
			} catch (Exception e) {
				System.out.println(e);
			}
			model.addAttribute("up_msg", "Adminデータを削除しました");
			return "/admin/bulk/delete_admin";
		}
		model.addAttribute("up_msg", "先にCommonデータの削除を行う必要があります");
		return "/admin/bulk/delete";
	}

}
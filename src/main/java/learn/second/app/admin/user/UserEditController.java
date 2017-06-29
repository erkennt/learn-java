package learn.second.app.admin.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Users;
import learn.second.domain.service.security.AdminUsers;
import learn.second.domain.service.users.UsersService;

@Controller
public class UserEditController {

	@Autowired
	private UsersService usersService;

	@ModelAttribute("Search")
	UserSearchForm initSearch() {
		return new UserSearchForm();
	}

	@ModelAttribute("Users")
	public UserEditForm initUsers() {
		return new UserEditForm();
	}

	@ModelAttribute("New")
	public NewPasswordForm initPass() {
		return new NewPasswordForm();
	}

	@RequestMapping(value = "/admin/user/edit/{userId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String user(@PathVariable("userId") String userId, @AuthenticationPrincipal AdminUsers adminUsers,
			Locale locale, Model model, HttpSession session) {

		UserEditForm users = usersService.getEditUser(userId);

		if (users.getUserId() == null) {
			return "/admin/user/search";
		}

		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("0", "Draft");
		statusMap.put("1", "Active");
		statusMap.put("2", "Inactive");

		// 年月日フォーマット
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		SimpleDateFormat ddf = new SimpleDateFormat("dd");

		// 年月日セット
		users.setYears(ydf.format(users.getBirthday()));
		users.setMonth(mdf.format(users.getBirthday()));
		users.setDays(ddf.format(users.getBirthday()));

		// 生年月日用
		model.addAttribute("Years", this.getYears());
		model.addAttribute("Month", this.getMonth());
		model.addAttribute("Days", this.getDays());

		model.addAttribute("statusMap", statusMap);
		model.addAttribute("Users", users);

		return "/admin/user/edit";
	}

	@RequestMapping(value = "/admin/user/edit/{userId}/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String userUpdate(@Valid@ModelAttribute("Users") UserEditForm userEditForm,
			BindingResult result,
			@PathVariable("userId") String userId,
			@AuthenticationPrincipal AdminUsers adminUsers, Locale locale, Model model, HttpSession session) {

		if (userEditForm.getUserId() == null) {
			return "/admin/user/search";
		}

		if (result.hasErrors()) {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("0", "Draft");
		statusMap.put("1", "Active");
		statusMap.put("2", "Inactive");

		// 年月日フォーマット
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		SimpleDateFormat ddf = new SimpleDateFormat("dd");



		UserEditForm users = usersService.getEditUser(userId);

		// 年月日セット
		userEditForm.setYears(ydf.format(users.getBirthday()));
		userEditForm.setMonth(mdf.format(users.getBirthday()));
		userEditForm.setDays(ddf.format(users.getBirthday()));

		// 生年月日用
		model.addAttribute("Years", this.getYears());
		model.addAttribute("Month", this.getMonth());
		model.addAttribute("Days", this.getDays());

		model.addAttribute("statusMap", statusMap);
		model.addAttribute("Users", userEditForm);
			model.addAttribute("up_msg", "エラー：値をチェックしてください。");
			return "/admin/user/edit";
		}

		String birthday = userEditForm.getYears() + userEditForm.getMonth() + userEditForm.getDays();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = null;
		try {
			date = simpleDateFormat.parse(birthday);
		} catch (ParseException e) {

		}

		Users users = usersService.getUser(userEditForm.getUserId());

		users.setNickName(userEditForm.getNickName());
		users.setMailAddress(userEditForm.getMailAddress());
		users.setAsset(userEditForm.getAsset());
		users.setBirthday(date);

		usersService.updateUsers(users);

		userEditForm = usersService.getEditUser(userId);

		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("0", "Draft");
		statusMap.put("1", "Active");
		statusMap.put("2", "Inactive");

		// 年月日フォーマット
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		SimpleDateFormat ddf = new SimpleDateFormat("dd");

		// 年月日セット
		userEditForm.setYears(ydf.format(userEditForm.getBirthday()));
		userEditForm.setMonth(mdf.format(userEditForm.getBirthday()));
		userEditForm.setDays(ddf.format(userEditForm.getBirthday()));

		// 生年月日用
		model.addAttribute("Years", this.getYears());
		model.addAttribute("Month", this.getMonth());
		model.addAttribute("Days", this.getDays());

		model.addAttribute("statusMap", statusMap);
		model.addAttribute("Users", userEditForm);
		model.addAttribute("up_msg", "情報の更新に成功しました");

		return "/admin/user/edit";
	}

	@RequestMapping(value = "/admin/user/edit/{userId}/ChangePassword", method = { RequestMethod.POST })
	public String changePassword(@ModelAttribute("New") NewPasswordForm newPasswordForm,
			@PathVariable("userId") String userId, @AuthenticationPrincipal AdminUsers adminUsers, Locale locale,
			Model model, HttpSession session) {

		model.addAttribute("Users", newPasswordForm);

		return "/admin/user/pass";
	}

	@RequestMapping(value = "/admin/user/edit/{userId}/UpdatePassword", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String userUpdatePassword(@Valid @ModelAttribute("New") NewPasswordForm newPasswordForm,
			BindingResult result, @PathVariable("userId") String userId, @AuthenticationPrincipal AdminUsers adminUsers,
			Locale locale, Model model, HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("up_msg", "エラー：値をチェックしてください。");
			return "/admin/user/pass";
		}

		UserEditForm userEditForm = usersService.getEditUser(userId);
		if (!newPasswordForm.getNewPassword().equals(newPasswordForm.getConfNewPassword())) {

			model.addAttribute("Users", userEditForm);
			model.addAttribute("up_msg", "パスワードの変更に失敗しました");

			return "/admin/user/pass";
		}

		usersService.updatePassword(userId, newPasswordForm.getNewPassword());


		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("0", "Draft");
		statusMap.put("1", "Active");
		statusMap.put("2", "Inactive");

		// 年月日フォーマット
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		SimpleDateFormat ddf = new SimpleDateFormat("dd");

		// 年月日セット
		userEditForm.setYears(ydf.format(userEditForm.getBirthday()));
		userEditForm.setMonth(mdf.format(userEditForm.getBirthday()));
		userEditForm.setDays(ddf.format(userEditForm.getBirthday()));

		// 生年月日用
		model.addAttribute("Years", this.getYears());
		model.addAttribute("Month", this.getMonth());
		model.addAttribute("Days", this.getDays());

		model.addAttribute("statusMap", statusMap);
		model.addAttribute("Users", userEditForm);
		model.addAttribute("up_msg", "パスワードを変更しました");

		return "/admin/user/edit";

	}

	@RequestMapping(value = "/admin/user/edit/{userId}/UpdateStatus", method = { RequestMethod.POST })
	public String userUpdateStatus(@ModelAttribute("Users") UserEditForm userEditForm,
			@PathVariable("userId") String userId, @AuthenticationPrincipal AdminUsers adminUsers, Locale locale,
			Model model, HttpSession session) {

		if (userEditForm.getUserId() == null) {
			return "/admin/user/search";
		}

		// ステータス更新
		String msg = "";
		switch (userEditForm.getStatus()) {
		case "0": // draft to active
			usersService.updateStatusActive(userId);
			msg = "ユーザーの認証を行いました";
			break;
		case "1": // active to inactive
			usersService.updateStatusInactive(userId);
			msg = "ユーザーを停止しました";
			break;
		case "2": // inactive to active
			usersService.updateStatusActive(userId);
			msg = "ユーザーを復旧しました";
			break;
		}

		// ユーザーデータ再読み込み
		userEditForm = usersService.getEditUser(userId);

		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("0", "Draft");
		statusMap.put("1", "Active");
		statusMap.put("2", "Inactive");

		// 年月日フォーマット
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		SimpleDateFormat ddf = new SimpleDateFormat("dd");

		// 年月日セット
		userEditForm.setYears(ydf.format(userEditForm.getBirthday()));
		userEditForm.setMonth(mdf.format(userEditForm.getBirthday()));
		userEditForm.setDays(ddf.format(userEditForm.getBirthday()));

		// 生年月日用
		model.addAttribute("Years", this.getYears());
		model.addAttribute("Month", this.getMonth());
		model.addAttribute("Days", this.getDays());

		model.addAttribute("statusMap", statusMap);
		model.addAttribute("Users", userEditForm);
		model.addAttribute("up_msg", msg);

		return "/admin/user/edit";
	}

	// 年
	public List<String> getYears() {
		int startYear = 1900;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int lastYear = Integer.parseInt(sdf.format(new Date()));

		List<String> list = new ArrayList<String>();
		for (int start = startYear; start <= lastYear; start++) {
			list.add(String.valueOf(start));
		}

		return list;
	}

	// 月
	public List<String> getMonth() {
		List<String> list = new ArrayList<String>();
		for (int start = 1; start <= 12; start++) {
			list.add(String.format("%02d", start));
		}

		return list;
	}

	// 日
	public List<String> getDays() {
		List<String> list = new ArrayList<String>();
		for (int start = 1; start <= 31; start++) {
			list.add(String.format("%02d", start));
		}

		return list;
	}

}

package learn.second.app.common.info;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import learn.second.app.common.regist.RegistForm;
import learn.second.domain.model.Users;
import learn.second.domain.service.security.CommonUsers;
import learn.second.domain.service.users.UsersService;

@Controller
public class InformationChangeController {

	@Autowired
	UsersService usersService;

	@ModelAttribute("Users")
	public RegistForm initUsers() {
		return new RegistForm();
	}

	@ModelAttribute("Name")
	public NewNickNameForm initNickName() {
		return new NewNickNameForm();
	}

	@ModelAttribute("Mail")
	public NewMailAddressForm initMailAddress() {
		return new NewMailAddressForm();
	}

	@ModelAttribute("Pass")
	public NewPasswordForm initPassword() {
		return new NewPasswordForm();
	}

	@RequestMapping(value = "/common/info/menu", method = { POST })
	public String menu(@ModelAttribute("Users") RegistForm userModel, Model model, BindingResult result,
			HttpServletRequest request) throws MessagingException {
		return "/common/info/menu";
	}

	@RequestMapping(value = "/common/info/info", method = { GET, POST })
	public String info(@AuthenticationPrincipal CommonUsers commonUsers, @ModelAttribute("Users") RegistForm userModel,
			Model model, HttpServletRequest request) throws MessagingException {
		Users users = commonUsers.getUser();
		model.addAttribute("Users", users);
		return "/common/info/info";
	}

	@RequestMapping(value = "/common/info/name", method = { POST })
	public String name(@AuthenticationPrincipal CommonUsers commonUsers, HttpServletRequest request)
			throws MessagingException {
		return "/common/info/name";
	}

	@RequestMapping(value = "/common/info/name/update", method = { POST })
	public String updateName(@Valid @ModelAttribute("Name") NewNickNameForm newNickNameForm, BindingResult result,
			@AuthenticationPrincipal CommonUsers commonUsers, Model model, HttpServletRequest request)
			throws MessagingException {

		Users users = usersService.getUsersByUserId(commonUsers.getUsername());

		if (!users.getNickName().equals(newNickNameForm.getNickName())) {
			if (result.hasErrors()) {

				model.addAttribute("up_msg", "更新に失敗しました");
				return "/common/info/name";
			}
		}
		users.setNickName(newNickNameForm.getNickName());
		usersService.updateUsers(users);

		model.addAttribute("up_msg", "ニックネームを更新しました");
		commonUsers.reloadUser(users);
		model.addAttribute("Users", users);
		return "/common/info/info";
	}

	// BindingResult result,

	@RequestMapping(value = "/common/info/pass", method = { POST })
	public String password(@AuthenticationPrincipal CommonUsers commonUsers, Model model, HttpServletRequest request)
			throws MessagingException {

		return "/common/info/pass";
	}

	@RequestMapping(value = "/common/info/pass/update", method = { POST })
	public String passwordUpdate(@Valid @ModelAttribute("Pass") NewPasswordForm newPasswordForm, BindingResult result,
			@AuthenticationPrincipal CommonUsers commonUsers, Model model, HttpServletRequest request)
			throws MessagingException {

		// Vallidate
		if (result.hasErrors()) {

			model.addAttribute("up_msg", "更新に失敗しました");
			return "/common/info/pass";
		}
		// 現在のパスワードをハッシュ化し合致チェック
		// Users users =
		// usersService.getUsersByPassword(commonUsers.getUsername(),
		// newPasswordForm.getPassword());

		// if(users == null){
		// model.addAttribute("up_msg", "更新に失敗しました");
		// return "/common/info/pass";
		// }

		// 新たなパスワードの合致チェック
		if (!newPasswordForm.getNewPassword().equals(newPasswordForm.getConfNewPassword())) {

			model.addAttribute("up_msg", "更新に失敗しました");
			return "/common/info/pass";
		}

		// パスワード更新
		usersService.updatePassword(commonUsers.getUsername(), newPasswordForm.getNewPassword());

		Users users = usersService.getUsersByUserId(commonUsers.getUsername());
		commonUsers.reloadUser(users);
		model.addAttribute("up_msg", "パスワードを更新しました");
		model.addAttribute("Users", users);
		return "/common/info/info";
	}

	@RequestMapping(value = "/common/info/mail", method = { POST })
	public String mail(@AuthenticationPrincipal CommonUsers commonUsers, @ModelAttribute("Users") RegistForm userModel,
			Model model, BindingResult result, HttpServletRequest request) throws MessagingException {
		// フォームデータのValidate
		return "/common/info/mail";
	}

	@RequestMapping(value = "/common/info/mail/update", method = { POST })
	public String updateMail(@Valid @ModelAttribute("Mail") NewMailAddressForm newMailAddressForm, BindingResult result,
			@AuthenticationPrincipal CommonUsers commonUsers, Model model, HttpServletRequest request)
			throws MessagingException {
		// フォームデータのValidate
		Users users = usersService.getUsersByUserId(commonUsers.getUsername());


		if (users == null) {
			model.addAttribute("up_msg", "更新に失敗しました");
			return "/common/info/mail";
		}

		if (!users.getMailAddress().equals(newMailAddressForm.getMailAddress())) {
			if (result.hasErrors()) {

				model.addAttribute("up_msg", "更新に失敗しました");
				return "/common/info/mail";
			}
		}
		users.setMailAddress(newMailAddressForm.getMailAddress());

		usersService.updateUsers(users);

		commonUsers.reloadUser(users);
		model.addAttribute("up_msg", "メールアドレスを更新しました");
		model.addAttribute("Users", users);
		return "/common/info/info";
	}

}

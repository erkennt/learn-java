package learn.second.app.common.support;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.Url.QueryParameter;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import learn.second.domain.model.RePasswordPool;
import learn.second.domain.model.Users;
import learn.second.domain.service.mail.MailService;
import learn.second.domain.service.repass.RePasswordPoolService;
import learn.second.domain.service.security.CryptionPageParametersEncoder;
import learn.second.domain.service.users.UsersService;

@Controller
@SessionAttributes("Users")
public class UserSupport {

	@Autowired
	private MailService mailService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private RePasswordPoolService rePasswordPoolService;

	@ModelAttribute("Support")
	private UserSupportForm supportInit() {
		return new UserSupportForm();
	}

	@ModelAttribute("ChangePassword")
	private ChangePasswordForm changeInit() {
		return new ChangePasswordForm();
	}

	@RequestMapping(value = "/support", method = { RequestMethod.GET, RequestMethod.POST })
	public String menu(Locale locale, Model model) {
		return "/common/support/menu";
	}

	@RequestMapping(value = "/support/userid", method = { RequestMethod.GET, RequestMethod.POST })
	public String userId(Locale locale, Model model) {

		return "/common/support/userid";
	}

	@RequestMapping(value = "/support/password", method = { RequestMethod.GET, RequestMethod.POST })
	public String password(Locale locale, Model model) {
		return "/common/support/password";
	}

	@RequestMapping(value = "/support/{type}/confirm", method = { RequestMethod.POST })
	public String verify(@ModelAttribute("Support") UserSupportForm userSupportForm, @PathVariable("type") String type,
			Locale locale, Model model, HttpServletRequest request) {

		boolean isEnableUserId = false;
		boolean isEnableEmail = false;

		Users users;
		String userId;
		String mailAddress;

		switch (type) {
		case "userid":

			// メールアドレスの確認
			users = usersService.getUsersByEmail(userSupportForm.getMailAddress());
			if (users != null) {

				// メール送信
				try {
					mailService.doSendUserIdRemaindEmail(users.getUserId(), users.getMailAddress());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			} else {
				// 異なります
				model.addAttribute("error_msg", "登録されていません。");

				// 前画面にフォワード
				return "/common/support/" + type;
			}
			break;

		case "password":

			users = usersService.getUsersByUserIdAndEmail(userSupportForm.getUserId(),
					userSupportForm.getMailAddress());
			if (users != null) {
				// 入力値の確認
				CryptionPageParametersEncoder enc = new CryptionPageParametersEncoder();

				PageParameters parameters = new PageParameters();

				double num = Math.random();

				parameters.add("parameter", num);
				Url param = enc.encodePageParameters(parameters);

				// URL生成
				String requestUrl = request.getRequestURL().toString();
				String requestServletPath = request.getServletPath();

				String url = requestUrl.replace(requestServletPath, "/support/password/change");
				url += param.toString();

				// パラメータ登録
				rePasswordPoolService.insertRePasswordPool(param.toString(), users.getUserId());

				// メール送信
				try {
					mailService.doSendPasswordRemaindEmail(users.getUserId(), users.getMailAddress(), url.toString());
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			} else {
				// 異なります
				model.addAttribute("error_msg", "その組み合わせでは登録されていません。");
				// 前画面にフォワード
				return "/common/support/" + type;
			}
			break;

		default:
			// パラメータが不正です
			break;
		}

		return "/common/support/mail";
	}

	@RequestMapping(value = "/support/password/change", method = { RequestMethod.POST })
	public String changePassword(Locale locale, Model model, HttpServletRequest req) {

		Url url = requestUrl(req.getParameterMap());
		RePasswordPool rePasswordPool = rePasswordPoolService.getRePasswordPool(url.toString());

		// パラメータが登録されてなければ処理しない
		if (rePasswordPool == null) {

			model.addAttribute("msg_up", "利用済みか無効なパラメータです");
			model.addAttribute("parameter", url.toString());
			return "/common/support/change";
		}

		Users users = usersService.getUsersByUserId(rePasswordPool.getUserId());

		model.addAttribute("Users", users);

		model.addAttribute("parameter", url.toString());
		return "/common/support/change";
	}

	@RequestMapping(value = "/support/password/update", method = { RequestMethod.POST })
	public String updatePassword(@RequestParam(required = true) String parameter,
			@Valid @ModelAttribute("ChangePassword") ChangePasswordForm changePasswordForm, BindingResult result,
			@ModelAttribute("Users") Users users, Locale locale, Model model, HttpServletRequest req) {

		// 情報のチェック
		if (result.hasErrors()) {

			model.addAttribute("msg", "入力に誤りがあります");

			model.addAttribute("parameter", parameter);
			return "/common/support/change";

		} else if (users != null) {
			if (changePasswordForm.getPassword1().equals(changePasswordForm.getPassword2())) {

				// パスワードを更新する
				usersService.updatePassword(users.getUserId(), changePasswordForm.getPassword1());
				// RegisterPoolのデータを削除
				rePasswordPoolService.deleteRePasswordPool(parameter);

				return "/common/support/update";
			}
		}
		model.addAttribute("msg_up", "更新に失敗しました");

		model.addAttribute("parameter", parameter);
		return "/common/support/change";
	}

	private Url requestUrl(Map<String, String[]> map) {
		Url url = new Url();
	for (String key : map.keySet()) {

			QueryParameter param = new QueryParameter(key, map.get(key)[0]);
			url.getQueryParameters().add(param);
		}
		return url;
	}

}
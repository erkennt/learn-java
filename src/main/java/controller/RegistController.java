package controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.InitialDataSettingsDAO;
import dao.MailAuthenticationDAO;
import dao.UserDAO;
import mail.MailService;
import model.InitialDataSettingsModel;
import model.MailAuthenticationModel;
import model.UserModel;

@Controller
@SessionAttributes("UM")
public class RegistController {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private InitialDataSettingsDAO initialDataSettingsDAO;

	@Autowired
	private MailAuthenticationDAO mailAuthenticationDAO;

	@Autowired
	private MailService mailService;

	@ModelAttribute("UM")
	public UserModel init() {
		return new UserModel();
	}
	// modelのBirthdayに値を代入
	@InitBinder("UM")
	public void bindUser(WebDataBinder binder, HttpServletRequest request) {
		// SelectBoxが全て選択されている場合に代入する
		if (!StringUtils.isEmpty(request.getParameter("Years")) && !StringUtils.isEmpty(request.getParameter("Month"))
				&& !StringUtils.isEmpty(request.getParameter("Days"))) {

			String birthday = request.getParameter("Years") + request.getParameter("Month")
					+ request.getParameter("Days");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			Date date = null;
			try {
				date = simpleDateFormat.parse(birthday);
			} catch (ParseException e) {

			}

			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.add("Birthday", date);
			binder.bind(pvs);
		}
	}

	@RequestMapping(value = "/regist", method = { GET, POST })
	public String loginPost(@ModelAttribute("UM") UserModel userModel, Model model) {

		// 生年月日用
		model.addAttribute("Years", this.years());
		model.addAttribute("Month", this.month());
		model.addAttribute("Days", this.days());

		return "regist";
	}

	// ユーザー登録確認 (Get)
	@RequestMapping(value = "/regist/add", method = RequestMethod.GET)
	public String userAddGet() {
		return "redirect:/";

	}
	// ユーザー登録確認 (Post)

	@RequestMapping(value = "/regist/add", method = RequestMethod.POST)
	public String userAddPost(@Valid @ModelAttribute("UM") UserModel userModel, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("msg", "エラー：値をチェックしてください。");

			// 生年月日用
			model.addAttribute("Years", this.years());
			model.addAttribute("Month", this.month());
			model.addAttribute("Days", this.days());
			return "regist";
		} else {

			return "regist_confirm";
		}

	}

	// ユーザー登録完了 (Get)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.GET)
	public String userAddCompleteGet() {
		return "redirect:/";
	}

	// ユーザー登録完了 (Post)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.POST)
	public String userAddCompletePost(@Valid @ModelAttribute("UM") UserModel userModel, BindingResult result,
			Model model, HttpSession session) {

		return "regist_complete";

	}

	// 仮登録
	@RequestMapping(value = "/regist/temp", method = { GET, POST })
	public String registTemp(@Valid @ModelAttribute("UM") UserModel userModel, Model model, BindingResult result,
			HttpServletRequest request) throws MessagingException {

		// 情報のチェック
		if (result.hasErrors()) {

			model.addAttribute("msg", "登録に失敗しました");

			return "regist";

		} else {

			// 初期値設定
			List<InitialDataSettingsModel> initialAssetList = initialDataSettingsDAO.getInitialAsset("Asset");
			long initialAsset = 0;
			for (InitialDataSettingsModel list : initialAssetList) {
				initialAsset = Long.parseLong(list.getValue());
			}
			userModel.setAsset(initialAsset);
	
			// 仮登録実行
			userDAO.userInsert(userModel);

			// AuthenticationPoolへの登録
			mailAuthenticationDAO.addAuthenticationPool(userModel.getUserId(), userModel.getMailAddress());




			// URL生成 ID + MailAddress
			String requestUrl = request.getRequestURL().toString();
			String requestServletPath = request.getServletPath();

			String url = requestUrl.replace(requestServletPath, "/regist/authentication?");
			String param = "UserId=" + userModel.getUserId() + "&" + "MailAddress=" + userModel.getMailAddress();
			url += param;

			// メール送信
			mailService.doSendUserAuthenticationEmail(userModel.getNickName(), userModel.getMailAddress(), url);

			return "regist_temporary";

		}
	}

	// 登録完了
	@RequestMapping(value = "/regist/authentication", method = { GET, POST })
	public String registAuth(@RequestParam("UserId") String userId, @RequestParam("MailAddress") String mailAddress,
			@ModelAttribute("UM") UserModel userModel, Model model) {

		// ユーザー照会
		List<MailAuthenticationModel> authenticationList = mailAuthenticationDAO.searchAuthenticationPool(mailAddress,
				userId);

		if (authenticationList.size() <= 0) {

			model.addAttribute("msg", "認証に失敗しました<br>URLが正しく入力されているか確認してください");
			return "regist_error";
		} else if (authenticationList.get(0).isStatus() == Boolean.TRUE) {
			model.addAttribute("msg", "既に本登録が完了しています。");
			return "regist_error";
		}

		// Authenticationステータス更新
		mailAuthenticationDAO.updateStatus(mailAddress);

		// ユーザーステータス更新
		userDAO.updateActive(userId);

		// ユーザーデータの再取得
		model.addAttribute("UM", userDAO.getUserInformation(userId).get(0));

		return "regist_complete";
	}

	// 年
	private List<String> years() {
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
	private List<String> month() {
		List<String> list = new ArrayList<String>();
		for (int start = 1; start <= 12; start++) {
			list.add(String.format("%02d", start));
		}

		return list;
	}

	// 日
	private List<String> days() {
		List<String> list = new ArrayList<String>();
		for (int start = 1; start <= 31; start++) {
			list.add(String.format("%02d", start));
		}

		return list;
	}

}
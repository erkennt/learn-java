package learn.second.app.common.regist;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.Url.QueryParameter;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import learn.second.domain.model.RegisterPool;
import learn.second.domain.model.Users;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.mail.MailService;
import learn.second.domain.service.pool.RegisterPoolService;
import learn.second.domain.service.security.CryptionPageParametersEncoder;
import learn.second.domain.service.users.UsersService;
import learn.second.domain.service.validate.PasswordEqualsValidator;

@Controller
@SessionAttributes("UM")
public class RegistController {

	@Autowired
	UsersService usersService;

	@Autowired
	InitialDataSettingsService initialDataSettingsService;

	@Autowired
	RegisterPoolService registerPoolService;

	@Autowired
	MailService mailService;

	@Autowired
	PasswordEqualsValidator passwordEqualsValidator;

	@ModelAttribute("UM")
	public RegistForm init() {
		return new RegistForm();
	}

	static List<String> Years = new ArrayList<String>();
	{
		Years = this.getYears();
	}
	static List<String> Month = new ArrayList<String>();
	{
		Month = this.getMonth();
	}
	static List<String> Days = new ArrayList<String>();
	{
		Days = this.getDays();
	}

	static void birthday() {
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(passwordEqualsValidator);
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
	public String loginPost(@ModelAttribute("UM") RegistForm userModel, Model model) {

		// 生年月日用
		model.addAttribute("Years", Years);
		model.addAttribute("Month", Month);
		model.addAttribute("Days", Days);

		return "/common/regist/regist";
	}

	// ユーザー登録確認 (Get)
	@RequestMapping(value = "/regist/add", method = RequestMethod.GET)
	public String userAddGet() {
		return "redirect:/";

	}
	// ユーザー登録確認 (Post)

	@RequestMapping(value = "/regist/add", method = RequestMethod.POST)
	public String userAddPost(@Valid @ModelAttribute("UM") RegistForm userModel, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("msg", "エラー：値をチェックしてください。");

			// 生年月日用
			model.addAttribute("Years", Years);
			model.addAttribute("Month", Month);
			model.addAttribute("Days", Days);
			return "/common/regist/regist";
		} else {

			return "/common/regist/regist_confirm";
		}

	}

	// ユーザー登録完了 (Get)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.GET)
	public String userAddCompleteGet() {
		return "redirect:/";
	}

	// ユーザー登録完了 (Post)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.POST)
	public String userAddCompletePost(@Valid @ModelAttribute("UM") RegistForm userModel, BindingResult result,
			Model model, HttpSession session) {

		return "/common/regist/regist_complete";

	}

	// 仮登録
	@RequestMapping(value = "/regist/temp", method = { GET, POST })
	public String registTemp(@Valid @ModelAttribute("UM") RegistForm userModel, Model model, BindingResult result,
			HttpServletRequest request) throws MessagingException {

		// 情報のチェック
		if (result.hasErrors()) {

			model.addAttribute("msg", "登録に失敗しました");

			return "/common/regist/regist";

		} else {

			// 初期値設定
			long initialAsset = 0;
			initialAsset = Long.parseLong(initialDataSettingsService.getInitialAsset());
			userModel.setAsset(initialAsset);

			Users users = new Users();

			// パスワードを暗号化する
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordHash = encoder.encode(userModel.getPassword());

			users.setPassword(passwordHash);

			users.setAsset(userModel.getAsset());
			users.setBirthday(userModel.getBirthday());
			users.setMailAddress(userModel.getMailAddress());
			users.setNickName(userModel.getNickName());
			users.setStatus("0");
			users.setUserId(userModel.getUserId());

			// URL生成
			CryptionPageParametersEncoder enc = new CryptionPageParametersEncoder();

			PageParameters parameters = new PageParameters();

			double num = Math.random();

			parameters.add("parameter", num);
			Url param = enc.encodePageParameters(parameters);

			// URL生成
			String requestUrl = request.getRequestURL().toString();
			String requestServletPath = request.getServletPath();

			String url = requestUrl.replace(requestServletPath, "/regist/authentication");
			url += param.toString();

			// 仮登録実行
			usersService.insertTemporaryUser(users);

			// RegisterPoolに登録
			RegisterPool registerPool = new RegisterPool();
			Timestamp insDate = new Timestamp(System.currentTimeMillis());
			registerPool.setParameter(param.toString());
			registerPool.setUserId(users.getUserId());
			registerPool.setInsDate(insDate);

			registerPoolService.insertRegisterPool(registerPool);

			// メール送信
			mailService.doSendUserAuthenticationEmail(userModel.getNickName(), userModel.getMailAddress(), url);

			return "/common/regist/regist_temporary";
		}
	}

	// 登録完了
	@RequestMapping(value = "/regist/authentication", method = { GET })
	public String registAuth(@ModelAttribute("UM") RegistForm userModel, Model model, HttpServletRequest req) {

		Url url = requestUrl(req.getParameterMap());
		RegisterPool registerPool = registerPoolService.getRegisterPool(url.toString());

		// パラメータが登録されてなければ処理しない
		if (registerPool == null) {

			model.addAttribute("meg" , "登録が完了しているかパラメータが不正です");
			return "/common/regist/regist_complete";
		}

		// ユーザーのステータスをアクティブに更新
		usersService.updateStatusActive(registerPool.getUserId());

		// RegisterPoolのデータを削除
		registerPoolService.deleteRegisterPool(url.toString());

		return "/common/regist/regist_complete";
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

	private Url requestUrl(Map<String, String[]> map) {
		Url url = new Url();
		CryptionPageParametersEncoder enc = new CryptionPageParametersEncoder();
		for (String key : map.keySet()) {

			QueryParameter param = new QueryParameter(key, map.get(key)[0]);
			url.getQueryParameters().add(param);
		}
		return url;
	}

	private PageParameters decodeUrl(Url url) {

		CryptionPageParametersEncoder enc = new CryptionPageParametersEncoder();
		PageParameters parameters = enc.decodePageParameters(url);
		return parameters;
	}

}
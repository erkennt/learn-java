package controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.InitialDataSettingsDAO;
import dao.UserDAO;
import model.InitialDataSettingsModel;
import model.UserModel;

@Controller
@SessionAttributes("UM")
public class RegistController {
	@Autowired
	private UserDAO userDao;

	@Autowired
	private InitialDataSettingsDAO initialDataSettingsDAO;

	@ModelAttribute("UM")
	public UserModel init() {
		return new UserModel();
	}

	@InitBinder("UM")
	public void bindUser(WebDataBinder binder, HttpServletRequest request) {
		// SelectBoxの選択値をBirthdayに代入
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

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			userModel.setAsset(initialAsset);

			// 登録実行
			userDao.userInsert(userModel);

			// ステータス更新
			userDao.userStatusUpdate(1, userModel.getUserId()); // 後にメール認証後に実行・・・マジックナンバー要対応

			return "regist_complete";
		}
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
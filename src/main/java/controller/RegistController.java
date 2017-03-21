package controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserDAO;
import model.UserModel;

@Controller
public class RegistController {
	@Autowired
	private UserDAO userDao;

	@ModelAttribute("UM")
	public UserModel init() {
		return new UserModel();
	}

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String loginGet() {
		return "regist";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute("UM") UserModel userModel, Model model) {
		return "regist";
	}

	// ユーザー登録確認 (Get)
	@RequestMapping(value = "/regist/add", method = RequestMethod.GET)
	public String userAddGet() {
		return "redirect:/";

	}
	// ユーザー登録確認 (Post)

	@RequestMapping(value = "/regist/add", method = RequestMethod.POST)
	public String userAddPost(@ModelAttribute("UM") UserModel userModel, Model model, HttpSession session) {

		// 入力バリデート

		return "regist_confirm";

	}

	// ユーザー登録完了 (Get)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.GET)
	public String userAddCompleteGet() {
		return "redirect:/";
	}

	// ユーザー登録完了 (Post)
	@RequestMapping(value = "/regist/complete", method = RequestMethod.POST)
	public String userAddCompletePost(@ModelAttribute("UM") UserModel userModel, Model model, HttpSession session) {

		// 情報のチェック
		if (!StringUtils.isEmpty(userModel.getUserId())) {

			// 初期値設定
			Date date = new Date();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

			userModel.setAsset(50000000); // 後にDBから取得
			userModel.setBirthday(date); // 後に入力値から変換
			userModel.setInsDate(timestamp);
			userModel.setUpdDate(timestamp);

			// 登録実行
			// userDao.userInsert(userModel);

			// ステータス更新
			userDao.userStatusUpdate(1, userModel.getUserId()); // 後にメール認証後に実行・・・マジックナンバー要対応

			return "regist_complete";

		} else {
			model.addAttribute("msg", "入力に誤りがあります");

			return "regist_confirm";
		}
	}

}
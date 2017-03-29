package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.UserDAO;
import model.UserModel;

@Controller
@SessionAttributes("UM")
public class LoginController {
	@Autowired
	private UserDAO userDao;

	@ModelAttribute("UM")
	public UserModel init() {
		return new UserModel();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		if (session.getAttribute("session") == null) {
			return "redirect:/";
		}
		return "menu";
	}

	// ユーザーログイン
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute("UM") UserModel userModel,Model model, RedirectAttributes redirectAttributes,
			HttpSession session) {

		// 入力バリデート

		// ユーザーデータ取得
		List<UserModel> userItem = userDao.userLogin(userModel);

		// 認証
		if (userItem.size() <= 0) {
			// 認証失敗時のメッセージ
			redirectAttributes.addFlashAttribute("msgR", "ログインに失敗しました");
			redirectAttributes.addFlashAttribute("support", "<SMALL><a href=\"support" + "\">ログインできませんか？</a></SMALL>");

			return "redirect:/";
		}
		session.setAttribute("session", userItem.get(0));
		return "redirect:../CardGame/menu";

	}

	// テストユーザーログイン (Get)
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String loginTest(HttpSession session) {
		if (session.getAttribute("session") == null) {
			return "redirect:/";
		}
		return "menu";
	}

	// テストユーザーログイン (Post)
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String loginTest(Model model, RedirectAttributes redirectAttributes, HttpSession session) {

		// テストユーザーデータ取得
		List<UserModel> userItem = userDao.userTestLogin();

		// 認証
		if (userItem.size() > 0) {
			session.setAttribute("session", userItem.get(0));

			return "redirect:../CardGame/menu";
		} else {
			// 認証失敗時のメッセージ
			redirectAttributes.addFlashAttribute("msgR", "ログインに失敗しました");

			return "redirect:/";
		}
	}
}
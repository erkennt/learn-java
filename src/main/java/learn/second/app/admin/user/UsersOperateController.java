package learn.second.app.admin.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import learn.second.domain.model.Admin;
import learn.second.domain.model.Users;
import learn.second.domain.service.security.AdminUsers;
import learn.second.domain.service.users.UsersService;

@Controller
public class UsersOperateController {

	@Autowired
	private UsersService usersService;

	@ModelAttribute("Search")
	UserSearchForm init() {
		return new UserSearchForm();
	}

	@RequestMapping(value = "/admin/user", method = { RequestMethod.GET, RequestMethod.POST })
	public String user(@AuthenticationPrincipal AdminUsers adminUsers, Locale locale, Model model,
			HttpSession session) {
		Admin users = adminUsers.getUser();
		model.addAttribute(users);

		return "/admin/menu";
	}

	@RequestMapping(value = "/admin/user/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String top(@ModelAttribute("Search") UserSearchForm userSearchForm,
			@AuthenticationPrincipal AdminUsers adminUsers, Locale locale, Model model, HttpSession session) {

		return "/admin/user/search";
	}

	@RequestMapping(value = "/admin/user/search/{page}", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@ModelAttribute("Search") UserSearchForm userSearchForm,
			@AuthenticationPrincipal AdminUsers adminUsers, @PathVariable("page") String page, Locale locale,
			Model model, HttpSession session) {

		// サーチ画面に戻す（pageが数字以外または0より小さい場合、フォームがNULLの場合）
		if (!page.matches("\\d") || Integer.parseInt(page) < 0 || userSearchForm == null) {
			return "/admin/user/search";
		}

		String userId = userSearchForm.getUserId();
		String nickName = userSearchForm.getNickName();
		String mailAddress = userSearchForm.getMailAddress();
		String pageSize = userSearchForm.getPageSize();

		List<Users> list = usersService.getSearchResult(userId, nickName, mailAddress);

		PagedListHolder<Users> pagenation = new PagedListHolder<>(list);
		pagenation.setPage(Integer.parseInt(page)); // 現在ページ（0から開始）
		pagenation.setPageSize(Integer.parseInt(pageSize)); // １ページの表示数

		model.addAttribute("pagedListHolder", pagenation);

		Admin users = adminUsers.getUser();
		model.addAttribute(users);

		return "/admin/user/search";
	}

	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
	public String edit(Locale locale, Model model) {
		return "/admin/user/edit";
	}
}
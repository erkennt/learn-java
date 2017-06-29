package learn.second.app.admin.bulk;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameBulk {
	@RequestMapping(value = "/admin/bulk", method = { RequestMethod.GET, RequestMethod.POST })
	public String game(Locale locale, Model model, HttpSession session) {

		return "/admin/bulk/menu";
	}
}

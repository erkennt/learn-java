package learn.second.app.admin.bulk.add;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import learn.second.domain.service.admin.AdminService;
import learn.second.domain.service.cards.CardsService;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.gamelogs.GameLogsService;
import learn.second.domain.service.gamesettings.GameSettingsService;
import learn.second.domain.service.userdraw.UserDrawWaitService;
import learn.second.domain.service.users.UsersService;

@Controller
public class BulkUsersController {

	@Autowired
	AdminService adminService;
	@Autowired
	CardsService cardsService;
	@Autowired
	GameLogsService gamelogsService;
	@Autowired
	GameSettingsService gameSettingsService;
	@Autowired
	InitialDataSettingsService initialDataSettingsService;
	@Autowired
	UserDrawWaitService userDrawWaitService;
	@Autowired
	UsersService usersService;
	@Autowired
	BulkInsert bulkInsert;

	@RequestMapping(value = "/admin/bulk/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String menu(Locale locale, Model model, HttpSession session) {

		return "/admin/bulk/add";
	}

	@RequestMapping(value = "/admin/bulk/add/upload", method = RequestMethod.POST)
	public String uploadRecv(@RequestParam(required = false) MultipartFile file, Model model)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		// Insertに失敗した行を格納する
		Map<String, List<Integer>> errorsMap = bulkInsert.getErrors();
		if(file == null || file.getSize() == 0){


			//model.addAttribute("errorsMap", errorsMap);
			return "/admin/bulk/add_complete";
		}

		// データ登録
		bulkInsert.insertBulk(file);


		for (String key : errorsMap.keySet()) {

			System.out.println(key + "テーブルは " + errorsMap.get(key).size() + " 件の登録に失敗しました");
			System.out.println("エラー行 : " + errorsMap.get(key));
		}

		model.addAttribute("errorsMap", errorsMap);
		return "/admin/bulk/add_complete";
	}
}
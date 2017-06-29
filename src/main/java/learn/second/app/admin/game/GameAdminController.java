package learn.second.app.admin.game;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import learn.second.domain.service.cards.CardsService;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.gamelogs.CardGameLogic;
import learn.second.domain.service.gamelogs.GameLogsService;
import learn.second.domain.service.gamesettings.GameSettingsService;
import learn.second.domain.service.userdraw.UserDrawWaitService;
import learn.second.domain.service.users.UsersService;

@Controller
public class GameAdminController {

	@Autowired
	InitialDataSettingsService initialDataSettingsService;
	@Autowired
	GameSettingsService gameSettingsService;
	@Autowired
	CardsService cardsService;
	@Autowired
	GameLogsService gameLogsService;
	@Autowired
	private CardGameLogic cardGameLogic;
	@Autowired
	private UserDrawWaitService userDrawWaitService;
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/admin/game", method = { RequestMethod.GET, RequestMethod.POST })
	public String menu(Locale locale, Model model, HttpSession session) {

		return "/admin/game/menu";
	}

	// ゲームの管理
	@RequestMapping(value = "/admin/game/setting", method = { RequestMethod.GET, RequestMethod.POST })
	public String setting(Locale locale, Model model, HttpSession session) {

		String asset = initialDataSettingsService.getInitialAsset();
		int wait_d = gameSettingsService.getWaitTime("Donuts");
		int wait_h = gameSettingsService.getWaitTime("HighLow");

		model.addAttribute("asset", asset);
		model.addAttribute("wait_d", wait_d);
		model.addAttribute("wait_h", wait_h);

		return "/admin/game/setting";
	}

	// 待機時間変更（ドーナツ）
	@RequestMapping(value = "/admin/game/setting/wait/donuts", method = { RequestMethod.GET, RequestMethod.POST })
	public String settingWaitDonuts(@RequestParam("wait_d") String wait, Locale locale, Model model,
			HttpSession session) {

		String gameType = "Donuts";
		model.addAttribute("up_msg", "待機時間の更新に失敗しました");
		if (StringUtils.isNotEmpty(wait)) {

			int castWait = Integer.valueOf(wait);

			gameSettingsService.updateWaitTime(castWait, gameType);
			model.addAttribute("up_msg", "ドーナツゲーム" + "の待機時間を更新しました");
		}
		String asset = initialDataSettingsService.getInitialAsset();
		int wait_d = gameSettingsService.getWaitTime("Donuts");
		int wait_h = gameSettingsService.getWaitTime("HighLow");

		model.addAttribute("asset", asset);
		model.addAttribute("wait_d", wait_d);
		model.addAttribute("wait_h", wait_h);

		return "/admin/game/setting";
	}

	// 待機時間変更（HighLow）
	@RequestMapping(value = "/admin/game/setting/wait/high", method = { RequestMethod.GET, RequestMethod.POST })
	public String settingWaitHigh(@RequestParam("wait_h") String wait, Locale locale, Model model,
			HttpSession session) {

		String gameType = "HighLow";
		model.addAttribute("up_msg", "待機時間の更新に失敗しました");
		if (StringUtils.isNotEmpty(wait)) {

			int castWait = Integer.valueOf(wait);
			gameSettingsService.updateWaitTime(castWait, gameType);
			model.addAttribute("up_msg", "High & Low" + "の待機時間を更新しました");
		}
		String asset = initialDataSettingsService.getInitialAsset();
		int wait_d = gameSettingsService.getWaitTime("Donuts");
		int wait_h = gameSettingsService.getWaitTime("HighLow");

		model.addAttribute("asset", asset);
		model.addAttribute("wait_d", wait_d);
		model.addAttribute("wait_h", wait_h);

		return "/admin/game/setting";
	}

	// 初期資産
	@RequestMapping(value = "/admin/game/setting/asset", method = { RequestMethod.GET, RequestMethod.POST })
	public String settingAsset(@RequestParam("asset") String asset, Locale locale, Model model, HttpSession session) {

		model.addAttribute("up_msg", "更新に失敗しました");

		if (StringUtils.isNotEmpty(asset)) {
			initialDataSettingsService.updateInitialAsset(asset);
			model.addAttribute("up_msg", "初期の所持金を更新しました");
		}
		int wait_d = gameSettingsService.getWaitTime("Donuts");
		int wait_h = gameSettingsService.getWaitTime("HighLow");

		model.addAttribute("asset", asset);
		model.addAttribute("wait_d", wait_d);
		model.addAttribute("wait_h", wait_h);
		return "/admin/game/setting";
	}

	// ゲーム情報の操作
	@RequestMapping(value = "/admin/game/data", method = { RequestMethod.GET, RequestMethod.POST })
	public String game(Locale locale, Model model, HttpSession session) {

		return "/admin/game/data";
	}

	// 成功数リセット（ドーナツ）
	@RequestMapping(value = "/admin/game/data/reset/donuts", method = { RequestMethod.GET, RequestMethod.POST })
	public String resetDonuts(Locale locale, Model model, HttpSession session) {

		String gameType = "Donuts";
		// typeを判定
		// アウトになるカードを生成する
		cardsService.createFalseCardData(gameType);
		return "/admin/game/data";
	}

	// 成功数リセット（HighLow）
	@RequestMapping(value = "/admin/game/data/reset/high", method = { RequestMethod.GET, RequestMethod.POST })
	public String resetHigh(Locale locale, Model model, HttpSession session) {

		String gameType = "HighLow";
		// typeを判定
		// アウトになるカードを生成する
		cardsService.createFalseCardData(gameType);

		return "/admin/game/data";
	}

	// カードのログ削除
	@RequestMapping(value = "/admin/game/data/initialize/log", method = { RequestMethod.GET, RequestMethod.POST })
	public String initializeLog(Locale locale, Model model, HttpSession session) {

		// 全てのログを削除する
		gameLogsService.deleteAll();
		return "/admin/game/data";
	}

	// 待機時間リセット
	@RequestMapping(value = "/admin/game/data/initialize/wait", method = { RequestMethod.GET, RequestMethod.POST })
	public String initializeLogWaitTmeAll(Locale locale, Model model, HttpSession session) {

		// 全ての待機レコードを削除する or 更新する
		userDrawWaitService.deleteAll();
		return "/admin/game/data";
	}

	// 全ユーザーの所持資産を初期値に変更
	@RequestMapping(value = "/admin/game/data/initialize/asset", method = { RequestMethod.GET, RequestMethod.POST })
	public String initializeAssetWaitTmeAll(Locale locale, Model model, HttpSession session) {
		String asset = initialDataSettingsService.getInitialAsset();
		usersService.updateAllUserAsset(asset);
		// 全てのユーザーの資産を上書きする
		return "/admin/game/data";
	}

}
package learn.second.app.common.game;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import learn.second.domain.model.Cards;
import learn.second.domain.model.GameLogs;
import learn.second.domain.model.GameSettings;
import learn.second.domain.model.UserDrawWait;
import learn.second.domain.model.Users;
import learn.second.domain.reposiory.cards.CardsRepository;
import learn.second.domain.reposiory.gamelogs.GameLogsRepositoryImpl;
import learn.second.domain.reposiory.gamesetting.GameSettingsRepository;
import learn.second.domain.reposiory.users.UsersRepository;
import learn.second.domain.service.gamelogs.CardGameLogic;
import learn.second.domain.service.gamesettings.GameSettingsService;
import learn.second.domain.service.security.CommonUsers;
import learn.second.domain.service.userdraw.UserDrawWaitService;

@Controller
public class CardGameController {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private GameSettingsRepository gameSettingsRepository;

	@Autowired
	private CardsRepository cardsDAO;

	@Autowired
	private GameLogsRepositoryImpl gameLogAccessDAO;

	@Autowired
	private CardGameLogic cardGameLogic;

	@Autowired
	private UserDrawWaitService userDrawWaitService;

	@Autowired
	private GameSettingsService gameSettingsService;

	@ModelAttribute("Game")
	private CardGameForm init() {
		return new CardGameForm();
	}

	@RequestMapping(value = "/common/game/{gametype}/main", method = { GET, POST })
	public String main(@AuthenticationPrincipal CommonUsers commonUsers, @PathVariable("gametype") String gameType,
			GameSettings gameSettingsModel, Model model, HttpSession session) {

		// ユーザー情報の取得
		Users user = usersRepository.getUser(commonUsers.getUsername());

		// ゲーム設定の読み込み
		GameSettings gameSettings = gameSettingsRepository.getGameSettings(gameType);

		if (gameSettings == null) {
			return "redirect:/"; // 存在しないGameTypeの場合はTOPへリダイレクト
		}

		// カードリストの生成
		List<Cards> cardList = cardsDAO.getGameCardList(gameSettings.getGameType());

		// ゲーム初アクセス時の処理
		if (cardList.isEmpty()) {
			cardsDAO.createInitialCardData(gameSettings.getGameType()); // 初期データの生成
			cardList = cardsDAO.getGameCardList(gameSettings.getGameType());// 生成したカードを取得
		}
		// 現在のカード取得
		Cards currentCard = cardList.get(cardList.size() - 1);

		model.addAttribute("CardList", cardList);
		model.addAttribute("CurrentCard", currentCard);

		// ゲームログの取得
		List<GameLogs> logList = gameLogAccessDAO.getGameLogs(gameSettings.getGameType(), gameSettings.getLogCounts());
		model.addAttribute("GameLogsList", logList);

		// ユーザーがカードを引けるか判定する
		int drawState = 0;

		UserDrawWait userDrawWait = userDrawWaitService.getUserDraw(user.getUserId(), gameType);
		if (currentCard.getUserId() != null) {
			if (currentCard.getUserId().equals(user.getUserId())) {
				drawState = 1;
			} else if (userDrawWait != null) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				int diff = timestamp.compareTo(userDrawWait.getNextDraw());
				if (diff < 0) {
					drawState = 2;
				}
			}
		}

		// ユーザーの待機時間演算
		String nextDraw = null;
		if (userDrawWait != null && userDrawWait.getNextDraw() != null) {
			nextDraw = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(userDrawWait.getNextDraw());
		} else {
			Date date = new Date();
			nextDraw = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
		}

		List<String> betType = new ArrayList<String>();
		betType.add("1000");
		betType.add("10000");
		betType.add("100000");

		model.addAttribute("BetTypeList", betType);
		model.addAttribute("NextDraw", nextDraw);
		model.addAttribute("DrawState", drawState);
		return "/common/game/" + gameType;

	}

	@RequestMapping(value = "/common/game/{gametype}/draw", method = { GET })
	public String drawCard(@AuthenticationPrincipal CommonUsers commonUsers, @PathVariable("gametype") String gameType,
			GameSettings gameSettingsModel, Model model, HttpSession session) {

		return this.main(commonUsers, gameType, gameSettingsModel, model, session);
	}

	@RequestMapping(value = "/common/game/{gametype}/draw", method = { POST })
	public String drawCard(@ModelAttribute("Game") CardGameForm cardGameForm,
			@AuthenticationPrincipal CommonUsers commonUsers, 
			@PathVariable("gametype") String gameType,
			@RequestParam(value = "select", required = false) String select, GameSettings gameSettingsModel,
			Model model, HttpSession session) throws NoSuchMethodException, SecurityException {

		// ゲーム設定の取得
		GameSettings gameSettings = gameSettingsService.getWaitTime2(gameType);

		if (gameSettings == null) {
			return "redirect:/"; // 存在しないGameTypeの場合はTOPへリダイレクト
		}

		// ユーザー情報の取得
		Users user = usersRepository.getUser(commonUsers.getUsername());

		// カード値の生成
		Random rand = new Random(System.currentTimeMillis());
		int newValue = rand.nextInt(5) + 1; // 1～5の数値を算出

		// 現在のカード取得
		List<Cards> cardList = cardsDAO.getGameCardList(gameSettings.getGameType());
		Cards currentCard = cardList.get(cardList.size() - 1);

		model.addAttribute("CardList", cardList);
		model.addAttribute("CurrentCard", currentCard);

		// カードを引けるか判定（リロード対策）

		if (currentCard.getUserId() != null) {
			if (currentCard.getUserId().equals(user.getUserId())) {

				// Logの再取得
				List<GameLogs> logList = gameLogAccessDAO.getGameLogs(gameSettings.getGameType(),
						gameSettings.getLogCounts());
				model.addAttribute("GameLogsList", logList);

				List<String> betType = new ArrayList<String>();
				betType.add("1000");
				betType.add("10000");
				betType.add("100000");

				model.addAttribute("BetTypeList", betType);
				model.addAttribute("DrawState", 0);
				return "/common/game/" + gameType;
			}
		}
		/*
		 * ゲーム別 成否判定 Donuts・・・同じかどうか High&Low・・・大きいか小さいか
		 */
		boolean result = Boolean.FALSE;
		if (gameType.equals("donuts")) {
			result = cardGameLogic.donuts(newValue, currentCard.getValue());
		} else if (gameType.equals("highlow")) {
			result = cardGameLogic.highLow(newValue, currentCard.getValue(), select);
		}

		// 掛け金
		long betType = Long.valueOf(cardGameForm.getBetType()); // 画面から選択させる

		// 倍率取得
		int magnification = cardList.size();

		// 金額演算
		long prize = betType * magnification;

		// メッセージ作成
		String logMessage;
		if (result) {
			logMessage = user.getNickName() + "さんが" + prize + "円獲得しました";
			model.addAttribute("resultMessage", "セーフ！ " + prize + "円獲得しました");

		} else {
			logMessage = user.getNickName() + "さんが" + prize + "円失いました";
			model.addAttribute("resultMessage", "アウトー！ " + prize + "円失いました");
		}

		// 金額の符号判定
		if (!result) {
			prize = prize * (-1);
		}

		// カードデータの生成
		cardsDAO.createCardData(gameSettings.getGameType(), user.getUserId(), newValue, result);

		// UserLastDraws更新or新規作成

		// 資産の増減
		usersRepository.userAssetUpdate(user.getUserId(), prize);

		// 最新のカードデータを取得
		cardList = cardsDAO.getGameCardList(gameSettings.getGameType());
		currentCard = cardList.get(cardList.size() - 1);
		model.addAttribute("NewCard", currentCard);

		// ログ作成
		gameLogAccessDAO.createGameLog(currentCard.getCardId(), user.getUserId(), magnification, betType, logMessage);

		// 最新のログ情報を取得
		List<GameLogs> logList = gameLogAccessDAO.getGameLogs(gameSettings.getGameType(), gameSettings.getLogCounts());
		model.addAttribute("GameLogsList", logList);

		Timestamp nextDrawTime = new Timestamp(System.currentTimeMillis() + 1000 * gameSettings.getWaitMinutes());

		// 待機時間の更新
		userDrawWaitService.updateUserDrawWait(user.getUserId(), gameType, nextDrawTime);

		// ユーザーの待機時間演算
		UserDrawWait userDrawWait = userDrawWaitService.getUserDraw(user.getUserId(), gameType);
		String nextDraw = null;
		if (userDrawWait != null && userDrawWait.getNextDraw() != null) {
			nextDraw = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(userDrawWait.getNextDraw());
		} else {
			Date date = new Date();
			nextDraw = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(nextDrawTime);
		}

		model.addAttribute("NextDraw", nextDraw);

		// 認証ユーザー情報の更新
		user = usersRepository.getUser(commonUsers.getUsername());
		commonUsers.reloadUser(user);
		return "/common/game/" + gameType;
	}

}
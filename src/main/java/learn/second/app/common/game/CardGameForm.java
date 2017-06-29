package learn.second.app.common.game;

public class CardGameForm {

	public String GameType;
	public int WaitMinutes;
	public int LogCounts;
	public String BetType;
	public String getGameType() {
		return GameType;
	}
	public void setGameType(String gameType) {
		GameType = gameType;
	}
	public int getWaitMinutes() {
		return WaitMinutes;
	}
	public void setWaitMinutes(int waitMinutes) {
		WaitMinutes = waitMinutes;
	}
	public int getLogCounts() {
		return LogCounts;
	}
	public void setLogCounts(int logCounts) {
		LogCounts = logCounts;
	}
	public String getBetType() {
		return BetType;
	}
	public void setBetType(String betType) {
		BetType = betType;
	}
}

package learn.second.domain.model;

public class GameSettings {

	public String GameType;
	public int WaitMinutes;
	public int LogCounts;
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


}
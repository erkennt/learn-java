package model;

import java.util.List;

public class GameSettingsModel {

	private String GameType;
	private float WaitMinutes;
	private Long CurrentCardId;
	private int LogCounts;

	public String getGameType() {
		return GameType;
	}

	public void setGameType(String gameType) {
		GameType = gameType;
	}

	public float getWaitMinutes() {
		return WaitMinutes;
	}

	public void setWaitMinutes(float waitMinutes) {
		WaitMinutes = waitMinutes;
	}

	public long getCurrentCardId() {
		return CurrentCardId;
	}

	public void setCurrentCardId(long currentCardId) {
		CurrentCardId = currentCardId;
	}

	public int getLogCounts() {
		return LogCounts;
	}

	public void setLogCounts(int logCounts) {
		LogCounts = logCounts;
	}
	public void setGameSettingsModel (List<GameSettingsModel> gameSettingsItem)
	{
		for (GameSettingsModel list : gameSettingsItem ){
			this.setGameType(list.getGameType());
			this.setWaitMinutes(list.getWaitMinutes());
			this.setCurrentCardId(list.getCurrentCardId());
			this.setLogCounts(list.getLogCounts());
		}
	}
}
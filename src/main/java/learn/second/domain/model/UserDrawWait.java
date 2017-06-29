package learn.second.domain.model;

import java.sql.Timestamp;

public class UserDrawWait {

	public String  UserId;
	public String GameType;
	public Timestamp NextDraw;

	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getGameType() {
		return GameType;
	}
	public void setGameType(String gameType) {
		GameType = gameType;
	}
	public Timestamp getNextDraw() {
		return NextDraw;
	}
	public void setNextDraw(Timestamp nextDraw) {
		NextDraw = nextDraw;
	}
}

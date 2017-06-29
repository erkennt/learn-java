package learn.second.domain.model;

import java.sql.Timestamp;
import java.util.List;

public class Cards {

	public long CardId;
	public String GameType;
	public String UserId;
	public int Value;
	public Boolean Result;
	public Timestamp InsDate;

	public long getCardId() {
		return CardId;
	}

	public void setCardId(long cardId) {
		CardId = cardId;
	}

	public String getGameType() {
		return GameType;
	}

	public void setGameType(String gameType) {
		GameType = gameType;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public int getValue() {
		return Value;
	}

	public void setValue(int value) {
		Value = value;
	}

	public Boolean getResult() {
		return Result;
	}

	public void setResult(Boolean result) {
		Result = result;
	}

	public Timestamp getInsDate() {
		return InsDate;
	}

	public void setInsDate(Timestamp insDate) {
		InsDate = insDate;
	}

	public void setGameSettingsModel(List<Cards> cardsItem) {
		for (Cards list : cardsItem) {
			this.setCardId(list.getCardId());
			this.setGameType(list.getGameType());
			this.setUserId(list.getUserId());
			this.setValue(list.getValue());
			this.setResult(list.getResult());
			this.setInsDate(list.getInsDate());
		}

	}

}
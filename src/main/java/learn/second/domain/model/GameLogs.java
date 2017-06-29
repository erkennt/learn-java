package learn.second.domain.model;

import java.sql.Timestamp;

public class GameLogs {

	public Long LogId;
	public Long CardId;
	public String UserId;
	public int Magnification;
	public Long BetType;
	public String Message;
	public Timestamp InsDate;

	public Long getCardId() {
		return CardId;
	}
	public void setCardId(Long cardId) {
		CardId = cardId;
	}
	public Long getLogId() {
		return LogId;
	}
	public void setLogId(Long logId) {
		LogId = logId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public int getMagnification() {
		return Magnification;
	}
	public void setMagnification(int magnification) {
		Magnification = magnification;
	}
	public Long getBetType() {
		return BetType;
	}
	public void setBetType(Long betType) {
		BetType = betType;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Timestamp getInsDate() {
		return InsDate;
	}
	public void setInsDate(Timestamp insDate) {
		InsDate = insDate;
	}

}
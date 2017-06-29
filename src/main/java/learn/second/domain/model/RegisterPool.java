package learn.second.domain.model;

import java.sql.Timestamp;

public class RegisterPool {
	public String Parameter;
	public String UserId;
	public Timestamp InsDate;

	public String getParameter() {
		return Parameter;
	}
	public void setParameter(String parameter) {
		Parameter = parameter;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Timestamp getInsDate() {
		return InsDate;
	}
	public void setInsDate(Timestamp insDate) {
		InsDate = insDate;
	}

}

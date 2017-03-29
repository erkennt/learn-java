package model;

import java.sql.Timestamp;

public class MailAuthenticationModel {

	private String MailAddress;
	private String UserId;
	private boolean Status;
	private Timestamp InsDate;
	private Timestamp UpdDate;

	public String getMailAddress() {
		return MailAddress;
	}
	public void setMailAddress(String mailAddress) {
		MailAddress = mailAddress;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public Timestamp getInsDate() {
		return InsDate;
	}
	public void setInsDate(Timestamp insDate) {
		InsDate = insDate;
	}
	public Timestamp getUpdDate() {
		return UpdDate;
	}
	public void setUpdDate(Timestamp updDate) {
		UpdDate = updDate;
	}


}
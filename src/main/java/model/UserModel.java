package model;


import java.sql.Timestamp;
import java.util.Date;

public class UserModel {

	private String UserId;
	private String Password;
	private String NickName;
	private String MailAddress;
	private Date Birthday;
	private long Asset;
	private int Status;
	private Timestamp InsDate;
	private Timestamp UpdDate;
	private String UpdUser;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getMailAddress() {
		return MailAddress;
	}

	public void setMailAddress(String mailAddress) {
		MailAddress = mailAddress;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public long getAsset() {
		return Asset;
	}

	public void setAsset(long asset) {
		Asset = asset;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
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

	public String getUpdUser() {
		return UpdUser;
	}

	public void setUpdUser(String updUser) {
		UpdUser = updUser;
	}

}
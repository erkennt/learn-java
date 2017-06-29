package learn.second.domain.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;

@Entity
public class Users {

	public String UserId;

	public String Password;

	public String NickName;

	public String MailAddress;

	public Date Birthday;

	public long Asset;

	public String Status;

	public Timestamp InsDate;

	public Timestamp UpdDate;

	public String UpdUser;

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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
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

	public Map map() {

		return null;
	}
}
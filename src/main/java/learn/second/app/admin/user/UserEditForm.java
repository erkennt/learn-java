package learn.second.app.admin.user;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserEditForm {

	private String UserId;

	private String Password;
	@NotEmpty
	@Length(min = 2, max = 20)
	private String NickName;
	@NotEmpty
	@Length(max = 254)
	@Email
	private String MailAddress;

	private Date Birthday;

	@NotNull
	@Min(0)
	private long Asset;

	private String Status;

	private Timestamp InsDate;

	private Timestamp UpdDate;

	private String UpdUser;

	// 新規登録用
	@NotEmpty
	private String Years;
	@NotEmpty
	private String Month;
	@NotEmpty
	private String Days;

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

	public String getYears() {
		return Years;
	}

	public void setYears(String years) {
		Years = years;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getDays() {
		return Days;
	}

	public void setDays(String days) {
		Days = days;
	}

}

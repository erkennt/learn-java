package learn.second.app.common.info;


import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import learn.second.domain.service.validate.NotDuplicationForMailAddress;
import learn.second.domain.service.validate.NotDuplicationForNickName;
import  learn.second.domain.service.validate.NotDuplicationForUserId;

public class RegistForm {

	@NotEmpty
	@NotDuplicationForUserId
	@Length(min = 2, max = 10)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String UserId;

	@NotEmpty
	@NotDuplicationForNickName
	@Length(min = 2, max = 20)
	private String NickName;

	@NotEmpty
	@NotDuplicationForMailAddress
	@Length(max = 254)
	@Email
	private String MailAddress;

	private Date Birthday;

	private long Asset;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
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


}
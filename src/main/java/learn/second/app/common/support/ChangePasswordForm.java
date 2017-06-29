package learn.second.app.common.support;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {

	private String UserId;
	@NotEmpty
	@Length(min = 6, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String Password1;
	@NotEmpty
	@Length(min = 6, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String Password2;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword1() {
		return Password1;
	}

	public void setPassword1(String password1) {
		Password1 = password1;
	}

	public String getPassword2() {
		return Password2;
	}

	public void setPassword2(String password2) {
		Password2 = password2;
	}

}

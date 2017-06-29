package learn.second.app.common.info;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class NewPasswordForm {

	@NotEmpty
	@Length(min = 6, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String NewPassword;

	@NotEmpty
	@Length(min = 6, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String ConfNewPassword;



	public String getNewPassword() {
		return NewPassword;
	}

	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}

	public String getConfNewPassword() {
		return ConfNewPassword;
	}

	public void setConfNewPassword(String confNewPassword) {
		ConfNewPassword = confNewPassword;
	}


}

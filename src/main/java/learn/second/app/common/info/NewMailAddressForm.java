package learn.second.app.common.info;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import learn.second.domain.service.validate.NotDuplicationForMailAddress;

public class NewMailAddressForm {
	@NotEmpty
	@NotDuplicationForMailAddress
	@Length(max = 254)
	@Email
	private String MailAddress;

	public String getMailAddress() {
		return MailAddress;
	}

	public void setMailAddress(String mailAddress) {
		MailAddress = mailAddress;
	}



}

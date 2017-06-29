package learn.second.app.common.info;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import learn.second.domain.service.validate.NotDuplicationForNickName;

public class NewNickNameForm {
	@NotEmpty
	@NotDuplicationForNickName
	@Length(min = 2, max = 20)
	private String NickName;

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}


}

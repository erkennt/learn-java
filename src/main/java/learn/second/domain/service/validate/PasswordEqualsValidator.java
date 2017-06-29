package learn.second.domain.service.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import learn.second.app.common.regist.RegistForm;

@Component
public class PasswordEqualsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistForm form = (RegistForm) target;
		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();

		if (password == null || confirmPassword == null) {
			// must be checked by @NotNull
			return;
		}
		if (!password.equals(confirmPassword)) {
			errors.rejectValue("Password", "PasswordEqualsValidator.registForm.Password",
					"パスワードが一致しません");
		}
	}
}
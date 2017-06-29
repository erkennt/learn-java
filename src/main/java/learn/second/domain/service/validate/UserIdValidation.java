package learn.second.domain.service.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import learn.second.domain.model.Users;
import learn.second.domain.service.users.UsersService;

public class UserIdValidation implements ConstraintValidator<NotDuplicationForUserId, String> {

	@Autowired
	UsersService usersService;

	@Override
	public void initialize(NotDuplicationForUserId constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String value, ConstraintValidatorContext context) {

		Users Users = usersService.getUsersByUserId(value);
		if (Users != null) {
			return false;
		}

		return true;
	}
}
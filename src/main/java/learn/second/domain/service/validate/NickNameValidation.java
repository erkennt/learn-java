package learn.second.domain.service.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import learn.second.domain.model.Users;
import learn.second.domain.service.users.UsersService;


public class NickNameValidation implements ConstraintValidator<NotDuplicationForNickName, String> {

	@Autowired
	UsersService usersService;

    @Override
    public void initialize(NotDuplicationForNickName constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

		Users Users = usersService.getUsersByNickName(value);
		if (Users != null) {
			return false;
		}

		return true;
	}
}
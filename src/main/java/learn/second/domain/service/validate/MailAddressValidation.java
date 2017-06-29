package learn.second.domain.service.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import learn.second.domain.model.Users;
import learn.second.domain.service.users.UsersService;


public class MailAddressValidation implements ConstraintValidator<NotDuplicationForMailAddress, String> {

	@Autowired
	UsersService usersService;

    @Override
    public void initialize(NotDuplicationForMailAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

		Users Users = usersService.getUsersByEmail(value);
		if (Users != null) {
			return false;
		}

		return true;
	}
}
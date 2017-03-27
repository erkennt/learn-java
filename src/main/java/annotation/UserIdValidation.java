package annotation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import model.UserModel;


public class UserIdValidation implements ConstraintValidator<NotDuplication, String> {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void initialize(NotDuplication constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

        Boolean duplicateCheck = searchUser(value);

        if (duplicateCheck == Boolean.FALSE){
        	return false;
        }

        return true;
    }

	private Boolean searchUser(String userId) {
		String sql = "Select * FROM User Where UserId = :UserId";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);

		List<UserModel> list = namedParameterJdbcTemplate.query(sql, params
				, new BeanPropertyRowMapper<UserModel>(UserModel.class));

		return list.isEmpty();

	}

}
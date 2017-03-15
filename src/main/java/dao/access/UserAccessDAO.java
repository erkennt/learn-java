package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.UserDAO;
import model.UserModel;

@Component
public class UserAccessDAO implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	public List<UserModel> userLogin(UserModel userModel) {
		String sql = "Select * FROM User Where UserId = :UserId And Password = :Password";

		return  namedParameterJdbcTemplate.query(sql
				, new BeanPropertySqlParameterSource(userModel)
				, new BeanPropertyRowMapper<UserModel>(UserModel.class));

	}

	public List<UserModel> userTestLogin() {

		String sql = "Select u.* from User as u"
		+" Inner JOIN TestUser as tu ON u.UserId = tu.UserId";

		return  jdbcTemplate.query(sql
				, new BeanPropertyRowMapper<UserModel>(UserModel.class));
	}
}
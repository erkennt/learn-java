package learn.second.domain.reposiory.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.Users;
@Component
public class CommonAuthRepositoryImpl implements CommonAuthRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Users findOne(String username) {


		String sql = "SELECT * From Users WHERE UserId = :UserId And Status = 1";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", username);

		List<Users> usersList = namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));

		Users user = usersList.get(0);

		return user;
	}

}

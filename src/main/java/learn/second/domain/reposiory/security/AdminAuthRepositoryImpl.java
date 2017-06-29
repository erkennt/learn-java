package learn.second.domain.reposiory.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.Admin;
@Component
public class AdminAuthRepositoryImpl implements AdminAuthRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Admin findOne(String username) {


		String sql = "SELECT * From Admin WHERE AdminId = :AdminId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("AdminId", username);

		List<Admin> usersList = namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Admin>(Admin.class));

		Admin user = usersList.get(0);

		return user;
	}

}

package learn.second.domain.reposiory.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.Admin;

@Component
public class AdminRepositoryImpl implements AdminRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int userCount() {

		String sql = "SELECT 1 From Admin Limit 1";

		List<Admin> usersList = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class));

		return usersList.size();
	}

	public Admin findOne(String username) {

		String sql = "SELECT * From Admin WHERE AdminId = :AdminId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("AdminId", username);

		List<Admin> usersList = namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Admin>(Admin.class));

		Admin user = usersList.get(0);

		return user;
	}

	public void insertAdmin(String adminId, String password) {

		String sql = "insert into Admin(AdminId, Password)" + " values(:AdminId, :Password)";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("AdminId", adminId);
		params.addValue("Password", password);

		namedParameterJdbcTemplate.update(sql, params);

	}

	public List<Admin> findAll() {

		String sql = "SELECT * From Admin";
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class));
	}


	public void deleteAll(){
		String sql = "DELETE FROM Admin";
		jdbcTemplate.update(sql);
	}

	public void insertAdmin(Admin admin) {
		String sql = "insert into Admin(AdminId, Password, Name)"
				+ " values(:AdminId, :Password, :Name)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(admin));

	}
}

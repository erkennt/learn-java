package learn.second.domain.reposiory.repass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.RePasswordPool;

@Component
public class RePasswordPoolRepositoryImpl implements RePasswordPoolRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void insertRePasswordPool(String parameter, String userId) {
		String sql = "insert into RePasswordPool(Parameter, UserId)" + " values(:Parameter, :UserId)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);
		params.addValue("UserId", userId);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public void deleteRePasswordPool(String parameter) {
		String sql = "Delete From RePasswordPool Where Parameter = :Parameter";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<RePasswordPool> getRePasswordPool(String parameter) {
		String sql = "Select * From RePasswordPool Where Parameter = :Parameter";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<RePasswordPool>(RePasswordPool.class));
	}

	public void deleteAll() {
		String sql = "DELETE FROM RePasswordPool";
		jdbcTemplate.update(sql);
	}

	@Override
	public void insertRePasswordPool(RePasswordPool rePasswordPool) {
		String sql = "insert into RePasswordPool(Parameter, UserId, InsDate)" + " values(:Parameter, :UserId, :InsDate)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(rePasswordPool));

	}


	@Override
	public List<RePasswordPool> findAll() {
		String sql = "Select * FROM RePasswordPool";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<RePasswordPool>(RePasswordPool.class));
	}

}

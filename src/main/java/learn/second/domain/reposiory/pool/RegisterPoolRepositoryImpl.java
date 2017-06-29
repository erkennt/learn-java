package learn.second.domain.reposiory.pool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.RegisterPool;

@Component
public class RegisterPoolRepositoryImpl implements RegisterPoolRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void insertRegisterPool(String parameter, String userId) {
		String sql = "insert into RegisterPool(Parameter, UserId)" + " values(:Parameter, :UserId)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);
		params.addValue("UserId", userId);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public void deleteRegisterPool(String parameter) {
		String sql = "Delete From RegisterPool Where Parameter = :Parameter";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<RegisterPool> getRegisterPool(String parameter) {
		String sql = "Select * From RegisterPool Where Parameter = :Parameter";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Parameter", parameter);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<RegisterPool>(RegisterPool.class));
	}

	public void deleteAll() {
		String sql = "DELETE FROM RegisterPool";
		jdbcTemplate.update(sql);
	}

	@Override
	public void insertRegisterPool(RegisterPool registerPool) {
		String sql = "insert into RegisterPool(Parameter, UserId, InsDate)" + " values(:Parameter, :UserId, :InsDate)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(registerPool));

	}

	@Override
	public List<RegisterPool> findAll() {
		String sql = "Select * FROM RegisterPool";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<RegisterPool>(RegisterPool.class));
	}
	public void insertGameSettings(String gameType, String waitMinutes, String logCounts){

	}
}

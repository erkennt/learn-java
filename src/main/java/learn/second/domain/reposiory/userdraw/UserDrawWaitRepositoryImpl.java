package learn.second.domain.reposiory.userdraw;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.UserDrawWait;

@Component
public class UserDrawWaitRepositoryImpl implements UserDrawWaitRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<UserDrawWait> findAll() {
		String sql = "Select * FROM UserDrawWait";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDrawWait>(UserDrawWait.class));
	}

	public void deleteAll() {
		String sql = "DELETE FROM UserDrawWait";
		jdbcTemplate.update(sql);
	}

	public void insertUserDraw(UserDrawWait userDrawWait) {
		String sql = "insert into UserDrawWait(UserId, GameType, NextDraw)" + " values(:UserId, :GameType, :NextDraw)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userDrawWait));
	}

	public void insertUserDraw(String userId, String gameType) {
		///String sql = "insert into UserDrawWait(UserId, GameType, NextDraw)" + " values(:UserId, :GameType, :NextDraw)";
		String sql = "insert into UserDrawWait(UserId, GameType)" + " values(:UserId, :GameType)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);
		params.addValue("GameType", gameType);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<UserDrawWait> getUserDraw(String userId, String gameType) {
		String sql = "Select * From UserDrawWait Where UserId = :UserId And GameType = :GameType";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);
		params.addValue("GameType", gameType);

		return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<UserDrawWait>(UserDrawWait.class));
	}

	public void updateUserDrawWait(String userId, String gameType, Timestamp nextDraw) {
		String sql = "Update UserDrawWait Set NextDraw = :NextDraw Where UserId = :UserId And GameType = :GameType";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);
		params.addValue("GameType", gameType);
		params.addValue("NextDraw", nextDraw);

		namedParameterJdbcTemplate.update(sql, params);;
	}
}

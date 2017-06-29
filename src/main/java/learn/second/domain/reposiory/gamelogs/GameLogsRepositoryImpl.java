package learn.second.domain.reposiory.gamelogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.GameLogs;

@Component
public class GameLogsRepositoryImpl	 implements GameLogsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void createGameLog(long cardId, String userId, int magnification, Long betType, String message){
		String sql = "INSERT INTO GameLogs(CardId, UserId, Magnification, BetType, Message)"
				+ " values(:CardId, :UserId, :Magnification, :BetType, :Message)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CardId", cardId);
		params.addValue("UserId", userId);
		params.addValue("Magnification", magnification);
		params.addValue("BetType", betType);
		params.addValue("Message", message);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<GameLogs> getGameLogs(String gameType, int logCounts){
		String sql = "Select * FROM GameLogs gl INNER JOIN Cards c ON gl.Cardid = c.CardId"
				+" WHERE GameType = :GameType"
				+ " ORDER BY LogId DESC LIMIT :LogCounts";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);
		params.addValue("LogCounts", logCounts);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<GameLogs>(GameLogs.class));
	}

	@Override
	public List<GameLogs> findAll() {
		String sql = "Select * FROM GameLogs";

		return namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<GameLogs>(GameLogs.class));
	}
	public void deleteAll(){
		String sql = "DELETE FROM GameLogs";
		jdbcTemplate.update(sql);
	}

	public void resetAutoIncrement() {
		String sql = "alter table GameLogs auto_increment = 1";
		jdbcTemplate.execute(sql);

	}
	@Override
	public void insertGameLogs(GameLogs gameLogs) {
		String sql = "insert into GameLogs(LogId, CardId, UserId, Magnification, BetType, Message, InsDate)"
	+ " values(:LogId, :CardId, :UserId, :Magnification, :BetType, :Message, :InsDate)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(gameLogs));

	}
}
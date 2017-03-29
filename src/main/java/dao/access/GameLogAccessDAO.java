package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.GameLogDAO;
import model.GameLogsModel;

@Component
public class GameLogAccessDAO implements GameLogDAO {

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

	public List<GameLogsModel> getGameLogs(int logCounts){
		String sql = "Select * FROM GameLogs ORDER BY LogId DESC LIMIT :LogCounts";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("LogCounts", logCounts);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<GameLogsModel>(GameLogsModel.class));
	}
}
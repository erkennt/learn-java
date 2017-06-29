package learn.second.domain.reposiory.gamesetting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.GameSettings;

@Component
public class GameSettingsRepositoryImpl implements GameSettingsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public GameSettings getGameSettings(String gameType) {
		String sql = "Select * FROM GameSettings Where GameType = :GameType";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);

		List<GameSettings> gameSettingsList = namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<GameSettings>(GameSettings.class));

		GameSettings gameSettings = new GameSettings();
		for (GameSettings item : gameSettingsList) {
			gameSettings = item;
			break;
		}
		return gameSettings;
	}

	public void updateCurrentCardId(String gameType, long CardId) {
		String sql = "UPDATE GameSettings SET CurrentCardId = :CardId WHERE GameType = :GameType";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CardId", CardId);
		params.addValue("GameType", gameType);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<GameSettings> getGameSettings2(String gameType) {
		String sql = "Select * FROM GameSettings Where GameType = :GameType";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<GameSettings>(GameSettings.class));
	}

	public void updateWaitTime(int wait, String gameType) {
		String sql = "UPDATE GameSettings SET WaitMinutes = :WaitMinutes WHERE GameType = :GameType";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("WaitMinutes", wait);
		params.addValue("GameType", gameType);

		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<GameSettings> findAll() {
		String sql = "Select * FROM GameSettings";

		return namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<GameSettings>(GameSettings.class));
	}

	public void deleteAll(){
		String sql = "DELETE FROM GameSettings";
		jdbcTemplate.update(sql);
	}

	@Override
	public void insertGameSettings(GameSettings gameSettings) {
		String sql = "insert into GameSettings(GameType, WaitMinutes, LogCounts)" + " values(:GameType, :WaitMinutes, :LogCounts)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(gameSettings));

	}
	public void insertGameSettings(String gameType, int waitMinutes, int logCounts){

		String sql = "insert into GameSettings(GameType, WaitMinutes, LogCounts)" + " values(:GameType, :WaitMinutes, :LogCounts)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);
		params.addValue("WaitMinutes", waitMinutes);
		params.addValue("LogCounts", logCounts);

		namedParameterJdbcTemplate.update(sql, params);
	}
}
package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.GameSettingsDAO;
import model.GameSettingsModel;

@Component
public class GameSettingsAccessDAO implements GameSettingsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<GameSettingsModel> gameSettingsLoad(GameSettingsModel gameSettingsModel) {
		String sql = "Select * FROM GameSettings Where GameType = :GameType";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(gameSettingsModel),
				new BeanPropertyRowMapper<GameSettingsModel>(GameSettingsModel.class));
	}

	public void updateCurrentCardId(String gameType, long CardId){
	String sql ="UPDATE GameSettings SET CurrentCardId = :CardId WHERE GameType = :GameType";

	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("CardId", CardId);
	params.addValue("GameType", gameType);

	namedParameterJdbcTemplate.update(sql, params);
	}
}
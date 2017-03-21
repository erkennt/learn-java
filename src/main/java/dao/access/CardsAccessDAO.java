package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.CardsDAO;
import model.CardsModel;

@Component
public class CardsAccessDAO implements CardsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<CardsModel> getCurrentCards(long currentCardId) {
		String sql = "SELECT * FROM Cards WHERE CardId = :CurrentCardId";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CurrentCardId", currentCardId);

		List<CardsModel> list;

		list = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<CardsModel>(CardsModel.class));
		return list;

	}

	public List<CardsModel> getGameCardList(String gameType) {

		// 開始のカード取得
		String sql1 = "SELECT CardId FROM Cards WHERE GameType = :GameType AND Result = 0 ORDER BY CardId DESC LIMIT 1";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);

		List<CardsModel> startCards = namedParameterJdbcTemplate.query(sql1, params,
				new BeanPropertyRowMapper<CardsModel>(CardsModel.class));


		// 開始から現在までのカードを取得
		long startCardId = 0;

		for (CardsModel list : startCards) {
			startCardId = list.getCardId();
		}

		params.addValue("StartCardId", startCardId);

		String sql2 = "SELECT * FROM Cards WHERE GameType = :GameType AND CardId >= :StartCardId";

		return namedParameterJdbcTemplate.query(sql2, params, new BeanPropertyRowMapper<CardsModel>(CardsModel.class));

	}

	public void createInitialCardData(String gameType) {
		String sql = "INSERT INTO Cards (GameType, UserId, Value, Result) VALUES (:GameType, NULL, 3, 0) ";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public void createCardData(String gameType, String userId, int value, Boolean result){
		String sql = "INSERT INTO Cards (GameType, UserId, Value, Result) VALUES (:GameType, :UserId, :Value, :Result) ";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);
		params.addValue("UserId", userId);
		params.addValue("Value", value);
		params.addValue("Result", result);

		namedParameterJdbcTemplate.update(sql, params);
	}


}
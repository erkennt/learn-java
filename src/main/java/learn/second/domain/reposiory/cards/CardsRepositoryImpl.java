package learn.second.domain.reposiory.cards;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.Cards;

@Component
public class CardsRepositoryImpl implements CardsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Cards> getCurrentCards(long currentCardId) {
		String sql = "SELECT * FROM Cards WHERE CardId = :CurrentCardId";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CurrentCardId", currentCardId);

		List<Cards> list;

		list = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Cards>(Cards.class));
		return list;

	}

	public List<Cards> getGameCardList(String gameType) {

		// 開始のカード取得
		String sql1 = "SELECT CardId FROM Cards WHERE GameType = :GameType AND Result = 0 ORDER BY CardId DESC LIMIT 1";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);

		List<Cards> startCards = namedParameterJdbcTemplate.query(sql1, params,
				new BeanPropertyRowMapper<Cards>(Cards.class));


		// 開始から現在までのカードを取得
		long startCardId = 0;

		for (Cards list : startCards) {
			startCardId = list.getCardId();
		}

		params.addValue("StartCardId", startCardId);

		String sql2 = "SELECT * FROM Cards WHERE GameType = :GameType AND CardId >= :StartCardId";

		return namedParameterJdbcTemplate.query(sql2, params, new BeanPropertyRowMapper<Cards>(Cards.class));

	}

	public void createInitialCardData(String gameType) {
		String sql = "INSERT INTO Cards (GameType, UserId, Value, Result) VALUES (:GameType, :UserId, :Value, :Result) ";

		Random rand = new Random(System.currentTimeMillis());
		int value = rand.nextInt(5) + 1; // 1～5の数値を算出

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("GameType", gameType);
		params.addValue("UserId", "System");
		params.addValue("Value", value);
		params.addValue("Result", 0);


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

	@Override
	public List<Cards> findAll() {
		String sql = "SELECT * FROM Cards";
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Cards>(Cards.class));
	}
	public void deleteAll(){
		String sql = "DELETE FROM  Cards";
		jdbcTemplate.update(sql);
	}

	public void resetAutoIncrement() {
		String sql = "alter table Cards auto_increment = 1";
		jdbcTemplate.execute(sql);

	}

	public void insertCards(Cards initialDataSettings) {
		String sql = "insert into Cards(CardId, GameType, UserId, Value, Result, InsDate)"
				+ " values(:CardId, :GameType, :UserId, :Value, :Result, :InsDate)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(initialDataSettings));

	}
}
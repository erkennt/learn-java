package learn.second.domain.reposiory.datasetting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.second.domain.model.InitialDataSettings;

@Component
public class InitialDataSettingsRepositoryImpl implements InitialDataSettingsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<InitialDataSettings> getInitialAsset(String dataType) {
		String sql = "SELECT * From InitialDataSettings WHERE DataType = :DataType";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("DataType", dataType);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<InitialDataSettings>(InitialDataSettings.class));

	}

	public List<InitialDataSettings> getInitialAsset() {
		String sql = "SELECT * From InitialDataSettings WHERE DataType = 'Asset'";

		return namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<InitialDataSettings>(InitialDataSettings.class));

	}

	public void updateInitialAsset(String value) {
		String sql = "UPDATE InitialDataSettings SET Value = :Value WHERE DataType = 'Asset'";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("Value", value);
		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<InitialDataSettings> findAll() {
		String sql = "SELECT * From InitialDataSettings";

		return namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<InitialDataSettings>(InitialDataSettings.class));
	}

	public void deleteAll() {
		String sql = "DELETE FROM InitialDataSettings";
		jdbcTemplate.update(sql);
	}

	public void insertInitialDataSettings(InitialDataSettings initialDataSettings) {
		String sql = "insert into InitialDataSettings(DataType, Value)" + " values(:DataType, :Value)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(initialDataSettings));

	}

	public void insertInitialDataSettingsAsset(String asset) {
		String sql = "insert into InitialDataSettings(DataType, Value)" + " values(:DataType, :Value)";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("DataType", "Asset");
		params.addValue("Value", asset);
		namedParameterJdbcTemplate.update(sql, params);
	}
}
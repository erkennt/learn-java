package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.InitialDataSettingsDAO;
import model.InitialDataSettingsModel;

@Component
public class InitialDataSettingsAccessDAO implements InitialDataSettingsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<InitialDataSettingsModel> getInitialAsset(String dataType) {
		String sql = "SELECT * From InitialDataSettings WHERE DataType = :DataType";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("DataType", dataType);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<InitialDataSettingsModel>(InitialDataSettingsModel.class));

	}

}
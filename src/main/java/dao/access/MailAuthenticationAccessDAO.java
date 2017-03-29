package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.MailAuthenticationDAO;
import model.MailAuthenticationModel;

@Component
public class MailAuthenticationAccessDAO implements MailAuthenticationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void addAuthenticationPool(String userId, String mailAddress) {
		String sql = "INSERT INTO MailAuthentication(MailAddress, UserId, Status) VALUES(:MailAddress, :UserId, :Status)";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("MailAddress", mailAddress);
		params.addValue("UserId", userId);
		params.addValue("Status", 0);

		namedParameterJdbcTemplate.update(sql, params);
	}
	public List<MailAuthenticationModel> searchAuthenticationPool(String mailAddress) {
		String sql = "SELECT * FROM MailAuthentication WHERE MailAddress = :MailAddress";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("MailAddress", mailAddress);
		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<MailAuthenticationModel>(MailAuthenticationModel.class));
	}

	public List<MailAuthenticationModel> searchAuthenticationPool(String mailAddress, String userId) {
		String sql = "SELECT * FROM MailAuthentication WHERE MailAddress = :MailAddress AND UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("MailAddress", mailAddress);
		params.addValue("UserId", userId);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<MailAuthenticationModel>(MailAuthenticationModel.class));
	}



	public void updateStatus(String mailAddress) {
		String sql = "UPDATE MailAuthentication SET Status = :Status WHERE MailAddress = :MailAddress";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("MailAddress", mailAddress);
		params.addValue("Status", 1);

		namedParameterJdbcTemplate.update(sql, params);
	}
}
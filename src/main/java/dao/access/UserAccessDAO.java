package dao.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import dao.UserDAO;
import model.UserModel;

@Component
public class UserAccessDAO implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	public List<UserModel> userLogin(UserModel userModel) {
		String sql = "Select * FROM User Where UserId = :UserId And Password = :Password";

		return  namedParameterJdbcTemplate.query(sql
				, new BeanPropertySqlParameterSource(userModel)
				, new BeanPropertyRowMapper<UserModel>(UserModel.class));

	}

	public List<UserModel> userTestLogin() {

		String sql = "Select u.* from User as u"
		+" Inner JOIN TestUser as tu ON u.UserId = tu.UserId";

		return  jdbcTemplate.query(sql
				, new BeanPropertyRowMapper<UserModel>(UserModel.class));
	}

	public void userInsert(UserModel userModel) {

		String sql = "insert into User(UserId, Password, NickName, MailAdress, Birthday, Asset, Status, InsDate, UpdDate, UpdUser)"
				+ " values(:UserId, :Password, :NickName, :MailAdress, :Birthday, :Asset, :Status, :InsDate, :UpdDate, :UpdUser)";

		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userModel));
	}


	public void userUpdate(UserModel userModel) {
		// TODO 自動生成されたメソッド・スタブ

	}


	public void userDelete(UserModel userModel) {
		// TODO 自動生成されたメソッド・スタブ

	}


	public void userSerarch(UserModel userModel) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void userStatusUpdate(int status, String userId){

		String sql = "Update User Set Status = :Status Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Status", status);

		namedParameterJdbcTemplate.update(sql, params);
		}

}
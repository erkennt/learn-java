package learn.second.domain.reposiory.users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import learn.second.domain.model.Users;
@Component
public class UsersRepositoryImpl implements UsersRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Users getUser(String UserId) {

		String sql = "SELECT * From Users WHERE UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", UserId);

		List<Users> usersList = namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));

		Users user = new Users();
		for (Users item : usersList){
			user = item;
			break;
		}

		return user;
	}

	public void updateUser(String userId, String nickName, String MailAddress ,Date birthday ,long Asset ,Timestamp updDate ,String updUser) {

		String sql = "Update Users Set NickName = :NickName, MailAddress = :MailAddress, Birthday = :Birthday, Asset = :Asset, UpdDate = UpdDate, UpdUser = UpdUser Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("NickName", nickName);
		params.addValue("MailAddress", MailAddress);
		params.addValue("Birthday", birthday);
		params.addValue("Asset", Asset);
		//params.addValue("UpdDate", updDate);
		//params.addValue("UpdUser", updUser);

		namedParameterJdbcTemplate.update(sql, params);
	}
	public void updateUser(Users users) {
		String sql = "Update Users Set NickName = :NickName, MailAddress = :MailAddress, Birthday = :Birthday, Asset = :Asset, UpdDate = UpdDate, UpdUser = UpdUser Where UserId = :UserId";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(users));

	}
	public void updateAllUserAsset(String asset){

		String sql = "Update Users Set Asset = :Asset";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("Asset", asset);

		namedParameterJdbcTemplate.update(sql, params);
		}


	public void updatePassword(String userId, String newPassword){

		String sql = "Update Users Set Password = :Password Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Password", newPassword);

		namedParameterJdbcTemplate.update(sql, params);
		}

	public void updateStatusActive(String userId){
		String sql = "Update Users Set Status = :Status Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Status", "1");;

		namedParameterJdbcTemplate.update(sql, params);
		}

	public void updateStatusInactive(String userId){
		String sql = "Update Users Set Status = :Status Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Status", "2");;

		namedParameterJdbcTemplate.update(sql, params);
		}



	public List<Users> findAll(){
		String sql = "Select * FROM Users";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Users>(Users.class));
	}


	public void deleteAll(){
		String sql = "DELETE FROM Users";
		jdbcTemplate.update(sql);
	}

	public List<Users> getUsersByEMail(String mailAddress){
		String sql = "SELECT * From Users WHERE MailAddress = :MailAddress";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("MailAddress", mailAddress);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));
	}




	public List<Users> getUsersByUserIdAndEmail(String userId, String mailAddress){
		String sql = "SELECT * From Users WHERE UserId = :UserId And MailAddress = :MailAddress";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);
		params.addValue("MailAddress", mailAddress);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));
	}



	public List<Users> getUsersByNickName(String nickName) {
		String sql = "SELECT * From Users WHERE NickName = :NickName";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("NickName", nickName);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));
	}


	public List<Users> getUsersByUserId(String userId) {
		String sql = "SELECT * From Users WHERE UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("UserId", userId);

		return namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));
	}














	public List<Users> userLogin(Users userModel) {
		String sql = "Select * FROM Users Where UserId = :UserId And Password = :Password";

		return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(userModel),
				new BeanPropertyRowMapper<Users>(Users.class));

	}

	public List<Users> userLogin(String userId, String password) {
		String sql = "Select * FROM Users Where UserId = :UserId And Password = :Password";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Password", password);

		return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Users>(Users.class));

	}

	public List<Users> userTestLogin() {

		String sql = "Select u.* from Users as u" + " Inner JOIN TestUser as tu ON u.UserId = tu.UserId";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Users>(Users.class));
	}

	public void userInsert(Users userModel) {

		String sql = "insert into Users(UserId, Password, NickName, MailAddress, Birthday, Asset, Status, InsDate, UpdDate, UpdUser)"
				+ " values(:UserId, :Password, :NickName, :MailAddress, :Birthday, :Asset, :Status, :InsDate, :UpdDate, :UpdUser)";

		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userModel));
	}
	public void insertUsers(Users users) {

		String sql = "insert into Users(UserId, Password, NickName, MailAddress, Birthday, Asset, Status, InsDate, UpdDate, UpdUser)"
				+ " values(:UserId, :Password, :NickName, :MailAddress, :Birthday, :Asset, :Status, :InsDate, :UpdDate, :UpdUser)";

		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(users));
	}

	public void updateActive(String userId) {

		String sql = "Update Users Set Status = :Status Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Status", 1);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public void userAssetUpdate(String userId, long prize) {
		String sql = "Update Users Set Asset = Asset + (:Prize) Where UserId = :UserId";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Prize", prize);

		namedParameterJdbcTemplate.update(sql, params);
	}

	public void updateUserSessionInformation(HttpSession session) {
		Users userModel = (Users) session.getAttribute("session");

		Users newUserModel = this.getUserInformation(userModel);
		session.setAttribute("session", newUserModel);
	}

	private Users getUserInformation(Users userModel) {
		String sql = "Select * FROM Users Where UserId = :UserId";

		List<Users> list = namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(userModel),
				new BeanPropertyRowMapper<Users>(Users.class));

		return list.get(0);
	}

	public List<Users> getUserInformation(String userId) {
		String sql = "Select * FROM Users Where UserId = :UserId";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);

		return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Users>(Users.class));

	}






	public List<Users> getGrid(String userId, String nickName, String mailAddress){

		String sql = "Select * FROM Users Where"
				+ " UserId LIKE IFNULL(:UserId, UserId)"
				+ " And NickName LIKE IFNULL(:NickName, NickName)"
				+ " And MailAddress LIKE IFNULL(:MailAddress, MailAddress)";

		MapSqlParameterSource params = new MapSqlParameterSource();

		// NULL値の場合は部分一致検索を行わない
		userId = enableValueOrNull(userId);
		nickName = enableValueOrNull(nickName);
		mailAddress = enableValueOrNull(mailAddress);

		params.addValue("UserId", userId);
		params.addValue("NickName", nickName);
		params.addValue("MailAddress", mailAddress);

		return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Users>(Users.class));
	}

	public List<Integer> getSearchResultCount(String userId, String nickName, String mailAddress){

		String sql = "Select Count(1) FROM Users Where"
				+ " UserId = IFNULL(:UserId, UserId)"
				+ " And NickName = IFNULL(:NickName, NickName)"
				+ " And MailAddress = IFNULL(:MailAddress, MailAddress)";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("NickName", nickName);
		params.addValue("MailAddress", mailAddress);

		List<Integer> count = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Integer>(Integer.class));
		return count;
	}


	private String enableValueOrNull(String value) {
		value = (StringUtils.isEmpty(value)) ? null : "%"+value+"%";
		return value;
	}

	@Override
	public List<Users> getUsersByPassword(String userId, String password) {
		String sql = "SELECT * From Users WHERE UserId = :UserId And Password = :Password";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("UserId", userId);
		params.addValue("Password", password);

		return  namedParameterJdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Users>(Users.class));

	}


}

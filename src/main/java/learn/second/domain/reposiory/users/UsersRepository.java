package learn.second.domain.reposiory.users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import learn.second.domain.model.Users;

public interface UsersRepository {
	public Users getUser(String UserId);

	public void updateUser(String userId, String nickName, String MailAddress, Date birthday, long Asset,
			Timestamp updDate, String updUser);

	public void updatePassword(String userId, String newPassword);

	public void updateStatusActive(String userId);

	public void updateStatusInactive(String userId);

	public List<Users> findAll();

	public void deleteAll();

	// ログイン
	public List<Users> userLogin(Users userModel);

	// テストユーザーログイン
	public List<Users> userTestLogin();

	// 登録
	public void userInsert(Users userModel);

	public void insertUsers(Users users);

	// 更新
	public void updateUser(Users users);

	// ユーザーステータス更新
	public void updateActive(String userId);

	// ユーザーの資産の増減
	public void userAssetUpdate(String userId, long prize);

	// ユーザーセッションン情報の更新
	public void updateUserSessionInformation(HttpSession session);

	public List<Users> getUserInformation(String userId);

	public List<Users> userLogin(String userId, String password);

	public List<Users> getGrid(String userId, String nickName, String mailAddress);

	public List<Integer> getSearchResultCount(String userId, String nickName, String mailAddress);

	public List<Users> getUsersByEMail(String mailAddress);

	public List<Users> getUsersByUserIdAndEmail(String userId, String mailAddress);

	public List<Users> getUsersByNickName(String nickName);

	public List<Users> getUsersByUserId(String userId);

	public void updateAllUserAsset(String asset);

	public List<Users> getUsersByPassword(String userId, String password);

}

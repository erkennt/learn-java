package learn.second.domain.service.users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import learn.second.app.admin.user.UserEditForm;
import learn.second.domain.model.Users;

public interface UsersService {

	public Users getUser(String userId);

	public UserEditForm getEditUser(String userId);

	public List<Users> getSearchResult(String userId, String nickName, String mailAddress);

	public void updateUser(String userId, String nickName, String MailAddress, Date birthday, long Asset,
			Timestamp updDate, String updUser);

	public void updatePassword(String userId, String newPassword);

	public void updateStatusActive(String userId);

	public void updateStatusInactive(String userId);

	public List<Users> findAll();

	public void deleteAll();

	public List<Users> getAll();

	public List<Integer> insertUsersList(List<Users> list);

	public Users getUsersByEmail(String mailAddress);

	public Users getUsersByUserIdAndEmail(String userId, String mailAddress);

	public Users getUsersByNickName(String nickName);

	public Users getUsersByUserId(String userId);

	public void insertTemporaryUser(Users users);

	public void updateAllUserAsset(String asset);

	public void updateUsers(Users users);

	public Users getUsersByPassword(String userId, String password);
}

package learn.second.domain.service.users;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import learn.second.app.admin.user.UserEditForm;
import learn.second.domain.model.Users;
import learn.second.domain.reposiory.users.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public Users getUser(String userId) {

		Users users = usersRepository.getUser(userId);

		return users;
	}

	public List<Users> getSearchResult(String userId, String nickName, String mailAddress) {

		// 空文字をNULlに置き換え
		userId = emptyToNull(userId);
		nickName = emptyToNull(nickName);
		mailAddress = emptyToNull(mailAddress);

		List<Users> searchResult = usersRepository.getGrid(userId, nickName, mailAddress);

		return searchResult;
	}

	public void updateUser(String userId, String nickName, String MailAddress, Date birthday, long Asset,
			Timestamp updDate, String updUser) {

		usersRepository.updateUser(userId, nickName, MailAddress, birthday, Asset, updDate, updUser);
	}

	private String emptyToNull(String value) {
		value = (StringUtils.isEmpty(value)) ? null : value;
		return value;
	}

	public void updatePassword(String userId, String newPassword) {

		// パスワードを暗号化する
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordHash = encoder.encode(newPassword);

		usersRepository.updatePassword(userId, passwordHash);

	}

	@Override
	public void updateStatusActive(String userId) {
		usersRepository.updateStatusActive(userId);

	}

	@Override
	public void updateStatusInactive(String userId) {
		usersRepository.updateStatusInactive(userId);

	}

	@Override
	public UserEditForm getEditUser(String userId) {

		Users users = usersRepository.getUser(userId);

		UserEditForm userEdit = new UserEditForm();

		userEdit.setUserId(users.getUserId());
		userEdit.setNickName(users.getNickName());
		userEdit.setMailAddress(users.getMailAddress());
		userEdit.setBirthday(users.getBirthday());
		userEdit.setAsset(users.getAsset());
		userEdit.setStatus(users.getStatus());
		return userEdit;
	}

	public List<Users> findAll() {
		List<Users> usersList = usersRepository.findAll();

		if (usersList.size() < 1) {
			Users users = new Users();
			usersList.add(users);
		}

		return usersList;
	}

	public void deleteAll() {
		usersRepository.deleteAll();
	}

	public List<Users> getAll() {
		return usersRepository.findAll();
	}

	@Override
	public List<Integer> insertUsersList(List<Users> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (Users users : list) {
			rowCount++;
			try {
				usersRepository.insertUsers(users);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	public Users getUsersByEmail(String mailAddress){
		List<Users> usersList = usersRepository.getUsersByEMail(mailAddress);

		for(Users users : usersList){
			return users;
		}

		return null;
	}


	public Users getUsersByUserIdAndEmail(String userId, String mailAddress){
		List<Users> usersList = usersRepository.getUsersByUserIdAndEmail(userId, mailAddress);

		for(Users users : usersList){
			return users;
		}

		return null;
	}
	public Users getUsersByNickName(String nickName){

		List<Users> usersList = usersRepository.getUsersByNickName(nickName);

		for(Users users : usersList){
			return users;
		}

		return null;

	}

	public Users getUsersByUserId(String userId){
		List<Users> usersList = usersRepository.getUsersByUserId(userId);

		for(Users users : usersList){
			return users;
		}

		return null;
	}


	public void insertTemporaryUser(Users users) {
		usersRepository.insertUsers(users);

	}

	public void updateAllUserAsset(String asset){
		usersRepository.updateAllUserAsset(asset);
	}

	@Override
	public void updateUsers(Users users) {
		usersRepository.updateUser(users);

	}

	public Users getUsersByPassword(String userId, String password){
		// パスワードを暗号化する
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordHash = encoder.encode(password);

	List<Users> usersList = usersRepository.getUsersByPassword(userId, passwordHash);

	for(Users users : usersList){
		return users;
	}

	return null;
}
}

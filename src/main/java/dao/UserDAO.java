package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.UserModel;
@Component
public interface UserDAO {

	//ログイン
	public List<UserModel> userLogin(UserModel userModel);

	//テストユーザーログイン
	public List<UserModel> userTestLogin();
}

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

	//登録
	public void userInsert(UserModel userModel);

	//更新
	public void userUpdate(UserModel userModel);


	//削除
	public void userDelete(UserModel userModel);

	//検索
	public void userSerarch(UserModel userModel);


	//ユーザーステータス更新
	public void userStatusUpdate(int status, String userId);

}

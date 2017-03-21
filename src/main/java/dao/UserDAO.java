package dao;

import java.util.List;

import javax.servlet.http.HttpSession;

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

	// ユーザーの資産の増減
	public void userAssetUpdate(String userId, long prize);

	// ユーザーセッションン情報の更新
	public void updateUserSessionInformation(HttpSession session);
}

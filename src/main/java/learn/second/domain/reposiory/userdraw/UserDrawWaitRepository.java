package learn.second.domain.reposiory.userdraw;

import java.sql.Timestamp;
import java.util.List;

import learn.second.domain.model.UserDrawWait;

public interface UserDrawWaitRepository {

	public List<UserDrawWait> findAll();

	public void deleteAll();

	public void insertUserDraw(UserDrawWait userDrawWait);

	public void insertUserDraw(String userId, String gameType);

	public List<UserDrawWait> getUserDraw(String userId, String gameType);

	public void updateUserDrawWait(String userId, String gameType, Timestamp nextDraw);
}

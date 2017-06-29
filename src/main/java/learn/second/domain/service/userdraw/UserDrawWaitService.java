package learn.second.domain.service.userdraw;

import java.sql.Timestamp;
import java.util.List;

import learn.second.domain.model.UserDrawWait;

public interface UserDrawWaitService {

	public List<UserDrawWait> findAll();

	public void deleteAll();

	public List<Integer> inserUserDrawWaitList(List<UserDrawWait> list);

	public void insertUserDraw(UserDrawWait userDrawWait);

	public UserDrawWait getUserDraw(String userId, String gameType);

	public void updateUserDrawWait(String userId, String gameType, Timestamp nextDraw);
}

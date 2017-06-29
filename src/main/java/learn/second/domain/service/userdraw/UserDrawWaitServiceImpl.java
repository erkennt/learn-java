package learn.second.domain.service.userdraw;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.UserDrawWait;
import learn.second.domain.reposiory.userdraw.UserDrawWaitRepository;

@Service
public class UserDrawWaitServiceImpl implements UserDrawWaitService {
	@Autowired
	UserDrawWaitRepository userDrawWaitRepository;

	@Override
	public List<UserDrawWait> findAll() {

		List<UserDrawWait> userDrawWaitList = userDrawWaitRepository.findAll();
		if (userDrawWaitList.size() < 1) {
			UserDrawWait userDrawWait = new UserDrawWait();
			userDrawWaitList.add(userDrawWait);
		}

		return userDrawWaitList;
	}

	public void deleteAll() {
		userDrawWaitRepository.deleteAll();
	}

	@Override
	public List<Integer> inserUserDrawWaitList(List<UserDrawWait> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (UserDrawWait userDrawWait : list) {
			rowCount++;
			try {
				userDrawWaitRepository.insertUserDraw(userDrawWait);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	@Override
	public void insertUserDraw(UserDrawWait userDrawWait) {
		userDrawWaitRepository.insertUserDraw(userDrawWait);

	}

	@Override
	public UserDrawWait getUserDraw(String userId, String gameType) {
		List<UserDrawWait> userDrawWaitList = userDrawWaitRepository.getUserDraw(userId, gameType);
		for(UserDrawWait userDrawWait : userDrawWaitList){
			return userDrawWait;
		}

		return null;
	}

	@Override
	public void updateUserDrawWait(String userId, String gameType, Timestamp nextDraw) {
		try{
			userDrawWaitRepository.insertUserDraw(userId, gameType);
		} catch(Exception e){
			userDrawWaitRepository.updateUserDrawWait(userId, gameType, nextDraw);
		}

	}
}

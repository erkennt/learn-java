package learn.second.domain.service.repass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.RePasswordPool;
import learn.second.domain.reposiory.repass.RePasswordPoolRepository;
@Service
public class RePasswordPoolServiceImpl implements RePasswordPoolService{

	@Autowired
	RePasswordPoolRepository rePasswordPoolRepository;

	@Override
	public void insertRePasswordPool(String parameter, String userId) {

		rePasswordPoolRepository.insertRePasswordPool(parameter, userId);
	}

	@Override
	public void deleteRePasswordPool(String parameter) {
		rePasswordPoolRepository.deleteRePasswordPool(parameter);

	}

	@Override
	public RePasswordPool getRePasswordPool(String parameter) {
		List<RePasswordPool> rePasswordPoolList = rePasswordPoolRepository.getRePasswordPool(parameter);
		for(RePasswordPool rePasswordPool : rePasswordPoolList){
			return rePasswordPool;
		}
		return null;
	}

	public void deleteAll() {
		rePasswordPoolRepository.deleteAll();
	}

	@Override
	public List<Integer> inserRePasswordPoolList(List<RePasswordPool> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (RePasswordPool rePasswordPool : list) {
			rowCount++;
			try {
				rePasswordPoolRepository.insertRePasswordPool(rePasswordPool);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	@Override
	public void insertRePasswordPool(RePasswordPool rePasswordPool) {
		rePasswordPoolRepository.insertRePasswordPool(rePasswordPool);

	}

	@Override
	public List<RePasswordPool> findAll() {
			List<RePasswordPool> rePasswordPoolList = rePasswordPoolRepository.findAll();

			if (rePasswordPoolList.size() < 1) {
				RePasswordPool rePasswordPool = new RePasswordPool();
				rePasswordPoolList.add(rePasswordPool);
			}

			return rePasswordPoolList;
		}
}

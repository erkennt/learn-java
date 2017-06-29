package learn.second.domain.service.repass;

import java.util.List;

import learn.second.domain.model.RePasswordPool;

public interface RePasswordPoolService {
	public void insertRePasswordPool(String parameter, String userId);

	public void deleteRePasswordPool(String parameter);

	public RePasswordPool getRePasswordPool(String parameter);

	public void deleteAll();

	public List<Integer> inserRePasswordPoolList(List<RePasswordPool> list);

	public void insertRePasswordPool(RePasswordPool rePasswordPool);

	public List<RePasswordPool> findAll();
}

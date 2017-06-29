package learn.second.domain.service.pool;

import java.util.List;

import learn.second.domain.model.RegisterPool;

public interface RegisterPoolService {
	void insertRegisterPool(String parameter, String userId);

	void deleteRegisterPool(String parameter);

	RegisterPool getRegisterPool(String parameter);

	public void deleteAll();

	public List<Integer> inserRegisterPoolList(List<RegisterPool> list);

	public void insertRegisterPool(RegisterPool registerPool);

	public List<RegisterPool> findAll();
}

package learn.second.domain.reposiory.pool;

import java.util.List;

import learn.second.domain.model.RegisterPool;

public interface RegisterPoolRepository {

	public void insertRegisterPool(String parameter, String userId);

	public void deleteRegisterPool(String parameter);

	public List<RegisterPool> getRegisterPool(String parameter);

	public void deleteAll();

	public void insertRegisterPool(RegisterPool registerPool);

	public List<RegisterPool> findAll();

}

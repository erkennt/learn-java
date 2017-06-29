package learn.second.domain.reposiory.repass;

import java.util.List;

import learn.second.domain.model.RePasswordPool;

public interface RePasswordPoolRepository {

	public void insertRePasswordPool(String parameter, String userId);

	public void deleteRePasswordPool(String parameter);

	public List<RePasswordPool> getRePasswordPool(String parameter);

	public void deleteAll();

	public void insertRePasswordPool(RePasswordPool rePasswordPool);

	public List<RePasswordPool> findAll();
}

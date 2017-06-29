package learn.second.domain.service.pool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.RegisterPool;
import learn.second.domain.reposiory.pool.RegisterPoolRepository;
@Service
public class RegisterPoolServiceImpl implements RegisterPoolService{

	@Autowired
	RegisterPoolRepository registerPoolRepository;

	@Override
	public void insertRegisterPool(String parameter, String userId) {

		registerPoolRepository.insertRegisterPool(parameter, userId);
	}

	@Override
	public void deleteRegisterPool(String parameter) {
		registerPoolRepository.deleteRegisterPool(parameter);

	}

	@Override
	public RegisterPool getRegisterPool(String parameter) {
		List<RegisterPool> registerPoolList = registerPoolRepository.getRegisterPool(parameter);
		for(RegisterPool legisterPool : registerPoolList){
			return legisterPool;
		}
		return null;
	}

	public void deleteAll() {
		registerPoolRepository.deleteAll();
	}

	@Override
	public List<Integer> inserRegisterPoolList(List<RegisterPool> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (RegisterPool registerPool : list) {
			rowCount++;
			try {
				registerPoolRepository.insertRegisterPool(registerPool);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	@Override
	public void insertRegisterPool(RegisterPool registerPool) {
		registerPoolRepository.insertRegisterPool(registerPool);

	}

	@Override
	public List<RegisterPool> findAll() {
		List<RegisterPool> registerPoolList = registerPoolRepository.findAll();

		if (registerPoolList.size() < 1) {
			RegisterPool registerPool = new RegisterPool();
			registerPoolList.add(registerPool);
		}

		return registerPoolList;
	}
}

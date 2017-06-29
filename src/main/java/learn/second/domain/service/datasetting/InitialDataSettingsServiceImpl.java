package learn.second.domain.service.datasetting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.InitialDataSettings;
import learn.second.domain.reposiory.datasetting.InitialDataSettingsRepository;

/**
 * @author ehayashi
 *
 */
@Service
public class InitialDataSettingsServiceImpl implements InitialDataSettingsService {
	@Autowired
	private InitialDataSettingsRepository initialDataSettingsRepository;

	public String getInitialAsset() {

		List<InitialDataSettings> initialDataList = initialDataSettingsRepository.getInitialAsset();
		InitialDataSettings initialData = null;
		String value = "";
		if (initialDataList.size() > 0) {
			initialData = initialDataList.get(0);
			value = initialData.getValue();
		}
		return value;
	}

	public void updateInitialAsset(String value) {
		initialDataSettingsRepository.updateInitialAsset(value);
	}

	public List<InitialDataSettings> findAll() {
		List<InitialDataSettings> initialDataSettingsList = initialDataSettingsRepository.findAll();

		if (initialDataSettingsList.size() < 1) {
			InitialDataSettings initialDataSettings = new InitialDataSettings();
			initialDataSettingsList.add(initialDataSettings);
		}

		return initialDataSettingsList;
	}

	public void deleteAll() {
		initialDataSettingsRepository.deleteAll();
	}

	@Override
	public List<Integer> insertInitialDataSettingsList(List<InitialDataSettings> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (InitialDataSettings initialDataSettings : list) {
			rowCount++;
			try {
				initialDataSettingsRepository.insertInitialDataSettings(initialDataSettings);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	@Override
	public void insertInitialInitialDataSettingsAsset(String asset) {
		try{
		initialDataSettingsRepository.insertInitialDataSettingsAsset(asset);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

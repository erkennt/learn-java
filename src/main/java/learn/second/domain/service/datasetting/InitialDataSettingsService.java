package learn.second.domain.service.datasetting;

import java.util.List;

import learn.second.domain.model.InitialDataSettings;

public interface InitialDataSettingsService {

	public String getInitialAsset();

	public void updateInitialAsset(String value);

	public List<InitialDataSettings> findAll();

	public void deleteAll();

	public List<Integer> insertInitialDataSettingsList(List<InitialDataSettings> list);

	public void insertInitialInitialDataSettingsAsset(String asset);
}

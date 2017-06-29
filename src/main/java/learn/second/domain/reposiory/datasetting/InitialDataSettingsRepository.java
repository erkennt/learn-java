package learn.second.domain.reposiory.datasetting;

import java.util.List;

import learn.second.domain.model.InitialDataSettings;

public interface InitialDataSettingsRepository {

	public List<InitialDataSettings> getInitialAsset(String dataType);

	public List<InitialDataSettings> getInitialAsset();

	public void updateInitialAsset(String value);

	public List<InitialDataSettings> findAll();

	public void deleteAll();

	public void insertInitialDataSettings(InitialDataSettings initialDataSettings);

	public void insertInitialDataSettingsAsset(String asset);

}

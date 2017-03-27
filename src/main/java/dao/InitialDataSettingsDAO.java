package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.InitialDataSettingsModel;
@Component
public interface InitialDataSettingsDAO {

	//ログイン
	public List<InitialDataSettingsModel> getInitialAsset(String dataType);
}

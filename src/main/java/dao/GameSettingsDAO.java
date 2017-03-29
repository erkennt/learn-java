package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.GameSettingsModel;
@Component
public interface GameSettingsDAO {

	//ゲーム設定の取得
	public List<GameSettingsModel> gameSettingsLoad(GameSettingsModel gameSettingsModel);

	//現在のID取得
	public void updateCurrentCardId(String gameType, long CardId);

}

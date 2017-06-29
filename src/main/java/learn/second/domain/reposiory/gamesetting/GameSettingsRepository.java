package learn.second.domain.reposiory.gamesetting;

import java.util.List;

import learn.second.domain.model.GameSettings;

public interface GameSettingsRepository {

	// ゲーム設定の取得
	public GameSettings getGameSettings(String gameType);

	// 現在のID取得
	public void updateCurrentCardId(String gameType, long CardId);

	public List<GameSettings> getGameSettings2(String gameType);

	public void updateWaitTime(int wait, String gameType);

	public List<GameSettings> findAll();

	public void deleteAll();

	public void insertGameSettings(GameSettings gameSettings);

	public void insertGameSettings(String gameType, int waitMinutes, int logCounts);
}

package learn.second.domain.service.gamesettings;

import java.util.List;

import learn.second.domain.model.GameSettings;

public interface GameSettingsService {

	public int getWaitTime(String gameType);

	public GameSettings getWaitTime2(String gameType);

	public void updateWaitTime(int wait, String gameType);

	public List<GameSettings> findAll();

	public void deleteAll();

	public List<Integer> insertGameSettingsList(List<GameSettings> list);

	public void insertGameSettings(String gameType, int waitMinutes, int logCounts);
}

package learn.second.domain.service.gamesettings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.GameSettings;
import learn.second.domain.reposiory.gamesetting.GameSettingsRepository;

@Service
public class GameSettingsServiceImpl implements GameSettingsService {

	@Autowired
	GameSettingsRepository gameSettingsRepository;

	public void updateWaitTime(int wait, String gameType) {
		gameSettingsRepository.updateWaitTime(wait, gameType);
	}

	public int getWaitTime(String gameType) {
		List<GameSettings> gameSettingsList = gameSettingsRepository.getGameSettings2(gameType);
		GameSettings gameSettings = null;
		int value = 0;
		if (gameSettingsList.size() > 0) {
			gameSettings = gameSettingsList.get(0);
			value = gameSettings.getWaitMinutes();
		}
		return value;
	}

	public GameSettings getWaitTime2(String gameType) {
		List<GameSettings> gameSettingsList = gameSettingsRepository.getGameSettings2(gameType);

		for (GameSettings gameSettings : gameSettingsList) {
			return gameSettings;
		}
		return null;
	}

	@Override
	public List<GameSettings> findAll() {
		List<GameSettings> gameSettingsList = gameSettingsRepository.findAll();

		if (gameSettingsList.size() < 1) {
			GameSettings gameSettings = new GameSettings();
			gameSettingsList.add(gameSettings);
		}

		return gameSettingsList;
	}

	public void deleteAll() {
		gameSettingsRepository.deleteAll();
	}

	public List<Integer> insertGameSettingsList(List<GameSettings> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (GameSettings gameSettings : list) {
			rowCount++;
			try {
				gameSettingsRepository.insertGameSettings(gameSettings);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	public void insertGameSettings(String gameType, int waitMinutes, int logCounts) {
		try {
			gameSettingsRepository.insertGameSettings(gameType, waitMinutes, logCounts);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

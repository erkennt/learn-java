package learn.second.domain.service.gamelogs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.GameLogs;
import learn.second.domain.reposiory.gamelogs.GameLogsRepository;

@Service
public class GameLogsServiceImpl implements GameLogsService {

	@Autowired
	GameLogsRepository gameLogsRepository;

	@Override
	public List<GameLogs> findAll() {
		List<GameLogs> gameLogsList = gameLogsRepository.findAll();

		if (gameLogsList.size() < 1) {
			GameLogs gameLogs = new GameLogs();
			gameLogsList.add(gameLogs);
		}

		return gameLogsList;
	}

	public void deleteAll() {
		gameLogsRepository.deleteAll();
	}

	public void resetAutoIncrement() {
		gameLogsRepository.resetAutoIncrement();

	}

	public List<Integer> insertGameLogsList(List<GameLogs> list){
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (GameLogs gameLogs : list) {
			rowCount++;
			try {
				gameLogsRepository.insertGameLogs(gameLogs);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}
}

package learn.second.domain.reposiory.gamelogs;

import java.util.List;

import learn.second.domain.model.GameLogs;

public interface GameLogsRepository {

	// Logの作成
	public void createGameLog(long cardId, String userId, int magnification, Long betType, String message);

	// Logの取得
	public List<GameLogs> getGameLogs(String gameType, int logCounts);

	public List<GameLogs> findAll();

	public void deleteAll();

	public void resetAutoIncrement();

	public void insertGameLogs(GameLogs gameLogs);
}

package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.GameLogsModel;
@Component
public interface GameLogDAO {


	// Logの作成
	public void createGameLog(long cardId, String userId, int magnification, Long betType, String message);

	// Logの取得
	public List<GameLogsModel> getGameLogs(String gameType, int logCounts);

}

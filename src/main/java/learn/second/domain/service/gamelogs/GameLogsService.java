package learn.second.domain.service.gamelogs;

import java.util.List;

import learn.second.domain.model.GameLogs;

public interface GameLogsService {

	public List<GameLogs> findAll();

	public void deleteAll();

	public void resetAutoIncrement();

	public List<Integer> insertGameLogsList(List<GameLogs> list);
}

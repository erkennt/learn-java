package learn.second.domain.service.cards;

import java.util.List;
import java.util.Map;

import learn.second.domain.model.Cards;

public interface CardsService {

	public List<Cards> findAll();

	public void deleteAll();

	public void resetAutoIncrement();

	public List<Integer> insertCardsList(List<Cards> list);

	public Map<String, Object> getTopPageItems(String gameType);

	public void createFalseCardData(String gameType);
}

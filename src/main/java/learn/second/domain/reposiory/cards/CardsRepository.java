package learn.second.domain.reposiory.cards;

import java.util.List;

import learn.second.domain.model.Cards;

public interface CardsRepository {

	// アウト後から現在までのカードを取得
	public List<Cards> getGameCardList(String gameType);

	// 現在のカードを取得
	public List<Cards> getCurrentCards(long currentCardId);

	// イニシャルデータの作成
	public void createInitialCardData(String gameType);

	// 作成
	public void createCardData(String gameType, String userId, int value, Boolean result);

	public List<Cards> findAll();

	public void deleteAll();

	public void resetAutoIncrement();

	public void insertCards(Cards cards);

}

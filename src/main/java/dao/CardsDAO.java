package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.CardsModel;
@Component
public interface CardsDAO {

	// アウト後から現在までのカードを取得
	public List<CardsModel> getGameCardList(String gameType);

	// 現在のカードを取得
	public List<CardsModel> getCurrentCards(long currentCardId);

	// イニシャルデータの作成
	public void createInitialCardData(String gameType);

	// 作成
	public void createCardData(String gameType, String userId, int value, Boolean result);

}

package learn.second.domain.service.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.second.domain.model.Cards;
import learn.second.domain.model.Users;
import learn.second.domain.reposiory.cards.CardsRepository;
import learn.second.domain.reposiory.users.UsersRepository;

@Service
public class CardsServiceImpl implements CardsService {
	@Autowired
	private CardsRepository cardsRepository;
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Cards> findAll() {
		List<Cards> cardsList = cardsRepository.findAll();

		if (cardsList.size() < 1) {
			Cards cards = new Cards();
			cardsList.add(cards);
		}

		return cardsList;
	}

	public void deleteAll() {
		cardsRepository.deleteAll();
	}

	@Override
	public void resetAutoIncrement() {
		cardsRepository.resetAutoIncrement();

	}

	@Override
	public List<Integer> insertCardsList(List<Cards> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (Cards cards : list) {
			rowCount++;
			try {
				cardsRepository.insertCards(cards);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

	@Override
	public Map<String, Object> getTopPageItems(String gameType) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Cards> list = cardsRepository.getGameCardList(gameType);
		String userId = "";
		String nickName = "";

		if (list.size() > 0) {

			Integer magnification = list.size();
			userId = list.get(list.size() - 1).getUserId();
			Users users = null;
			try {
				users = usersRepository.getUser(userId);
			} catch (Exception e) {

			}
			if (users != null) {
				nickName = users.getNickName();
			}

			map.put("Magnification", magnification);
			map.put("NickName", nickName);

		} else {
			map.put("Magnification", "0");
			map.put("NickName", nickName);

		}
		return map;
	}

	public void createFalseCardData(String gameType) {
		Random rand = new Random(System.currentTimeMillis());
		int value = rand.nextInt(5) + 1; // 1～5の数値を算出
		cardsRepository.createCardData(gameType, null, value, false);
	}

}

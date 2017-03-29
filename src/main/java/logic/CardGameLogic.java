package logic;

import org.springframework.stereotype.Component;

@Component
public class CardGameLogic {

	public boolean donuts(int oldValue, int currentValue) {
		boolean result = (oldValue != currentValue);

		return result;
	}

	public boolean highLow(int oldValue, int currentValue, String select) {
		boolean result = Boolean.FALSE;

		if (select.equals("big")) {
			result = (oldValue <= currentValue);

		}

		if (select.equals("small")) {
			result = (oldValue >= currentValue);
		}

		return result;
	}

}

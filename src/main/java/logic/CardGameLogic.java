package logic;

import org.springframework.stereotype.Component;

@Component
public class CardGameLogic {

	public boolean donuts(int newValue, int currentValue) {
		boolean result = (newValue != currentValue);

		return result;
	}

	public boolean highLow(int newValue, int currentValue, String select) {
		boolean result = Boolean.FALSE;

		if (select.equals("big")) {
			result = (newValue >= currentValue);

		}

		if (select.equals("small")) {
			result = (newValue <= currentValue);
		}

		return result;
	}

}

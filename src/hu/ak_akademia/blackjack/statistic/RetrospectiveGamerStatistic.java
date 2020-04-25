package hu.ak_akademia.blackjack.statistic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;

public class RetrospectiveGamerStatistic {

	private Map<Carddeck, List<Card>> hittingSteps;

	public RetrospectiveGamerStatistic(List<Card> cardsInHand, Carddeck carddeck) {
		buildMap(cardsInHand, carddeck);
	}

	public int getHittingStepsSize() {
		return hittingSteps.size();
	}

	private void buildMap(List<Card> cardsInHand, Carddeck carddeck) {
		hittingSteps = new LinkedHashMap<Carddeck, List<Card>>();

		while (cardsInHand.size() > 1) {

			Carddeck tempCarddeck = new Carddeck(carddeck.getCarddeck());
			List<Card> tempCardsInHand = new ArrayList<>(cardsInHand);
			hittingSteps.put(tempCarddeck, tempCardsInHand);
			Card card = cardsInHand.get(cardsInHand.size() - 1);
			carddeck.addCard(card);
			cardsInHand.remove(card);
		}

	}

	public List<Card> getCardsInHand(int hittingNumber) {
		if (hittingNumber > hittingSteps.size()) {
			return null;
		}
		int index = hittingSteps.size() - hittingNumber;
		List<Entry<Carddeck, List<Card>>> indexedList = new ArrayList<Map.Entry<Carddeck, List<Card>>>(hittingSteps.entrySet());
		Entry<Carddeck, List<Card>> entry = indexedList.get(index);
		return entry.getValue();
	}

	public Carddeck getCarddeck(int hittingNumber) {
		if (hittingNumber > hittingSteps.size()) {
			return null;
		}
		int index = hittingSteps.size() - hittingNumber;
		List<Entry<Carddeck, List<Card>>> indexedList = new ArrayList<Map.Entry<Carddeck, List<Card>>>(hittingSteps.entrySet());
		Entry<Carddeck, List<Card>> entry = indexedList.get(index);
		return entry.getKey();
	}

}

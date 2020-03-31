package hu.ak_akademia.blackjack.statistic;

import java.util.LinkedHashMap;
import java.util.Map;

import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.gamer.Gamer;

public class GeneralStatistic {
	Map<Gamer, Carddeck> statistic;

	public GeneralStatistic() {
		statistic = new LinkedHashMap<>();
	}

	public Map<Gamer, Carddeck> getStatistic() {
		return statistic;
	}

	public void setStatistic(Map<Gamer, Carddeck> statistic) {
		this.statistic = statistic;
	}

	public void add(Gamer gamer, Carddeck carddeck) {
		statistic.put(gamer, carddeck);
	}

}

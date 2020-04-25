package hu.ak_akademia.blackjack.statistic;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.gamer.Gamer;

public class GeneralStatistic {
	private Map<Gamer, Carddeck> statistic;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Carddeck getCarddeck(Gamer gamer) {
		return statistic.get(gamer);
	}

}

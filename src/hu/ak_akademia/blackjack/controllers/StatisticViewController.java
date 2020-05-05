package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;
import hu.ak_akademia.blackjack.statistic.RetrospectiveGamerStatistic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatisticViewController {
	private ArrayList<Player> players;
	private Diller diller;
	private int countOfGameRound;
	private GeneralStatistic generalStatistic;

	public StatisticViewController(ArrayList<Player> players, Diller diller, int countOfGameRound, GeneralStatistic generalStatistic) {
		this.players = players;
		this.diller = diller;
		this.countOfGameRound = countOfGameRound;
		this.generalStatistic = generalStatistic;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane statisticMainPane;

	@FXML
	private Button exitButton;

	@FXML
	private TabPane tabsPane;

	@FXML
	void exitWindow() {
		
		System.out.println("StatisticViewController exitWindow");
		System.out.println(players);
		System.out.println(players.get(0).getCardsInHand());
		System.out.println(diller);
		System.out.println(diller.getCardsInHand());
		
		Stage stage = (Stage) statisticMainPane.getScene()
				.getWindow();
		stage.close();
	}

	@FXML
	void initialize() throws IOException {
		tabsPane.getTabs()
				.clear();
		Tab generalTab = getGeneralTab("../views/StatisticPaneGeneralView.fxml");
		tabsPane.getTabs()
				.addAll(generalTab);
		
		
		for (ListIterator<Player> iterator = players.listIterator(); iterator.hasNext();) {
			
			System.out.println("ListIterator<Player> iterator kezdet");
			System.out.println(players);
			System.out.println(players.get(0).getCardsInHand());
			System.out.println(diller);
			System.out.println(diller.getCardsInHand());
			
			int counter = iterator.nextIndex();
			Gamer gamer = iterator.next();
			Tab gamerTab = getGamerPane(gamer, counter, "../views/StatisticPaneGamerView.fxml");
			tabsPane.getTabs()
					.addAll(gamerTab);
			
			System.out.println("ListIterator<Player> iterator vége");
			System.out.println(players);
			System.out.println(players.get(0).getCardsInHand());
			System.out.println(diller);
			System.out.println(diller.getCardsInHand());
		}

		Tab dillerTab = new Tab("Osztó");
		Node dillerPane = getGamerPane(diller, "../views/StatisticPaneGamerView.fxml");
		dillerTab.setContent(dillerPane);
		tabsPane.getTabs()
				.add(dillerTab);

		Tab winDrawLostTab = new Tab("Összesített");
		Node winDrawLost = getWinDrawLostPane("../views/StatisticPaneWinDrawLostView.fxml");
		winDrawLostTab.setContent(winDrawLost);
		tabsPane.getTabs()
				.add(winDrawLostTab);
		
		System.out.println("Tab winDrawLostTab = new Tab(Összesített)");
		System.out.println(players);
		System.out.println(players.get(0).getCardsInHand());
		System.out.println(diller);
		System.out.println(diller.getCardsInHand());
	}

	private Node getWinDrawLostPane(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		StatisticPaneWinDrawLostController controller;
		try {
			controller = new StatisticPaneWinDrawLostController();
			loader.setController(controller);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Node node = loader.load();
		return node;
	}

	private Tab getGeneralTab(String path) throws IOException {
		Tab tab = new Tab("Általános");
		Node pane = getGeneralPane(path);
		tab.setContent(pane);
		return tab;
	}

	private Tab getGamerPane(Gamer gamer, int counter, String path) throws IOException {
		String title = (gamer instanceof Diller) ? "Osztó" : (counter + 1) + ". Játékos";
		Tab tab = new Tab(title);
		Node pane = getGamerPane(gamer, path);
		tab.setContent(pane);
		return tab;

	}

	private Node getGamerPane(Gamer gamer, String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		RetrospectiveGamerStatistic gamerStatistic = new RetrospectiveGamerStatistic(gamer.getCardsInHand(), generalStatistic.getCarddeck(gamer));
		StatisticPaneGamerController controller = new StatisticPaneGamerController(gamer, gamerStatistic);
		loader.setController(controller);
		Node pane = loader.load();
		return pane;

	}

	private Node getGeneralPane(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		StatisticPaneGeneralController controller = new StatisticPaneGeneralController(players, diller, countOfGameRound, generalStatistic);
		loader.setController(controller);
		Node pane = loader.load();
		return pane;
	}

}

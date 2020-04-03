package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatisticViewController {

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
		Stage stage = (Stage) statisticMainPane.getScene()
				.getWindow();
		stage.close();
	}

	@FXML
	void initialize() throws IOException {
		Tab generalTab = getGeneralTab("../views/StatisticTabGeneralView.fxml");
		tabsPane.getTabs()
				.addAll(generalTab);
		Tab gamerTab = getGamerTab("../views/StatisticTabGamerView.fxml");
		tabsPane.getTabs()
				.addAll(gamerTab);
		
		Tab newTab=new Tab("HURRA");
		tabsPane.getTabs().add(newTab);
	}

	private Tab getGamerTab(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		StatisticTabGamerController controller = new StatisticTabGamerController();
		loader.setController(controller);
		Tab node = (Tab) loader.load();
		return node;

	}

	private Tab getGeneralTab(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		StatisticTabGeneralController controller = new StatisticTabGeneralController();
		loader.setController(controller);
		Tab node = (Tab) loader.load();
		return node;
	}
}

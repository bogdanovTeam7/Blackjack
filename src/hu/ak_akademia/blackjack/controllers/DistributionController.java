package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.distribution.Distributor;
import hu.ak_akademia.blackjack.distribution.GamersDataBase;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DistributionController implements Initializable, ControlledScreen {
	private Distributor distributor;

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane distributionPane;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button selectedNameOkButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private Label questionLabel;

	@FXML
	private Button startGameButton;

	@FXML
	private ComboBox<String> namesComboBox;

	@FXML
	private HBox allGamersHBox;

	@FXML
	void changeToNextView(ActionEvent event) {

	}

	@Override
	public void setScreenParent() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startGameButton.setVisible(true);

		ArrayList<Gamer> gamers = new GamersDataBase().getGamers();
		addGamersToHBox(gamers);

		questionLabel.setText("Ki lesz az osztó?");
		addGamersToComboBox(gamers);
		namesComboBox.setStyle("-fx-font-family:\"" + Constants.getFont() + "\"; -fx-font-size:24");

		Fade fade = new Fade(distributionPane);
		fade.in();
	}

	private void addGamersToComboBox(ArrayList<Gamer> gamers) {
		ObservableList<String> gamersNames = FXCollections.observableArrayList();
		namesComboBox.getItems()
				.clear();
		for (ListIterator<Gamer> iterator = gamers.listIterator(); iterator.hasNext();) {
			Gamer currentGamer = iterator.next();
			if (currentGamer.getState() == State.APPLICANT) {
				gamersNames.add(currentGamer.getName());
			}
		}
		namesComboBox.setItems(gamersNames);
	}

	private void addGamersToHBox(ArrayList<Gamer> gamers) {
		allGamersHBox.getChildren()
				.clear();
		for (Gamer gamer : gamers) {
			Node node = getNode(gamer);
			allGamersHBox.getChildren()
					.add(node);
		}
	}

	private Node getNode(Gamer gamer) {
		GamerController gc = new GamerController();
		gc.setGamer(gamer);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GamerView.fxml"));
		loader.setController(gc);
		Node node = new Node() {
		};
		try {
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	@FXML
	private void goToNextScreen() {
		Fade fade = new Fade(distributionPane);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					setNextScene();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		fade.out(event);
	}

	private void setNextScene() throws IOException {
		InitialDealController controller = new InitialDealController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/InitialDealView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) distributionPane.getScene()
				.getWindow();
		stage.setScene(scene);
	}
}

package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.animations.Shake;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.distribution.Distributor;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DistributionController {
	private Distributor distributor;

	public DistributionController(Distributor distributor) {
		this.distributor = distributor;
	}

	@FXML
	private BorderPane distributionPane;

	@FXML
	private Button selectedNameOkButton;

	@FXML
	private Label questionLabel;

	@FXML
	private Button startGameButton;

	@FXML
	private ComboBox<Gamer> namesComboBox;

	@FXML
	private HBox allGamersHBox;

	@FXML
	private TextArea selectedGamersTextArea;

	@FXML
	private AnchorPane gamerChoosingPane;

	@FXML
	void setParticipant(ActionEvent event) throws IOException {

		Gamer selectedGamer = namesComboBox.getSelectionModel()
				.getSelectedItem();
		if (selectedGamer != null) {
			if (distributor.getPartipants()
					.getDiller() == null) {
				distributor.getPartipants()
						.setDiller(new Diller(selectedGamer));
				selectedGamer.setState(State.PARTICIPATOR);
				refreshCurrentScene();
			} else if (getNumberOfApplicants() < 1) {
				gamerChoosingPane.setVisible(false);
				refreshCurrentScene();
			} else {
				distributor.getPartipants()
						.getPlayers()
						.add(new Player(selectedGamer));
				selectedGamer.setState(State.PARTICIPATOR);
				int currentPlayer = distributor.getPartipants()
						.getPlayers()
						.size() + 1;
				selectedGamersTextArea.appendText(currentPlayer + ". játékos - " + selectedGamer.getName());

				refreshCurrentScene();
			}
		} else {
			Shake shake = new Shake(questionLabel);
			shake.playAnim();
		}

	}

	@FXML
	private void refreshCurrentScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/DistributionView.fxml"));
		DistributionController controller = new DistributionController(distributor);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) distributionPane.getScene()
				.getWindow();
		stage.setScene(scene);
		stage.show();
	}

	private int getNumberOfApplicants() {
		int number = 0;
		for (Gamer gamer : distributor.getGamersList()
				.getGamers()) {
			if (gamer.getState() == State.APPLICANT) {
				number++;
			}
		}
		return number;
	}

	@FXML
	void initialize() {

		if (distributor.getPartipants()
				.getDiller() != null
				&& distributor.getPartipants()
						.getPlayers()
						.size() > 0) {
			startGameButton.setVisible(true);

		}

		addGamersToHBox();

		int currentPlayer = distributor.getPartipants()
				.getPlayers()
				.size() + 1;
		questionLabel.setText((distributor.getPartipants()
				.getDiller() == null) ? "Ki lesz az osztó?" : "Ki lesz " + Constants.getEnumerationWithArticleHun(currentPlayer) + " játékos?");

		addGamersToComboBox();
		namesComboBox.setStyle("-fx-font-family:\"" + Constants.getFont() + "\"; -fx-font-size:24");

		selectedGamersTextArea.appendText(getAlreadyChosenGamers());

		if (!isAtleastOneApplicantExist()) {
			gamerChoosingPane.setVisible(false);
		}

		Fade fade = new Fade(distributionPane, 700);
		fade.in();
	}

	private boolean isAtleastOneApplicantExist() {
		for (ListIterator<Gamer> iterator = distributor.getGamersList()
				.getGamers()
				.listIterator(); iterator.hasNext();) {
			if (iterator.next()
					.getState() == State.APPLICANT) {
				return true;
			}
		}
		return false;
	}

	private String getAlreadyChosenGamers() {
		String text = "";
		if (distributor.getPartipants()
				.getDiller() != null) {
			text += "Osztó - " + distributor.getPartipants()
					.getDiller()
					.getName();
			text += "\n";
		}
		int counter = 1;
		for (ListIterator<Player> iterator = distributor.getPartipants()
				.getPlayers()
				.listIterator(); iterator.hasNext();) {
			text += counter++ + ". Játékos - " + iterator.next()
					.getName() + "\n";
		}
		return text;
	}

	private void addGamersToComboBox() {
		ArrayList<Gamer> gamers = distributor.getGamersList()
				.getGamers();
		ObservableList<Gamer> gamersNames = FXCollections.observableArrayList();
		namesComboBox.getItems()
				.clear();
		for (ListIterator<Gamer> iterator = gamers.listIterator(); iterator.hasNext();) {
			Gamer currentGamer = iterator.next();
			if (currentGamer.getState() == State.APPLICANT) {
				gamersNames.add(currentGamer);
			}
		}
		namesComboBox.setItems(gamersNames);
	}

	private void addGamersToHBox() {
		ArrayList<Gamer> gamers = distributor.getGamersList()
				.getGamers();
		allGamersHBox.getChildren()
				.clear();
		for (Gamer gamer : gamers) {

			Node node = getNode(gamer);
			allGamersHBox.getChildren()
					.add(node);
		}
	}

	private Node getNode(Gamer gamer) {
		GamerController gc = new GamerController(gamer);
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
		Fade fade = new Fade(distributionPane, 1000);
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
		BetOrGameOverController controller = new BetOrGameOverController(1, distributor.getPartipants()
				.getDiller(),
				distributor.getPartipants()
						.getPlayers());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BetOrGameOverView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) distributionPane.getScene()
				.getWindow();
		stage.setScene(scene);
	}
}

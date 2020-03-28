package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.distribution.Distributor;
import hu.ak_akademia.blackjack.distribution.GamersDataBase;
import hu.ak_akademia.blackjack.gamer.Gamer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DistributionController implements Initializable, ControlledScreen {
	private Distributor distributor;

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
	private ComboBox<?> namesComboBox;

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
		questionLabel.setText("Ki lesz az osztó?");
		startGameButton.setVisible(false);

		GamersDataBase gdb = new GamersDataBase();
		ArrayList<Gamer> gamers = gdb.getGamers();
		for (Gamer gamer : gamers) {

			Node node = getNode(gamer);
			allGamersHBox.getChildren()
					.add(node);
		}
		Fade fade = new Fade(distributionPane);
		fade.in();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return node;
	}

	@FXML
	private void goToNextScreen() {
	}

}

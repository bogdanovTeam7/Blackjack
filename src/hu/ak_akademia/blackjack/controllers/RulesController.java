package hu.ak_akademia.blackjack.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import hu.ak_akademia.blackjack.animations.Fade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RulesController implements Initializable, ControlledScreen {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane rulePane;

	@FXML
	private Button startGameButton;

	@FXML
	private TextArea rulesListView;

	@Override
	public void setScreenParent() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String rulesInText = getRules();
		rulesListView.setText(rulesInText);
		Fade fade = new Fade(rulePane);
		fade.in();
	}

	@FXML
	private void goToNextScreen() {
		Fade fade = new Fade(rulePane);
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/DistributionView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) rulePane.getScene()
				.getWindow();
		stage.setScene(scene);
	}

	private String getRules() {
		String rulesInText = "";
		try {
			FileReader file = new FileReader("res/rulesHun.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				rulesInText += scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rulesInText;
	}

}

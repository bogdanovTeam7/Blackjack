package hu.ak_akademia.blackjack.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.distribution.Distributor;
import hu.ak_akademia.blackjack.distribution.GamersDataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RulesController {

	@FXML
	private BorderPane rulePane;

	@FXML
	private Button startGameButton;

	@FXML
	private TextArea rulesListView;

	@FXML
	void initialize() {
		String rulesInText = getRules();
		rulesListView.setText(rulesInText);
		Fade fade = new Fade(rulePane, 1000);
		fade.in();
	}

	@FXML
	private void goToNextScreen() {
		Fade fade = new Fade(rulePane, 1000);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					setNextScene();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void setNextScene() throws IOException {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/DistributionView.fxml"));
				Distributor distributor = new Distributor(new GamersDataBase());
				DistributionController controller = new DistributionController(distributor);
				loader.setController(controller);
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) rulePane.getScene()
						.getWindow();
				stage.setScene(scene);
			}
		};
		fade.out(event);
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

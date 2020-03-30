package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.animations.Shake;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class SalutationController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane salutationPane;

	@FXML
	private ImageView goToNextScreen;

	@FXML
	private Label informationLabel;

	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Shake shake = new Shake(informationLabel);
			shake.playAnim();
	}

	@FXML
	private void goToNextScreen() {
		Fade fade=new Fade(salutationPane);
		EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
			
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
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../views/RulesView.fxml"));
		Parent root=loader.load();
		Scene scene=new Scene(root);
		Stage stage = (Stage)salutationPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

}

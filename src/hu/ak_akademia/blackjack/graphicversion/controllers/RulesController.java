package hu.ak_akademia.blackjack.graphicversion.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class RulesController {

	@FXML
	private Button game_start;

	@FXML
	private TextArea rules;

	@FXML
	public void initialize() throws FileNotFoundException {
		String rulesText = getText("src/res/rulesHun.txt");
		rules.setText(rulesText);
		game_start.setOnAction(event -> {
			game_start.getScene()
					.getWindow()
					.hide();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/hu/ak_akademia/blackjack/graphicversion/views/distribution.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

	private String getText(String path) throws FileNotFoundException {
		StringBuilder text = new StringBuilder();
		FileReader fileReader = new FileReader(path);
		Scanner scanner = new Scanner(fileReader);
		while (scanner.hasNextLine()) {
			text.append(scanner.nextLine());
			text.append("\n");
		}
		scanner.close();
		System.out.println(text);
		return text.toString();
	}
}

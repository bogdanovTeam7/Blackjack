package hu.ak_akademia.blackjack.modalwindows;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadingWindow {
	private Stage stage;

	public void show(String title) {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 100);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public void close() {
		stage.close();
	}

}

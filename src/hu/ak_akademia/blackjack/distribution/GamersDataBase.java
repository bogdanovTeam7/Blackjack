package hu.ak_akademia.blackjack.distribution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.modalwindows.LoadingWindow;

public class GamersDataBase {

	private ArrayList<Gamer> gamers;

	public GamersDataBase() {

		LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.show("BETŐLTÉS....KIS TÜRELEM...");
		try {
			getGamersDatesFromSQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loadingWindow.close();
	}

	private void getGamersDatesFromSQL() throws SQLException {
		gamers = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Constants.getUrl(), Constants.getUser(), Constants.getPassword())) {
			String sql = "SELECT gamer_id, name, pic_file_name FROM gamer";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("gamer_id");
						String name = resultSet.getString("name");
						String picFileName = resultSet.getString("pic_file_name");
						Gamer gamer = new Gamer(name, picFileName, id);
						gamers.add(gamer);
					}
				}
			}
		}
	}

	public ArrayList<Gamer> getGamers() {
		return gamers;
	}

}

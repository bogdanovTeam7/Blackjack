package hu.ak_akademia.blackjack.distribution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Gamer;

public class GamersDataBase {

	private ArrayList<Gamer> gamers;

	public GamersDataBase() {
//		getGamersDates();
		try {
			getGamersDatesFromSQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

//	private void getGamersDates() {
//		gamers = new ArrayList<>();
//		gamers.add(new Gamer("András", "file:res/András.png"));
//		gamers.add(new Gamer("Katalin", "file:res/Katalin.png"));
//		gamers.add(new Gamer("Dóra", "file:res/Dora.png"));
//		gamers.add(new Gamer("István", "file:res/István.png"));
//		gamers.add(new Gamer("Gergő", "file:res/Gergő.png"));
//		gamers.add(new Gamer("Botond", "file:res/Botond.png"));
//		gamers.add(new Gamer("Bence", "file:res/Bence.png"));
//		gamers.add(new Gamer("Vladimir", "file:res/Vladimir.png"));
//	}

	public ArrayList<Gamer> getGamers() {
		return gamers;
	}

}

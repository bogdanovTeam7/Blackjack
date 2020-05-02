package hu.ak_akademia.blackjack.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.ak_akademia.blackjack.constants.Constants;

public class SQLHandler {
	private String url = Constants.getUrl();
	private String user = Constants.getUser();
	private String password = Constants.getPassword();

	private void increaseResult(int id, String field) throws SQLException {
		int oldValue = getResult(id, field);
		updateNumber(id, field, oldValue + 1);
	}

	private void updateNumber(int id, String field, int newValue) throws SQLException {
		System.out.println(id + " id");
		System.out.println(newValue + " newValue");
		System.out.println(field);

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "UPDATE gamer SET ? = ? WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, field);
				preparedStatement.setInt(2, newValue);
				preparedStatement.setInt(3, id);
				preparedStatement.executeUpdate();
			}
		}
	}

	private int getResult(int id, String field) throws SQLException {
		int result = 0;
		System.out.println(id + " id");
		System.out.println(field);
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT ? FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, field);
				preparedStatement.setInt(2, id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						result = resultSet.getInt(field);
						System.out.println(result + " result");
					}
				}
			}
		}
		return result;
	}

	public void increaseLostGames(int id) throws SQLException {
		increaseResult(id, "lost_games");
	}

	public void increaseWinGames(int id) throws SQLException {
		increaseResult(id, "win_games");
	}

	public void increaseDrawGames(int id) throws SQLException {
		increaseResult(id, "draw_games");
	}

}

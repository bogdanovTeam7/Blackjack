package hu.ak_akademia.blackjack.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "UPDATE gamer SET " + field + " = ? WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, newValue);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}
		}
	}

	private int getResult(int id, String field) throws SQLException {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT " + field + " FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						result = resultSet.getInt(field);
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

	public Set<Number> getPrimaryKeys() throws SQLException {
		Set<Number> pk = new HashSet<>();
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT gamer_id FROM gamer";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						pk.add(resultSet.getInt("gamer_id"));
					}
				}
			}
		}
		return pk;
	}

	public String getStringValue(int primaryKey, String field) throws SQLException {
		String value = "";
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT " + field + " FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, primaryKey);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						value = resultSet.getString(field);
					}
				}
			}
		}
		return value;
	}

	public int getIntValue(int primaryKey, String field) throws SQLException {
		int value = 0;
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT " + field + " FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, primaryKey);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						value = resultSet.getInt(field);
					}
				}
			}
		}
		return value;
	}

	public int getSum(int primaryKey) throws SQLException {
		int sum = 0;
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT win_games + draw_games + lost_games AS sum FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, primaryKey);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						sum = resultSet.getInt("sum");
					}
				}
			}
		}
		return sum;
	}

	public double getPercent(int primaryKey, String field) throws SQLException {
		double percent = 0.0;
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT CASE WHEN win_games+draw_games+lost_games = 0 THEN 0 ELSE 100*" + field + "/(win_games+draw_games+lost_games) END percent FROM gamer WHERE gamer_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, primaryKey);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						percent = resultSet.getDouble("percent");
					}
				}
			}
		}
		return percent;
	}

}

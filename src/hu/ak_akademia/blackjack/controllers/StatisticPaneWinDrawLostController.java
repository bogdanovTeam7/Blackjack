package hu.ak_akademia.blackjack.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Set;

import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.sql.SQLHandler;

public class StatisticPaneWinDrawLostController {

	private SQLHandler sqlHandler;
	private Set<Number> gamersIDs;
	private DecimalFormat format = new DecimalFormat("#.#");
	private Font font = Font.font(Constants.getFont(), FontWeight.BOLD, 16);

	public StatisticPaneWinDrawLostController() throws SQLException {
		sqlHandler = new SQLHandler();
		gamersIDs = sqlHandler.getPrimaryKeys();
	}

	@FXML
	private GridPane winDrawLostGridPane;

	@FXML
	void initialize() throws SQLException {
		int column = 1;

		for (Number number : gamersIDs) {
			int row = 0;
			int primaryKey = number.intValue();
			setGamerName(primaryKey, column, row++);
			setPlayedGamesNumber(primaryKey, column, row++);
			setWinDrawLostGamesNumber(primaryKey, column, row++, "win_games");
			setWinDrawLostGamesPercent(primaryKey, column, row++, "win_games");
			setWinDrawLostGamesNumber(primaryKey, column, row++, "draw_games");
			setWinDrawLostGamesPercent(primaryKey, column, row++, "draw_games");
			setWinDrawLostGamesNumber(primaryKey, column, row++, "lost_games");
			setWinDrawLostGamesPercent(primaryKey, column, row++, "lost_games");
			column++;
		}
	}

	private void setWinDrawLostGamesPercent(int primaryKey, int column, int row, String field) throws SQLException {
		Text text = new Text(format.format(sqlHandler.getPercent(primaryKey, field)));
		text.setFont(font);
		text = setFill(text, field);
		winDrawLostGridPane.add(text, column, row);
	}

	private void setWinDrawLostGamesNumber(int primaryKey, int column, int row, String field) throws SQLException {
		Text text = new Text(sqlHandler.getIntValue(primaryKey, field) + "");
		text.setFont(font);
		text = setFill(text, field);
		winDrawLostGridPane.add(text, column, row);
	}

	private Text setFill(Text text, String field) {
		if (field.equals("win_games")) {
			text.setFill(Color.CHARTREUSE);
			return text;
		} else if (field.equals("draw_games")) {
			text.setFill(Color.CRIMSON);
			return text;
		} else if (field.equals("lost_games")) {
			text.setFill(Color.LIGHTSLATEGREY);
		}
		return text;
	}

	private void setPlayedGamesNumber(int primaryKey, int column, int row) throws SQLException {
		Text text = new Text(sqlHandler.getSum(primaryKey) + "");
		text.setFont(font);
		winDrawLostGridPane.add(text, column, row);
	}

	private void setGamerName(int primaryKey, int colomn, int row) throws SQLException {
		Text text = new Text(sqlHandler.getStringValue(primaryKey, "name"));
		text.setFont(font);
		winDrawLostGridPane.add(text, colomn, row);
	}

}

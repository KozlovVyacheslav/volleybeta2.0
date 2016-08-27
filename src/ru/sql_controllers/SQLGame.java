package ru.sql_controllers;

import javafx.collections.ObservableList;
import ru.abstracts.SQLController;
import ru.objects.Game;
import ru.objects.Game2_0;

import java.sql.SQLException;

/**
 * Created by secret on 25.08.2016.
 */
public class SQLGame extends SQLController {

    public void select(ObservableList<Game> games, int id_cup) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT g.id, g.date_game, c.id as id_command, c.name_command, c.sex, gc.set_1, gc.set_2,gc.set_3,gc.set_4, gc.set_5\n" +
                "from game g INNER JOIN game_command gc ON g.id = gc.id_game INNER JOIN command c ON c.id = gc.id_command \n" +
                "WHERE g.ID_CUP = '"+id_cup+"'");

        while (resultSet.next()) {
            games.add(new Game(resultSet.getInt("id"), resultSet.getDate("DATE_GAME"), resultSet.getInt("ID_COMMAND"),
                    resultSet.getString("NAME_COMMAND"), resultSet.getBoolean("SEX"), resultSet.getInt("SET_1"),
                    resultSet.getInt("SET_2"), resultSet.getInt("SET_3"), resultSet.getInt("SET_4"), resultSet.getInt("SET_5"), id_cup));
            resultSet.next();
            games.get(games.size() - 1).setId_command2(resultSet.getInt("ID_COMMAND"));
            games.get(games.size() - 1).setName_command2(resultSet.getString("NAME_COMMAND"));
            games.get(games.size() - 1).setSet12(resultSet.getInt("SET_1"));
            games.get(games.size() - 1).setSet22(resultSet.getInt("SET_2"));
            games.get(games.size() - 1).setSet32(resultSet.getInt("SET_3"));
            games.get(games.size() - 1).setSet42(resultSet.getInt("SET_4"));
            games.get(games.size() - 1).setSet52(resultSet.getInt("SET_5"));
            games.get(games.size() - 1).toStringScore();

        }
    }

    public void select2 (ObservableList<Game2_0> games, int id_cup) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT g.id, g.date_game, c.id as id_command, c.name_command, c.sex, gc.set_1, gc.set_2,gc.set_3,gc.set_4, gc.set_5\n" +
                "from game g INNER JOIN game_command gc ON g.id = gc.id_game INNER JOIN command c ON c.id = gc.id_command \n" +
                "WHERE g.ID_CUP = '"+id_cup+"'");

        while (resultSet.next()) {
            games.add(new Game2_0(resultSet.getInt("id"), resultSet.getDate("DATE_GAME"), resultSet.getInt("ID_COMMAND"),
                    resultSet.getString("NAME_COMMAND"), resultSet.getBoolean("SEX"), id_cup));
            for (int i = 0; i < 5; i++) {
                games.get(games.size() - 1).setIScore(i, resultSet.getInt(i + 6));
            }
            resultSet.next();
            games.get(games.size() - 1).setId_command2(resultSet.getInt("ID_COMMAND"));
            games.get(games.size() - 1).setName_command2(resultSet.getString("NAME_COMMAND"));
            for (int i = 0; i < 5; i++) {
                games.get(games.size() - 1).setIScore2(i, resultSet.getInt(i + 6));
            }
            games.get(games.size() - 1).scoreToString();
        }
    }


}

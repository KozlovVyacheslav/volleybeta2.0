package ru.sql_controllers;

import ru.abstracts.SQLController;
import ru.objects.Command;
import ru.objects.Player;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyacheslav_kozlov on 03.08.2016.
 */
public class SQLPlayer extends SQLController {

    public void select(List<Player> players) throws SQLException{
        this.statement = connection.createStatement();
        resultSet = statement.executeQuery("select * \n" +
                "from player");
        while (resultSet.next()) {
            players.add(new Player(resultSet.getInt("id"), resultSet.getString("surname"), resultSet.getString("name_player"),
                    resultSet.getString("patronymic"), resultSet.getInt("number"), resultSet.getInt("id_command")));
        }
    }

    public void add(Player player) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("insert into player(NAME_PLAYER, SURNAME, PATRONYMIC, NUMBER, ID_COMMAND) " +
                "values('"+player.getName()+"', '"+player.getSurname()+"', " +
                "'"+player.getPatronymic()+"', '"+player.getNumber()+"', '"+player.getId_command()+"');");
    }

    public void update(Player player) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("update player set NAME_PLAYER = '"+player.getName()+"', SURNAME = '"+player.getSurname()+"', " +
                "PATRONYMIC = '"+player.getPatronymic()+"', NUMBER = '"+player.getNumber()+"' where id = '"+player.getId()+"'");
    }

    public void delete(Player player) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("DELETE from PLAYER where ID = '"+player.getId()+"'");
    }
}

package ru.sql_controllers;

import javafx.collections.ObservableList;
import ru.abstracts.SQLController;
import ru.objects.Cup;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyacheslav_kozlov on 22.08.2016.
 */
public class SQLCup extends SQLController {

    public void select(List<Cup> cups) throws SQLException {
        this.statement = connection.createStatement();
        resultSet = statement.executeQuery("select * \n" +
                "from cup");
        while (resultSet.next()) {
            cups.add(new Cup(resultSet.getInt("id"), resultSet.getString("name_cup"),
                    resultSet.getString("sponsor"), resultSet.getDate("start_cup"), resultSet.getDate("end_cup")));
        }
    }

    public void add(Cup cup) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("insert into cup(NAME_CUP, SPONSOR, START_CUP, END_CUP) " +
                "values('"+cup.getName()+"', '"+cup.getSponsor()+"', " +
                "'"+cup.getDateStart()+"', '"+cup.getDateEnd()+"')");
    }

    public void update(Cup cup) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate("update cup set NAME_CUP = '"+cup.getName()+"', SPONSOR = '"+cup.getSponsor()+"', " +
                "START_CUP = '"+cup.getDateStart()+"', END_CUP = '"+cup.getDateEnd()+"' WHERE ID = '"+cup.getId()+"'");
    }

    public void delete(Cup cup) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("delete from cup where id = '"+cup.getId()+"'");
    }

    public void initIdCommand(ObservableList<Integer> id_commands ,int id_cup) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select ID_COMMAND from CUP_COMMAND WHERE ID_CUP = '"+id_cup+"';");

        while (resultSet.next()) id_commands.add(resultSet.getInt(1));
    }

    public int lastCup() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select max(id) from cup");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public void addCupCommand(int id_cup, ObservableList<Integer> id_commands)  throws SQLException{
        statement = connection.createStatement();
        for (int i = 0; i < id_commands.size(); i++) {
            System.out.println("addCupCommand = " + i);
            statement.executeUpdate("insert into cup_command(ID_CUP, ID_COMMAND) VALUES ('" + id_cup + "', '" + id_commands.get(i) +"')");
        }
    }

    public void deleteCupCommand(int id_cup, ObservableList<Integer> id_commands)  throws SQLException{
        statement = connection.createStatement();
        for (int i = 0; i < id_commands.size(); i++) {
            System.out.println("addCupCommand = " + i);
            statement.executeUpdate("delete from cup_command where ID_CUP = '"+id_cup+"' and ID_COMMAND = '"+id_commands.get(i)+"'");
        }
    }
}

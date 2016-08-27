package ru.sql_controllers;

import ru.abstracts.SQLController;
import ru.objects.Command;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyacheslav_kozlov on 02.08.2016.
 */
public class SQLCommand extends SQLController {

    public void select(List<Command> commands) throws SQLException{
        this.statement = connection.createStatement();
        resultSet = statement.executeQuery("select * \n" +
                "from command");
        while (resultSet.next()) {
            commands.add(new Command(resultSet.getInt("id"), resultSet.getString("name_command"), resultSet.getString("coach"),
                    resultSet.getString("sponsor"), resultSet.getBoolean("sex")));
        }
    }

    public void add(Command command) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("insert into command(name_command, coach, sponsor, sex) values('"+command.getName()+"', '"+command.getCoach()+"', " +
                "'"+command.getSponsor()+"', '"+command.isSex()+"');");
    }

    public void update(Command command) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("update command set name_command = '"+command.getName()+"', coach = '"+command.getCoach()+"', " +
                "sponsor = '"+command.getSponsor()+"' where id = '"+command.getId()+"'");
    }

    public void delete(Command command) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("delete from command where id = '"+command.getId()+"'");
    }

    public int getLastIdCommand() throws SQLException{
        System.out.println("getLastIdCommand");
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select max(id) from command");
        resultSet.next();
        System.out.println("lastId" + resultSet.getInt(1));
        return resultSet.getInt(1);
    }
}

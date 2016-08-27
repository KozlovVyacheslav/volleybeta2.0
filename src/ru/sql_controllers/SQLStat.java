package ru.sql_controllers;

import javafx.collections.ObservableList;
import ru.abstracts.SQLController;
import ru.objects.Stat;

import java.sql.SQLException;

/**
 * Created by secret on 26.08.2016.
 */
public class SQLStat extends SQLController{

    public void select(ObservableList<Stat> stats) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select s.id_game, s.id_player, p.id_command, p.surname, p.name_player, " +
                "s.COLUMNPOINTTOT, s.columnServeTot, s.columnServeError, s.columnServePoint,\n" +
                "  s.columnReceptionTot, s.columnReceptionError, s.columnReceptionNice, s.columnAttackTot, " +
                "s.columnAttackError, s.columnAttackPoint,\n" +
                "  s.columnBlockPoint\n" +
                "from player p INNER JOIN stat s ON s.id_player = p.id;");
        while (resultSet.next()){
            stats.add(new Stat(resultSet.getInt("id_game"), resultSet.getInt("id_player"), resultSet.getInt("id_command"), resultSet.getString("surname"),
                    resultSet.getString("name_player"), resultSet.getInt("columnpointtot"), resultSet.getInt("columnservetot"),
                    resultSet.getInt("columnserveerror"), resultSet.getInt("columnservepoint"), resultSet.getInt("columnreceptiontot"),
                    resultSet.getInt("columnreceptionerror"), resultSet.getInt("columnreceptionnice"), resultSet.getInt("columnattacktot"),
                    resultSet.getInt("columnattackerror"), resultSet.getInt("columnattackpoint"), resultSet.getInt("columnblockpoint")));
        }
        stats.stream().forEach(System.out :: println);
    }

    public void update(ObservableList<Stat> stats, int id_game) throws SQLException{
        statement = connection.createStatement();
        for (Stat stat : stats) {
            statement.executeUpdate("update stat set COLUMNPOINTTOT = '"+stat.getPoints()+"', COLUMNSERVETOT = '"+stat.getTot_serve()+"', " +
                    "COLUMNSERVEERROR = '"+stat.getError_serve()+"', COLUMNSERVEPOINT = '"+stat.getPoints_serve()+"', " +
                    "COLUMNRECEPTIONTOT = '"+stat.getTot_reception()+"', COLUMNRECEPTIONERROR = '"+stat.getError_reception()+"', " +
                    "COLUMNRECEPTIONNICE = '"+stat.getNice_reception()+"', COLUMNATTACKTOT = '"+stat.getTot_attack()+"', " +
                    "COLUMNATTACKERROR = '"+stat.getError_attack()+"', COLUMNATTACKPOINT = '"+stat.getPoints_attack()+"', " +
                    "COLUMNBLOCKPOINT = '"+stat.getBlock()+"' " +
                    "WHERE ID_GAME = '"+id_game+"' and ID_PLAYER = '"+stat.getId_player()+"'");
        }
    }
}

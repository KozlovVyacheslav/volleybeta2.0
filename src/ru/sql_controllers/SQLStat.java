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
        resultSet = statement.executeQuery("select s.id_game, s.id_player, p.surname, p.name_player, s.COLUMNPOINTTOT, s.columnServeTot, s.columnServeError, s.columnServePoint,\n" +
                "  s.columnReceptionTot, s.columnReceptionError, s.columnReceptionNice, s.columnAttackTot, s.columnAttackError, s.columnAttackPoint,\n" +
                "  s.columnBlockPoint\n" +
                "from player p INNER JOIN stat s ON s.id_player = p.id;");
    }
}

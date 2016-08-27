package ru.objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.sql_controllers.SQLCup;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Cup {
    private int id;
    private String name;
    private String sponsor;
    private Date dateStart;
    private Date dateEnd;
    private ObservableList<Integer> id_commands = FXCollections.observableArrayList();

    public Cup() {
        id = 0;
    }

    public Cup(String name, String sponsor, Date dateStart, Date dateEnd) {
        this.name = name;
        this.sponsor = sponsor;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Cup(int id, String name, String sponsor, Date dateStart, Date dateEnd) {
        this.id = id;
        this.name = name;
        this.sponsor = sponsor;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        try {
            initIdComands();
            toStringIdCommands();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ObservableList<Integer> getId_commands() {
        return id_commands;
    }

    public void setId_commands(ObservableList<Integer> id_commands) {
        this.id_commands = id_commands;
    }

    public void initIdComands() throws SQLException{
        SQLCup sqlCup = new SQLCup();
        sqlCup.Connection();
        sqlCup.initIdCommand(id_commands, id);
    }

    public void addIdCommand(int id_command) {
        id_commands.add(id_command);
    }

    public void removeIdCommand(int id_command) {
        id_commands.remove(java.lang.Integer.valueOf(id_command));
    }

    @Override
    public String toString() {
        return "Cup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }

    public void toStringIdCommands() {
        id_commands.stream().forEach(i -> System.out.println("id_command = " + i));
    }
}

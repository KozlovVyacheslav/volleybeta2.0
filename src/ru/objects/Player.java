package ru.objects;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Player {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int number;
    private int id_command;

    public Player(String surname, String name, String patronymic, int number, int id_command) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.number = number;
        this.id_command = id_command;
    }

    public Player(int id, String surname, String name, String patronymic, int number, int id_command) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.number = number;
        this.id_command = id_command;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", number=" + number +
                ", id_command=" + id_command +
                '}';
    }
}

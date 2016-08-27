package ru.objects;

/**
 * Created by secret on 25.08.2016.
 */
public class Stat {
    private int id_game;
    private int id_player;
    private int id_command;
    private String surname_player;
    private String name_player;

    private int points;
    private int tot_serve;
    private int error_serve;
    private int points_serve;
    private int tot_reception;
    private int error_reception;
    private int nice_reception;
    private int tot_attack;
    private int error_attack;
    private int points_attack;
    private int points_persent_attack;
    private int block;

    public Stat() {
    }

    public Stat(int id_player, int id_command, String surname_player, String name_player, int points,
                int tot_serve, int error_serve, int points_serve,
                int tot_reception, int error_reception, int nice_reception,
                int tot_attack, int error_attack, int points_attack, int block) {
        this.id_player = id_player;
        this.id_command = id_command;
        this.surname_player = surname_player;
        this.name_player = name_player;
        this.points = points;
        this.tot_serve = tot_serve;
        this.error_serve = error_serve;
        this.points_serve = points_serve;
        this.tot_reception = tot_reception;
        this.error_reception = error_reception;
        this.nice_reception = nice_reception;
        this.tot_attack = tot_attack;
        this.error_attack = error_attack;
        this.points_attack = points_attack;
        this.block = block;
    }

    public Stat(int id_game, int id_player, int id_command, String surname_player, String name_player,
                int points, int tot_serve, int error_serve, int points_serve,
                int tot_reception, int error_reception, int nice_reception,
                int tot_attack, int error_attack, int points_attack, int block) {
        this.id_game = id_game;
        this.id_player = id_player;
        this.id_command = id_command;
        this.surname_player = surname_player;
        this.name_player = name_player;
        this.points = points;
        this.tot_serve = tot_serve;
        this.error_serve = error_serve;
        this.points_serve = points_serve;
        this.tot_reception = tot_reception;
        this.error_reception = error_reception;
        this.nice_reception = nice_reception;
        this.tot_attack = tot_attack;
        this.error_attack = error_attack;
        this.points_attack = points_attack;
        this.block = block;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public String getSurname_player() {
        return surname_player;
    }

    public void setSurname_player(String surname_player) {
        this.surname_player = surname_player;
    }

    public String getName_player() {
        return name_player;
    }

    public void setName_player(String name_player) {
        this.name_player = name_player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTot_serve() {
        return tot_serve;
    }

    public void setTot_serve(int tot_serve) {
        this.tot_serve = tot_serve;
    }

    public int getError_serve() {
        return error_serve;
    }

    public void setError_serve(int error_serve) {
        this.error_serve = error_serve;
    }

    public int getPoints_serve() {
        return points_serve;
    }

    public void setPoints_serve(int points_serve) {
        this.points_serve = points_serve;
    }

    public int getTot_reception() {
        return tot_reception;
    }

    public void setTot_reception(int tot_reception) {
        this.tot_reception = tot_reception;
    }

    public int getError_reception() {
        return error_reception;
    }

    public void setError_reception(int error_reception) {
        this.error_reception = error_reception;
    }

    public int getNice_reception() {
        return nice_reception;
    }

    public void setNice_reception(int nice_reception) {
        this.nice_reception = nice_reception;
    }

    public int getTot_attack() {
        return tot_attack;
    }

    public void setTot_attack(int tot_attack) {
        this.tot_attack = tot_attack;
    }

    public int getError_attack() {
        return error_attack;
    }

    public void setError_attack(int error_attack) {
        this.error_attack = error_attack;
    }

    public int getPoints_attack() {
        return points_attack;
    }

    public void setPoints_attack(int points_attack) {
        this.points_attack = points_attack;
    }

    public int getPoints_persent_attack() {
        return points_persent_attack;
    }

    public void setPoints_persent_attack(int points_persent_attack) {
        this.points_persent_attack = points_persent_attack;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "id_game=" + id_game +
                ", id_player=" + id_player +
                ", surname_player='" + surname_player + '\'' +
                ", name_player='" + name_player + '\'' +
                ", points=" + points +
                ", tot_serve=" + tot_serve +
                ", error_serve=" + error_serve +
                ", points_serve=" + points_serve +
                ", tot_reception=" + tot_reception +
                ", error_reception=" + error_reception +
                ", nice_reception=" + nice_reception +
                ", tot_attack=" + tot_attack +
                ", error_attack=" + error_attack +
                ", points_attack=" + points_attack +
                ", points_persent_attack=" + points_persent_attack +
                ", block=" + block +
                '}';
    }
}

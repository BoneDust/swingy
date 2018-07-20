package za.co.wethinkcode.swingy.controllers;

import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.models.playables.Villain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


//http://www.benchresources.net/jdbc-getting-single-record-using-jdbc-preparedstatement-interface/
public class DBController
{
    private static Connection getConnection() throws Exception
    {
        Connection conn;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3308", "root", "password");
        return (conn);
    }

    public static void createDB() throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        String createDB = "create database if not exists swingy";
        statement.executeUpdate(createDB);//statement.executeQuery();
        statement.close();
        connection.close();
    }


    public static void initDB() throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        String createArtefactTable = "CREATE TABLE if not exists `swingy`.`artefact`(" +
                "`id` INT NOT NULL , " +
                "`playerId` INT NOT NULL," +
                "`name` TEXT NOT NULL , " +
                "`type` TEXT NOT NULL , " +
                "`value` INT NOT NULL , " +
                "PRIMARY KEY (`id`))" +
                "FOREIGN KEY (`playerId`) REFERENCES  REFERENCES swingy.player (`id`)  " +
                "ENGINE = InnoDB;";

        String createPlayerTable = "CREATE TABLE if not exists `swingy`.`player` (" +
                "`id` INT NOT NULL , " +
                "`name` TEXT NOT NULL , " +
                "`type` TEXT NOT NULL , " +
                "`level` INT NOT NULL , " +
                "`exp` INT NOT NULL , " +
                "`atk` INT NOT NULL , " +
                "`def` INT NOT NULL , " +
                "`hp` INT NOT NULL , " +
                "`x_coord` INT NOT NULL , " +
                "`y_coord` INT NOT NULL , " +
                "`phrase` TEXT NULL , " +
                "PRIMARY KEY (`id`)) " +
                "FOREIGN KEY (`playerId`) REFERENCES  REFERENCES swingy.player (`id`)  " +
                "ENGINE = InnoDB;";

        String createMapTable = "CREATE TABLE if not exists `swingy`.`map` (" +
                "`id` INT NOT NULL , " +
                "`playerId` INT NOT NULL," +
                "`size` INT NOT NULL , " +
                "PRIMARY KEY (`id`))" +
                " ENGINE = InnoDB;";

        statement.executeUpdate(createPlayerTable);
        statement.executeUpdate(createArtefactTable);
        statement.executeUpdate(createMapTable);
        statement.close();
        connection.close();
    }

    public static ArrayList<Villain> getVillains() throws Exception
    {

        return (null);
    }

    public static Player getPlayer() throws Exception
    {
        return (null);
    }

    public static ArrayList<Player> getPlayers() throws Exception
    {
        return (null);
    }

    public static ArrayList<Artefact> getArtefacts(int id) throws Exception
    {
        return (null);
    }

    public static void recordPlayer(Player player) throws Exception
    {

    }

    public static void recordVillains(ArrayList<Villain> villains) throws Exception
    {

    }

    public static void recordArtefacts(ArrayList<Artefact> artefacts) throws Exception
    {

    }
}

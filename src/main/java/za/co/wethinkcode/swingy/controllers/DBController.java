package za.co.wethinkcode.swingy.controllers;

import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.*;
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
        String createArtefactTable =
                "CREATE TABLE if not exists `swingy`.`artefacts`(" +
                "`id` INT NOT NULL , " +
                "`playerId` INT NOT NULL," +
                "`name` TEXT NOT NULL , " +
                "`type` TEXT NOT NULL , " +
                "`value` INT NOT NULL , " +
                "PRIMARY KEY (`id`)," +
                "FOREIGN KEY (`playerId`) REFERENCES swingy.heroes (`id`));";

        String createHeroTable =
                "CREATE TABLE if not exists `swingy`.`heroes` (" +
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
                "PRIMARY KEY (`id`));";

        String createVillainTable =
                "CREATE TABLE if not exists `swingy`.`villains` (" +
                "`id` INT NOT NULL , " +
                "`playerId` INT NOT NULL , " +
                "`name` TEXT NOT NULL , " +
                "`type` TEXT NOT NULL , " +
                "`level` INT NOT NULL , " +
                "`exp` INT NOT NULL , " +
                "`atk` INT NOT NULL , " +
                "`def` INT NOT NULL , " +
                "`hp` INT NOT NULL , " +
                "`x_coord` INT NOT NULL , " +
                "`y_coord` INT NOT NULL , " +
                "`phrase` TEXT NOT NULL , "+
                "PRIMARY KEY (`id`), " +
                "FOREIGN KEY (`playerId`) REFERENCES swingy.heroes (`id`));";

        String createMapTable =
                "CREATE TABLE if not exists `swingy`.`maps` (" +
                "`id` INT NOT NULL , " +
                "`playerId` INT NOT NULL," +
                "`size` INT NOT NULL , " +
                "PRIMARY KEY (`id`)," +
                "FOREIGN KEY (`playerId`) REFERENCES swingy.heroes (`id`)) ;"
                //"ENGINE = InnoDB;"
                ;

        statement.executeUpdate(createHeroTable );
        statement.executeUpdate(createVillainTable );
        statement.executeUpdate(createArtefactTable);
        statement.executeUpdate(createMapTable);
        statement.close();
        connection.close();
    }

    public static ArrayList<Villain> getVillains(int playerId) throws Exception
    {
        ArrayList<Villain> villains = new ArrayList<>();
        return (villains);
    }

    public static Player getPlayer() throws Exception
    {
        Player player;
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

    public static Map getMap(int id) throws Exception
    {
        return (null)
    }

    public static void recordHero(Player player) throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        String type;
        if (player instanceof Swordsman)
            type = "Swordsman";
        else if (player instanceof Gunman)
            type = "Gunman";
        else if (player instanceof KungFuMaster)
            type = "KungFuMaster";
        else
            type = "";
        String insertHero = String.format("INSERT INTO `swingy`.`heroes` (`id`, `name`, `type`, `level`, `exp`," +
                            " `atk`, `def`, `hp`, `x_coord`, `y_coord`)" +
                            " VALUES (%d, %s, %s, %d, %d, %d, %d, %d, %d, %d);",
                            player.getId(),
                            player.getName(),
                            type,
                            player.getLevel(),
                            player.getExp(),
                            player.getAtk(),
                            player.getDef(),
                            player.getHp(),
                            player.getCoordinates().getX(),
                            player.getCoordinates().getY());
        statement.executeUpdate(insertHero);
        statement.close();
        connection.close();
    }

    public static void recordVillains(ArrayList<Villain> villains, int playerId) throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        for (Villain villain : villains)
        {
            String insertVillain = String.format("INSERT INTO `swingy`.`villains` (`id`, 'playerId`, `name`, `type`," +
                            " `level`, `exp`, `atk`, `def`, `hp`, `x_coord`, `y_coord`, `phrase`)" +
                            " VALUES (%d, %d, %s, %s, %d, %d, %d, %d, %d, %d, %d, %s);",
                            villain.getId(),
                            playerId,
                            villain.getName(),
                            villain.getType(),
                            villain.getLevel(),
                            villain.getExp(),
                            villain.getAtk(),
                            villain.getDef(),
                            villain.getHp(),
                            villain.getCoordinates().getX(),
                            villain.getCoordinates().getY(),
                            villain.getCatchPhrase());
            statement.executeUpdate(insertVillain);
        }
        statement.close();
        connection.close();
    }

    public static void recordArtefacts(ArrayList<Artefact> artefacts, int playerId) throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        for (Artefact artefact : artefacts)
        {
            String insertArtefact = String.format("INSERT INTO `swingy`.`artefacts` (`id`, `playerId`, `name`, `type`," +
                            " `value`)" +
                            " VALUES (%d, %d, %s, %s, %d);",
                            artefact.getId(),
                            playerId,
                            artefact.getName(),
                            artefact.getType(),
                            artefact.getValue());
            statement.executeUpdate(insertArtefact);
        }
        statement.close();
        connection.close();
    }

    public static void recordMap(Map map, int playerId) throws Exception
    {
        Connection  connection = getConnection();
        Statement statement = connection.createStatement();
        String insertMap = String.format("INSERT INTO `swingy`.`maps` (`id`, `playerId`, `size`)" +
                            " VALUES (%d, %d, %d);",
                            map.getId(),
                            playerId,
                             map.getSize());
        statement.executeUpdate(insertMap);
        statement.close();
        connection.close();
    }
}

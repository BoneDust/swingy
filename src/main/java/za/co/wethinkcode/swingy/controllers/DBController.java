package za.co.wethinkcode.swingy.controllers;

import za.co.wethinkcode.swingy.factories.ArtefactFactory;
import za.co.wethinkcode.swingy.factories.MapFactory;
import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.*;

import java.sql.*;
import java.util.ArrayList;

//http://www.benchresources.net/jdbc-getting-single-record-using-jdbc-preparedstatement-interface/
public class DBController
{
    private static void closeDBConnection(Connection connection, Statement statement)
    {
        try
        {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private static Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308", "root", "password");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return (conn);
    }

    public static void createDB()
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String createDB = "create database if not exists swingy";
            statement.executeUpdate(createDB);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }


    public static void initDB()
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
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
                            "`phrase` TEXT NOT NULL , " +
                            "PRIMARY KEY (`id`), " +
                            "FOREIGN KEY (`playerId`) REFERENCES swingy.heroes (`id`));";

            String createMapTable =
                    "CREATE TABLE if not exists `swingy`.`maps` (" +
                            "`id` INT NOT NULL , " +
                            "`playerId` INT NOT NULL," +
                            "`size` INT NOT NULL , " +
                            "PRIMARY KEY (`id`)," +
                            "FOREIGN KEY (`playerId`) REFERENCES swingy.heroes (`id`)) ;";

            statement.executeUpdate(createHeroTable);
            statement.executeUpdate(createVillainTable);
            statement.executeUpdate(createArtefactTable);
            statement.executeUpdate(createMapTable);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }

    public static ArrayList<Villain> getVillains(int playerId)
    {
        ArrayList<Villain> villains = new ArrayList<>();
        return (villains);
    }

    public static Player getPlayer(int playerId)
    {
        Player player;
        return (null);
    }

    public static ArrayList<Player> getPlayers()
    {
        int id, value;
        String type, name;
        Connection connection = null;
        Statement statement = null;
        ArrayList<Artefact> artefacts = new ArrayList<>();
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String selectArtefacts = String.format("select `id`, `name`, `type`, `value` from `swingy`.`artefacts` where `playerId` = %d", playerId);
            ResultSet results = statement.executeQuery(selectArtefacts);
            while(results.next())
            {
                id = results.getInt("id");
                value = results.getInt("value");
                name = results.getString("name");
                type = results.getString("type");
                artefacts.add(ArtefactFactory.oldArtefact(id, value, name, type));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
        return (artefacts);
    }

    public static ArrayList<Artefact> getArtefacts(int playerId)
    {
        int id, value;
        String type, name;
        Connection connection = null;
        Statement statement = null;
        ArrayList<Artefact> artefacts = new ArrayList<>();
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String selectArtefacts = String.format("select `id`, `name`, `type`, `value` from `swingy`.`artefacts`" +
                                                    " where `playerId` = %d", playerId);
            ResultSet results = statement.executeQuery(selectArtefacts);
            while(results.next())
            {
                id = results.getInt("id");
                value = results.getInt("value");
                name = results.getString("name");
                type = results.getString("type");
                artefacts.add(ArtefactFactory.oldArtefact(id, value, name, type));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
        return (artefacts);
    }

    public static Map getMap(int playerId)
    {
        int size;
        int mapId;
        Connection connection = null;
        Statement statement = null;
        Map map = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String selectMap = String.format("select `id`, `size` from `swingy`.`maps` where `playerId` = %d", playerId);
            ResultSet results = statement.executeQuery(selectMap);
            while(results.next())
            {
                mapId = results.getInt("id");
                size = results.getInt("size");
                map = MapFactory.oldMap(size, mapId);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
        return (map);
    }

    public static void recordHero(Player player)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
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
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }

    public static void recordVillains(ArrayList<Villain> villains, int playerId)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            for (Villain villain : villains)
            {
                String insertVillain = String.format("INSERT INTO `swingy`.`villains` (`id`, 'playerId`, `name`," +
                                " `type`, `level`, `exp`, `atk`, `def`, `hp`, `x_coord`, `y_coord`, `phrase`)" +
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
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }

    public static void recordArtefacts(ArrayList<Artefact> artefacts, int playerId)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            for (Artefact artefact : artefacts)
            {
                String insertArtefact = String.format("INSERT INTO `swingy`.`artefacts` (`id`, `playerId`, `name`," +
                                " `type`, `value`)" +
                                " VALUES (%d, %d, %s, %s, %d);",
                        artefact.getId(),
                        playerId,
                        artefact.getName(),
                        artefact.getType(),
                        artefact.getValue());
                statement.executeUpdate(insertArtefact);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }

    public static void recordMap(Map map, int playerId)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String insertMap = String.format("INSERT INTO `swingy`.`maps` (`id`, `playerId`, `size`)" +
                            " VALUES (%d, %d, %d);",
                    map.getId(),
                    playerId,
                    map.getSize());
            statement.executeUpdate(insertMap);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
    }
}

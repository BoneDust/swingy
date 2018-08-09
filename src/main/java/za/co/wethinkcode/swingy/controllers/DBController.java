package za.co.wethinkcode.swingy.controllers;

import za.co.wethinkcode.swingy.factories.*;
import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.map.Coordinates;
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
                            "PRIMARY KEY (`id`));";

            statement.executeUpdate(createHeroTable);
            statement.executeUpdate(createArtefactTable);
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

    public static ArrayList<Player> getPlayers()
    {
        int id, level, exp, atk, def, hp;
        String type, name;
        Connection connection = null;
        Statement statement = null;
        ArrayList<Player> players = new ArrayList<>();
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String selectPlayers = "select *" +
                                    "from `swingy`.`heroes`";
            ResultSet results = statement.executeQuery(selectPlayers);
            while(results.next())
            {
                id = results.getInt("id");
                name = results.getString("name");
                type = results.getString("type");
                level = results.getInt("level");
                exp = results.getInt("exp");
                atk = results.getInt("atk");
                def = results.getInt("def");
                hp = results.getInt("hp");
                int size = ((level - 1) * 5) + 10 - (level % 2);
                Coordinates coordinates = CoordinateFactory.newCoordinates(size / 2, size / 2, size);
                Player player = PlayerFactory.customPlayer(id, name, type, level, exp, atk, def, hp, coordinates);
                player.setArtefacts(getArtefacts(player.getId()));
                players.add(player);
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
        return (players);
    }

    private static ArrayList<Artefact> getArtefacts(int playerId)
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
            String selectArtefacts = String.format("select * from `swingy`.`artefacts`" +
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

    public static String updateHeroString(Player player)
    {
        String updateHero = String.format("UPDATE `swingy`.`heroes` SET `level` = %d, `exp` = %d, `atk` = %d," +
                        " `def` = %d, `hp` = %d WHERE `id` = %d;" +,
                player.getLevel(),
                player.getExp(),
                player.getAtk(),
                player.getDef(),
                player.getHp(),
                player.getId()
        );
        return (updateHero);
    }

    public static void recordHero(Player player)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String selectPlayer = String.format("select * from `swingy`.`heroes`" +
                    " where `playerId` = %d", player.getId());
            ResultSet results = statement.executeQuery(selectPlayer);
            if (results.next())
                statement.executeUpdate(updateHeroString(player));
            else
            {
                String insertHero = String.format("INSERT INTO `swingy`.`heroes` (`id`, `name`, `type`, `level`, `exp`," +
                                " `atk`, `def`, `hp`)" +
                                " VALUES (%d, %s, %s, %d, %d, %d, %d, %d);",
                        player.getId(),
                        player.getName(),
                        player.getType(),
                        player.getLevel(),
                        player.getExp(),
                        player.getAtk(),
                        player.getDef(),
                        player.getHp()
                );
                statement.executeUpdate(insertHero);
            }
            statement.executeUpdate("DROP * FROM `swingy`.`artefacts'`");
            recordArtefacts(player.getArtefacts(), player.getId());
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

    private static void recordArtefacts(ArrayList<Artefact> artefacts, int playerId)
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
}
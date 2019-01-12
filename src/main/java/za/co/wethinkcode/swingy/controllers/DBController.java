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
    private  GameController gameController;

    public DBController(GameController controller)
    {
        gameController = controller;
    }

    private void closeDBConnection(Connection connection, Statement statement)
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

    private Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308?useSSL=false", "root", "password");

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

    public void createDB()
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

    public void initDB()
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();

            String createHeroTable =
                    "CREATE TABLE if not exists `swingy`.`heroes` (" +
                            "`name` TEXT NOT NULL , " +
                            "`type` TEXT NOT NULL , " +
                            "`level` INT NOT NULL , " +
                            "`exp` INT NOT NULL , " +
                            "`atk` INT NOT NULL , " +
                            "`def` INT NOT NULL , " +
                            "`hp` INT NOT NULL);"
                    ;
            statement.executeUpdate(createHeroTable);
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

    public ArrayList<Player> getPlayers()
    {
        int level, exp, atk, def, hp;
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
                name = results.getString("name");
                type = results.getString("type");
                level = results.getInt("level");
                exp = results.getInt("exp");
                atk = results.getInt("atk");
                def = results.getInt("def");
                hp = results.getInt("hp");
                int size = ((level - 1) * 5) + 10 - (level % 2);
                Coordinates coordinates = CoordinateFactory.newCoordinates(size / 2, size / 2, null);
                Player player = PlayerFactory.customPlayer(name, type, level, exp, atk, def, hp, coordinates,
                                                            gameController);
                if (player != null)
                    players.add(player);
                else
                    gameController.setErrors(new ArrayList<String>());
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

    public boolean heroExists(Player player)
    {
        boolean exists = false;
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = getConnection();
            statement = connection.createStatement();
            String select  = String.format("SELECT * FROM `swingy`.`heroes` WHERE (`name` = '%s' AND `type` = '%s' AND " +
                            "`level` = %d AND `exp` = %d AND `atk` = %d AND `def` = %d AND `hp` = %d) " ,
                    player.getName(),
                    player.getType(),
                    player.getLevel(),
                    player.getExp(),
                    player.getAtk(),
                    player.getDef(),
                    player.getHp()
            );

            ResultSet results = statement.executeQuery(select);
            exists = results.next();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeDBConnection(connection, statement);
        }
        return (exists);
    }

    public void recordHero(Player player)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            if (heroExists(player) == false)
            {
                connection = getConnection();
                statement = connection.createStatement();
                String insertHero = String.format("INSERT INTO `swingy`.`heroes` (`name`, `type`, `level`, `exp`," +
                                " `atk`, `def`, `hp`) " +
                                " VALUES ('%s', '%s', %d, %d, %d, %d, %d)",
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

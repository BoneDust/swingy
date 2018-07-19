package za.co.wethinkcode.swingy.controllers;

import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.models.playables.Villain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

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
        String createDB = "create database if not exists budas";
        statement.executeUpdate(createDB);//statement.executeQuery();
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

    public static ArrayList<Artefact> getArtefacts() throws Exception
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

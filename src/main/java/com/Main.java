package com;


import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Getter
@Setter
public class Main
{
    public static void main (String[] args) throws Exception
    {
        System.out.println("boombox\n");
        Connection conn;
        Statement statement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3308", "root", "password");
        statement = conn.createStatement();
        String createDB = "create database if not exists budas";
        statement.executeUpdate(createDB);//statement.executeQuery();
        statement.close();
        conn.close();
        System.out.println("booopoppo\n");

    }
}

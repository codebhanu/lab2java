package com.example.javafxlab2;

import  java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

private static final String url ="jdbc:mysql://localhost:3306/javafxLab2";
private static  final String userName="root";
private static   final String password="";

public static Connection getConnection() throws SQLException {
 return DriverManager.getConnection(url,userName,password);
}

}

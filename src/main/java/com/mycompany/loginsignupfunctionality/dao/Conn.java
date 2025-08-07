/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginsignupfunctionality.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author agamal
 */
public class Conn {
    
    private static final String url="jdbc:mysql://localhost/USERS_DB";
    private static final String user="root";
    private static final String password="22904110";
    
    private static Connection conn = null;
    
    private Conn(){}
    
    public static Connection getConnection() throws SQLException{
        if(conn == null || conn.isClosed()) conn = DriverManager.getConnection(url, user, password);
        
       return conn;
    }
    
    public static void closeConnection(Connection conn) throws SQLException{
        conn.close();
    }
}

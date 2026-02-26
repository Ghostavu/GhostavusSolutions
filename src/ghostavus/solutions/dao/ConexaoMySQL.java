/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghostavus.solutions.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    
    public static Connection getConexao() {
        try {
            String URL = "jdbc:mysql://localhost:3306/ghostavus_db";
            String USER = "root";
            String PASSWORD = "59995136";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro na conexão:" + e.getMessage());
            return null;
        }
    }
    
    public static Connection getConnection(){
        return getConexao();
    }
}   
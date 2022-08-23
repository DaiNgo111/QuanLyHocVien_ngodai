package com.ngodai.qlhv.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ngoda
 */
public class DBConnect {
    
    public static Connection getConnetion() {
        try {

        Connection cons = null;
        Class.forName("com.mysql.jdbc.Driver");
        cons = DriverManager.getConnection("jdbc:mySQL://127.0.0.1:3306/db_qlhv", "root", "");
        return cons;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
	 
	
    
         
    public static void main(String[] args) throws SQLException {
        Connection c = getConnetion();
        System.out.println(c.toString());
        c.close();
    }
     
       // chỗ này phải add thư viện vào mới chạy được nha  
}

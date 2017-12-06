
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
 * @author HP
 */
public class Demo {
        private Connection connection = null;
    
    public Connection connector(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
	}catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return connection;
	}                
        try{
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ics2101","root", "");
	}catch(SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return connection;
	}        
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
	} else {
            System.out.println("Failed to make connection!");
	}
        return connection;
    }
    public void closeDB (){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error"+ ex.getMessage());
        }
    }
}

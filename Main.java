

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class  Main{
 
    public static void main(String[] args) {
 
        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;

        try {
        	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading"
                    + " MS Access JDBC driver");
            cnfex.printStackTrace();
        }
 
        
        try {
 
            String dbURL = "jdbc:ucanaccess://C:\\Users\\kie69\\OneDrive\\바탕 화면\\Database4.accdb";
 
            connection = DriverManager.getConnection(dbURL); 
 
          
            statement = connection.createStatement();
 
            resultSet = statement
                    .executeQuery("SELECT * FROM Bookdb");

            String sql = "SELECT*FROM Bookdb";
            PreparedStatement pStmt = connection.prepareStatement( sql,  ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE );
            ResultSet rs = pStmt.executeQuery( ); 
           rs.moveToInsertRow();
         // updating values into insert row 
            rs.updateString("title","Harry Potter"); 
            rs.updateString("publisher", "bloomsbury"); 
           rs.updateString("author", "J.K ROLLING");
           rs.insertRow(); 
           rs.updateString("title","The Lord of the Rings"); 
           rs.updateString("publisher", "Allen&Unwin"); 
          rs.updateString("author", "J.R.R. Tolkein");
          rs.insertRow(); 
          rs.updateString("title","Pride and Prejudice"); 
          rs.updateString("publisher", "T.Egerton Kingdom"); 
         rs.updateString("author", "Jane Austen");
         rs.insertRow(); 
         rs.updateString("title","The Twilight Saga"); 
         rs.updateString("publisher", "Little, Brown and Company"); 
        rs.updateString("author", "Stephenie Meyer");
        rs.insertRow(); 
      
        sql = "SELECT*FROM Bookdb";
        pStmt = connection.prepareStatement( sql,  ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE );
        rs = pStmt.executeQuery( );
        while(rs.next()) {
            System.out.println(rs.getInt(1) + " | " + 
                    rs.getString(2) + " | " + 
                    rs.getString(3)+" | "+rs.getString(4));
         //insert row into rs & db rs.insertRow();  
          
        }
        }
        
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {
         
            try {
                if(null != connection) {
 
                   
                    resultSet.close();
                    statement.close();
 
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    
       
        }
    }

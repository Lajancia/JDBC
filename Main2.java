
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
 
            resultSet = statement.executeQuery("SELECT * FROM Bookdb");

            String sql = "SELECT*FROM Bookdb";
            PreparedStatement pStmt = connection.prepareStatement( sql,  ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE );
            ResultSet rs = pStmt.executeQuery( ); 
        
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " | " + 
                        resultSet.getString(2) + " | " + 
                        resultSet.getString(3)+" | "+resultSet.getString(4));
            }
            
            
            pstmt = connection.prepareStatement("update Bookdb set title=? where id=?");
            
            rs.absolute(1);
            rs.updateString("title","Hi Harry");
            rs.updateRow();
            
            Connection connection1 = DriverManager.getConnection(dbURL); 
            Statement statement1 = connection1.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM Bookdb");
            System.out.println("");
           
            resultSet1.next();
            resultSet1.next();
            resultSet1.next();
            resultSet1.next();
            System.out.println(resultSet1.getInt(1) + " | " + 
                    resultSet1.getString(2) + " | " + 
                    resultSet1.getString(3)+" | "+resultSet1.getString(4));
            resultSet1 = statement1.executeQuery("SELECT * FROM Bookdb");
            for(int i=0;i<3;i++) {
           //resultSet1.first();
            	 resultSet1.next();
                System.out.println(resultSet1.getInt(1) + " | " + 
                        resultSet1.getString(2) + " | " + 
                        resultSet1.getString(3)+" | "+resultSet1.getString(4));
               
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
		 
	 
 

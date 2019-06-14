package logindemo;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
   public static Connection connectdb(){
        Connection con = null;
        try {
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Login Table", "nakhla054", "nakh1436");
            return con;
        } catch (SQLException ex) {
            System.out.println("yyyyyyyyyyyy");
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

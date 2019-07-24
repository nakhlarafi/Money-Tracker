/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.derby.client.am.ResultSet;

/**
 * FXML Controller class
 *
 * @author 16101197
 */
public class UnfriendController implements Initializable {

    /**
     * Initializes the controller class.
     */
     Connection con = MyConnection.connectdb();
    String FrndUserid = EditFriendsInfoController.setUserid;
    String MyUserId = FXMLDocumentController.myUsername;
    @FXML
    public void deleteFriend(ActionEvent event) throws IOException{
         try {
            String deleteSql = "DELETE FROM NAKHLA054.FRIENDS WHERE FRIENDS_USERID = '"+FrndUserid+"' AND USERID= '"+MyUserId+"'";
            Statement smt = con.createStatement();
            int rs=smt.executeUpdate(deleteSql);
            
            String deleteSql2 = "DELETE FROM NAKHLA054.FRIENDS WHERE FRIENDS_USERID = '"+MyUserId+"' AND USERID= '"+FrndUserid+"'";
            smt = con.createStatement();
            int rj=smt.executeUpdate(deleteSql2);
            
            Parent signIn = FXMLLoader.load(getClass().getResource("Friends.fxml"));
            Scene signInScene = new Scene(signIn);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            //window.setTitle("Which one?");
            window.setScene(signInScene);
            window.show();
            
         } catch (SQLException ex) {
             Logger.getLogger(UnfriendController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
   
    public void back(ActionEvent event) throws IOException{
                    Parent signIn = FXMLLoader.load(getClass().getResource("EditFriendsInfo.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    //window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

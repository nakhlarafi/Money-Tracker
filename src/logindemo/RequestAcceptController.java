/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 16101197
 */
public class RequestAcceptController implements Initializable {
    
    Connection con = MyConnection.connectdb();
    ResultSet rs,rs2;
    PreparedStatement ps = null;
    
    String myUserid,frndUserid,name,status;
    public void acceptRequest(ActionEvent event) throws IOException{
        String sql = "insert into FRIENDS "+ " (userid, friends_userid)" + " values (?, ?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, myUserid);
            ps.setString(2, frndUserid);
            ps.executeUpdate();
            String sql2 = "insert into FRIENDS "+ " (userid, friends_userid)" + " values (?, ?)";
                
            ps=con.prepareStatement(sql);
            ps.setString(1, frndUserid);
            ps.setString(2, myUserid);
            ps.executeUpdate();
            
            String deleteSql = "DELETE FROM NAKHLA054.REQUEST WHERE NAME = '"+name+"' AND MY_USERID = '"+myUserid+"' AND FRND_USERID = '"+frndUserid+"' AND STATUS = 'pending'";
            
            
            
            Parent signIn = FXMLLoader.load(getClass().getResource("FriendsController.fxml"));
            Scene signInScene = new Scene(signIn);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            //window.setTitle("Which one?");
            window.setScene(signInScene);
            window.show();
                
        } catch (SQLException ex) {
            Logger.getLogger(RequestAcceptController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void setData(String myUserid, String frndUserid, String name, String status) {
       this.frndUserid = frndUserid;
       this.myUserid = myUserid;
       this.name = name;
       this.status = status;
    }
    
}

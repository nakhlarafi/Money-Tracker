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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class DeleteOrRemoceController implements Initializable {

    Connection con = MyConnection.connectdb();
    ResultSet rs,rs2;
    PreparedStatement ps = null;
    
    @FXML
    private Label lblTotal;
    int total;
    String userId;
    String groupId;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTotal.setText((Integer.toString(GroupEditController.totalCheck)));
    }    
    
    @FXML
    public void deleteMember(ActionEvent event){
        try {
            String deleteSql = "DELETE FROM MEMBERS WHERE USERID = '"+userId+"' AND GROUPID= '"+groupId+"'";
            Statement smt = con.createStatement();
            int rs=smt.executeUpdate(deleteSql);
            
            String updateQuery = "update GROUPS set TOTALEXPENSE=TOTALEXPENSE-"+total+" where GROUPID='"+groupId+"'";
            smt = con.createStatement();
            rs=smt.executeUpdate(updateQuery);
            
            updateQuery = "update MEMBERS set OWES=OWES-"+(total/(GroupEditController.globalCount))+" where GROUPID='"+groupId+"'";
            smt = con.createStatement();
            rs=smt.executeUpdate(updateQuery);
            
            
            Parent signIn = FXMLLoader.load(getClass().getResource("GroupEdit.fxml"));
            Scene signInScene = new Scene(signIn);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            //window.setTitle("Which one?");
            window.setScene(signInScene);
            window.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
         Parent signIn = FXMLLoader.load(getClass().getResource("GroupEdit.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
    }

    void setData(String groupId, int total, String userId) {
        this.userId = userId;
        this.groupId = groupId;
        this.total= total;
        System.out.println(total);
    }
    
}

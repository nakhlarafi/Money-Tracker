/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class EditFriendsInfoController implements Initializable {

    
    Connection con = MyConnection.connectdb();
    PreparedStatement ps = null; 
    ResultSet rs = null;
    
    String name,userid, owe, gets;
    String usernameMe = FXMLDocumentController.myUsername;
    @FXML
    private Label txName;
    
    @FXML
    private Label txUserid;
    
    @FXML
    private Label txOwe;
    
    @FXML
    private Label txGets;
    
    @FXML
    private TextField txGave;
    
    @FXML
    private TextField txTook;
    
    @FXML
    private Label txUpdate;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    public void gaveHim(ActionEvent event) {
        try {
        int addOwe=0,addGets=0;
        int moneyGiven = Integer.parseInt(txGave.getText());
        //System.out.println(moneyGiven);
        int hereOwe = Integer.parseInt(owe);
        int hereGet = Integer.parseInt(gets);
        //System.out.println(userid);
        
        moneyGiven = moneyGiven-hereOwe;
            System.out.println(moneyGiven);
        if (moneyGiven>=0) {
            hereGet = hereGet+moneyGiven;
            addGets = hereGet;
            System.out.println(addGets);
        } else {
            hereOwe = Math.abs(moneyGiven);
            addOwe = hereOwe;
            System.out.println(addOwe);
        }
        String sql = "update FRIENDS set owe="+addOwe+",gets ="+addGets+" where userid='"+usernameMe+"' and friends_userid='"+userid+"'";
        
        Statement smt = con.createStatement();
        int rl=smt.executeUpdate(sql);
        if(rl == 0){
            txUpdate.setText("Not Updated");
        }else{
            txUpdate.setText("Updated!");
        }

            
        } catch (Exception e) {
            System.out.println(e);
        }
                
    }
    
    
    public void setData(String name, String userid, String owe, String gets) {
        
        this.name = name;
        this.userid = userid;
        this.owe = owe;
        this.gets = gets;
        txName.setText(name);
        txUserid.setText(userid);
        txOwe.setText(owe);
        txGets.setText(gets);
    }
    
}

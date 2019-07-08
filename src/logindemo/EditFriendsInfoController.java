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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private Label txUpdates;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
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
            //addOwe = 0;
            addGets = hereGet;
            System.out.println(addGets);
        } else {
            hereOwe = Math.abs(moneyGiven);
            addOwe = hereOwe;
            System.out.println(addOwe);
        }
        gets = Integer.toString(addGets);
        owe = Integer.toString(addOwe);
        txGets.setText(Integer.toString(addGets));
        txOwe.setText(Integer.toString(addOwe));
        String sql = "update FRIENDS set owe="+addOwe+",gets ="+addGets+" where userid='"+usernameMe+"' and friends_userid='"+userid+"'";
        
        Statement smt = con.createStatement();
        int rl=smt.executeUpdate(sql);
        
        if(rl == 0){
            txUpdates.setText("Not Updated");
        }else{
            txUpdates.setText("Updated!");
        }
        System.out.println("aise");
        //initialize(null,null);
        } catch (Exception e) {
            System.out.println(e);
        }
                
    }
    
    @FXML
    public void tookFromHim(ActionEvent event){
        try {
            int takenMoney = Integer.parseInt(txTook.getText());
            int hereOwe = Integer.parseInt(owe);
            int hereGet = Integer.parseInt(gets);
            int checkMoney = takenMoney-hereGet;
            if (checkMoney>=0) {
                hereOwe = hereOwe+checkMoney;
                hereGet = 0;
            } else {
                hereGet = Math.abs(checkMoney);
            }
            
            gets = Integer.toString(hereGet);
            owe = Integer.toString(hereOwe);
       
            String sql = "update FRIENDS set owe="+hereOwe+",gets ="+hereGet+" where userid='"+usernameMe+"' and friends_userid='"+userid+"'";
            
            Statement smt = con.createStatement();
            int rl=smt.executeUpdate(sql);
            txGets.setText(Integer.toString(hereGet));
            txOwe.setText(Integer.toString(hereOwe));
            if(rl == 0){
                txUpdates.setText("Not Updated");
            }else{
                txUpdates.setText("Updated!");
            }
            System.out.println("aise");
            //initialize(null,null);
            
        } catch (Exception e) {
        }
    }
    
    public void back(ActionEvent event) throws IOException{
                    Parent signIn = FXMLLoader.load(getClass().getResource("Friends.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    //window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
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

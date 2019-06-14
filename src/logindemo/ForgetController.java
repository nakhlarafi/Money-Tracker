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
public class ForgetController implements Initializable {

    public static String usern;
    Connection con = MyConnection.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField txEmail;
    
    @FXML
    private TextField txUsername;
    
    @FXML
    private TextField txAns;
    
    /*public void setName(String s){
        usern = s;
    }
    
    public String getName(){
        return usern;
    }*/
    
    public void UpdatePass(ActionEvent event){
        try {
            String username = txUsername.getText();
            System.out.println(username);
            String email = txEmail.getText();
            String ans = txAns.getText();
            //setName(username);
            usern = username;
            //System.out.println(usern);
            String query = "SELECT EMAIL,USERID,QUE FROM NAKHLA054.USERINFO WHERE USERID='"+username+"'and EMAIL='"+email+"'and QUE='"+ans+"'";
            ps= con.prepareStatement(query); 
            rs=ps.executeQuery();
            
            if(rs.next()){
                 Parent signIn = FXMLLoader.load(getClass().getResource("UpdatePass.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Give new Password");
                    window.setScene(signInScene);
                    window.show();
            }
            else{
                lblStatus.setText("info doesn't match!");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

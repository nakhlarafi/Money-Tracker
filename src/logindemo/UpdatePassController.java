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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdatePassController implements Initializable  {

    Connection con = MyConnection.connectdb();
    PreparedStatement ps = null; 
    ResultSet rs = null;
    String pass;
    //ForgetController f = new ForgetController();
    
    /*public String getTheName(){
        System.out.println(f.getName());
        return f.getName();
    }*/
    
    //String u = f.getName();
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField txNewPass;
    
    public void update(ActionEvent event){
        try {
            List<String> arraList = new ArrayList();
            System.out.println("ki beta");
            pass = txNewPass.getText();
            String username = ForgetController.usern;
            System.out.println("ki beta");
            System.out.println("ken vai?"+username);
            //String query = "SELECT password FROM NAKHLA054.USERINFO WHERE USERID='"+username+"'and password='"+pass+"'";
            String query = "SELECT password FROM NAKHLA054.password WHERE USERID='"+username+"'";
            
            ps= con.prepareStatement(query); 
            rs=ps.executeQuery();
            
            while (rs.next()) {
                arraList.add(rs.getString(1));
            }
            Iterator i = arraList.iterator();
            int count = 0;
            boolean match = false;
            //System.out.println(arraList.size());
            while (arraList.size()>count) {
                match = Password.check(pass, arraList.get(count).toString());
                if(match){
                    break;
                }
                //System.out.println(count);
                ++count;
            }
            
            if (match) {
                lblStatus.setText("Password exists!");
            } else {
                System.out.println("Ashche edike");
                pass = Password.getSaltedHash(pass);
                Statement smt;
                String updateQuery = "update USERINFO set password='"+pass+"' where userid='"+username+"'";
                smt = con.createStatement();
                int rl=smt.executeUpdate(updateQuery);
                if(rl==0) {
                    lblStatus.setText("Failed!");
                }
                else{
                    lblStatus.setText("Updated!");
                    String insertPass = "insert into PASSWORD "+ " (USERID,PASSWORD)" + " values (?,?)";
                    
                    PreparedStatement pll = null;
                    ResultSet rll = null;
                    
                    pll = con.prepareStatement(insertPass);
                    pll.setString(1, username);
                    pll.setString(2, pass);
                    int s =pll.executeUpdate();
                    
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

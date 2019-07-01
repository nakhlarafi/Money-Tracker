/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class EditFriendsInfoController implements Initializable {

    
    @FXML
    private Label txName;
    
    @FXML
    private Label txUserid;
    
    @FXML
    private Label txOwe;
    
    @FXML
    private Label txGets;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData(String name, String userid, String owe, String gets) {
        
        txName.setText(name);
        txUserid.setText(userid);
        txOwe.setText(owe);
        txGets.setText(gets);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class TypeSelectorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button txFriends;
    
    @FXML
    private Button txGroups;
    
    
    public void Friends(ActionEvent event) throws IOException{
        try {
                    Parent signIn = FXMLLoader.load(getClass().getResource("Friends.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
                    
    }
    public void Go(ActionEvent event) throws IOException{
        Parent signIn = FXMLLoader.load(getClass().getResource("Groups.fxml"));
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

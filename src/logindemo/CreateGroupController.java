
package logindemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateGroupController implements Initializable {

    Connection con = MyConnection.connectdb();
    PreparedStatement ps = null;
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField txName;
    
    @FXML
    private TextField txGroupId;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void CreateGroup(ActionEvent event){
        try {
            String admin = FXMLDocumentController.myUsername;
            String name = txName.getText();
            String groupId = txGroupId.getText();
            String sql = "insert into GROUPS "+ " (GROUPID, GROUPNAME, ADMIN)" + " values (?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, groupId);
            ps.setString(2, name);
            ps.setString(3, admin);
            ps.executeUpdate();
            sql = "insert into MEMBERS "+ " (GROUPID, USERID, NAME)" + " values (?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, groupId);
            ps.setString(2, admin);
            ps.setString(3, FXMLDocumentController.onlyName);
            ps.executeUpdate();
            
            lblStatus.setText("Group has been created!");
        } catch (SQLException ex) {
            lblStatus.setText("This group id already exists");
            Logger.getLogger(CreateGroupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Back(ActionEvent event) throws IOException{
                    Parent signIn = FXMLLoader.load(getClass().getResource("Groups.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
    }
    
    
}

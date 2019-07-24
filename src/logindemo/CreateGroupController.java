
package logindemo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            ps.setString(1, name);
            ps.setString(2, groupId);
            ps.setString(3, admin);
            ps.executeUpdate();
            lblStatus.setText("Group has been created!");
        } catch (SQLException ex) {
            lblStatus.setText("This group id already exists");
            Logger.getLogger(CreateGroupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

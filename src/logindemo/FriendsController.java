/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class FriendsController implements Initializable {
    
    Connection con = MyConnection.connectdb();
    ResultSet rs;
    String username = FXMLDocumentController.myUsername;

    @FXML
    private TableView<ModelTableFriends> table;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_name;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_email;
    
    ObservableList<ModelTableFriends> oblist = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String query = "select * from FRIENDS inner join USERINFO on FRIENDS.USERID = USERINFO.USERID and FRIENDS.USERID='"+username+"'";
            //"SELECT NAME,EMAIL FROM NAKHLA054.FRIENDS"
            rs = con.createStatement().executeQuery(query);
            
            while (rs.next()) {
                oblist.add(new ModelTableFriends(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(oblist);
    }    
    
}

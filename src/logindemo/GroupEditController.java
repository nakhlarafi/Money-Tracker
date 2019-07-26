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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class GroupEditController implements Initializable {

    
    Connection con = MyConnection.connectdb();
    ResultSet rs,rs2;
    PreparedStatement ps = null;
    String groupId;
    String username = FXMLDocumentController.myUsername;
    
    @FXML
    private Label lblGroupName;
    
    @FXML
    private Label lblGroupId;
    
    @FXML
    private TextField txTaka;
    
    @FXML
    private TableView<TableGroupEdit> table;
    
    @FXML
    private TableColumn<TableGroupEdit, String> col_name;
    
    @FXML
    private TableColumn<TableGroupEdit, String> col_owe;
    
    @FXML
    private TableColumn<TableGroupEdit, String> col_gets;
    
    @FXML
    private TableColumn<TableGroupEdit, String> col_total;
    
     @FXML
    private TableColumn<TableGroupEdit, String> col_userid;
    
    
    ObservableList<TableGroupEdit> oblist = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            //Queries for updating the tables
            //String query = "select *  from USERINFO inner join MEMBERS on MEMBERS.USERID=USERINFO.USERID and MEMBERS.GROUPID='"+groupId+"'";
            groupId = GroupsController.groupNameGoes;
            String query = "select * from MEMBERS WHERE GROUPID='"+groupId+"'";
            //"SELECT NAME,EMAIL FROM NAKHLA054.FRIENDS"
            
            rs = con.createStatement().executeQuery(query);
            
            while (rs.next()) {
                System.out.println("aise");
                oblist.add(new TableGroupEdit(rs.getString("GROUPID"), rs.getString("USERID"),rs.getInt("GETS")
                        ,rs.getInt("OWES"),rs.getString("NAME"),rs.getInt("USEREXPENSE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_owe.setCellValueFactory(new PropertyValueFactory<>("owes"));
        col_gets.setCellValueFactory(new PropertyValueFactory<>("gets"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        table.setItems(oblist);
        
    }    

    void setData(String groupName, String groupId, int totalExpense, String status) {
        this.groupId = groupId;
        lblGroupName.setText(groupName);
        System.out.println("Ashche");
        
    }
    
}

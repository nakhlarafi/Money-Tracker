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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    String  groupId = GroupsController.groupNameGoes;
    String username = FXMLDocumentController.myUsername;
    
    @FXML
    private Label lblGroupName;
    
    @FXML
    private Label lblGroupId;
    
    @FXML
    private TextField txTaka;
    
    @FXML
    private TextField txAddMem;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private Label lblExpense;
    
    @FXML
    private TextField txExpense;
    
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
    
    @FXML
    public void addFriend(ActionEvent event){
        try {
            String id = txAddMem.getText();
            String sql1 = "SELECT * FROM MEMBERS WHERE USERID='"+id+"' AND GROUPID='"+groupId+"'";
            String sql3 = "SELECT NAME FROM USERINFO WHERE USERID='"+id+"'";
            String sql2 = "insert into MEMBERS "+ " (GROUPID, USERID,NAME)" + " values (?,?,?)";
            rs = con.createStatement().executeQuery(sql1);
            if (!rs.next()) {
               rs =  con.createStatement().executeQuery(sql3);
               String uName = ""; 
               if (rs.next()) {
                uName = rs.getString("NAME");
                ps=con.prepareStatement(sql2);
                ps.setString(1, groupId);
                ps.setString(2, id);
                ps.setString(3, uName);
                ps.executeUpdate();
                lblMessage.setText("Updated");
                oblist.clear();
                initialize(null,null);
                }
               else{
                   lblMessage.setText("No user named this.");
               }
            }
            else{
                lblMessage.setText("Already In the group.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void addExpense(ActionEvent event) throws SQLException{
        try {
            int expense = Integer.parseInt(txExpense.getText());
            int owes,get,total = 0,userExpense=0,count=0,value=0;
            String sql = "SELECT * FROM GROUPS WHERE GROUPID='"+groupId+"'";
            rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("TOTALEXPENSE");
            }
            sql = "SELECT count(*) FROM MEMBERS WHERE GROUPID='"+groupId+"'";
            rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            
            sql = "SELECT * FROM MEMBERS WHERE GROUPID='"+groupId+"' AND USERID='"+username+"'";
            rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                userExpense = rs.getInt("USEREXPENSE");
            }
            userExpense = userExpense+expense;
            //System.out.println(userExpense/4);
            Statement smt;
                String updateQuery = "update MEMBERS set USEREXPENSE="+userExpense+" where userid='"+username+"' AND GROUPID='"+groupId+"'";
                smt = con.createStatement();
                int rl=smt.executeUpdate(updateQuery);
                if(rl==0) {
                    //System.out.println("Hoi nai eikhane000");
                }
                else{
                    //System.out.println("ekta hoise");
                }
            total = total + expense;
            updateQuery = "update GROUPS set TOTALEXPENSE="+total+" where GROUPID='"+groupId+"'";
            smt = con.createStatement();
                rl=smt.executeUpdate(updateQuery);
                if(rl==0) {
                    //System.out.println("Hoi nai eikhane");
                }
                
                else{}
            
            value = total/count;
            System.out.println(count+" "+value+">>");
            String sqlUpdate = "update MEMBERS set OWES=USEREXPENSE-"+value+" where GROUPID='"+groupId+"'";
            smt = con.createStatement();
                rl=smt.executeUpdate(sqlUpdate);
                if(rl==0) {
                    lblExpense.setText("Not Updated");
                }
                else{
                    lblExpense.setText("Updated");
                }
                oblist.clear();
                initialize(null,null);
            //String sql = "update MEMBERS set OWES="+hereOwe+",gets ="+hereGet+" where userid='"+usernameMe+"' and friends_userid='"+userid+"'";
        } catch (SQLException ex) {
            Logger.getLogger(GroupEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}

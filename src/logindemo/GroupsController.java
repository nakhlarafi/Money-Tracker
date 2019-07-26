package logindemo;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 16101197
 */
public class GroupsController implements Initializable {

    Connection con = MyConnection.connectdb();
    ResultSet rs,rs2;
    PreparedStatement ps = null;
    public static String groupNameGoes;
    String username = FXMLDocumentController.myUsername;
    
    @FXML
    private TableView<TableGroups> table;
    
    @FXML
    private TableColumn<TableGroups, String> col_name;
    
    @FXML
    private TableColumn<TableGroups, String> col_userid;
    
    @FXML
    private TableColumn<TableGroups, String> col_total;
    
    @FXML
    private TableColumn<TableGroups, String> col_status;
    
    
    ObservableList<TableGroups> oblist = FXCollections.observableArrayList();
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            //Queries for updating the tables
            String query = "select *  from GROUPS inner join MEMBERS on GROUPS.GROUPID=MEMBERS.GROUPID AND MEMBERS.USERID='"+username+"'";
            //String query = "select * from FRIENDS inner join USERINFO on FRIENDS.USERID = USERINFO.USERID and FRIENDS.USERID='"+username+"'";
            //"SELECT NAME,EMAIL FROM NAKHLA054.FRIENDS"
            rs = con.createStatement().executeQuery(query);
            //rs2 = con.createStatement().executeQuery(query2);
            /*
            .
            .
            .
            .
            Friends Table*/
            while (rs.next()) {
                
                oblist.add(new TableGroups(rs.getString("GROUPNAME"), rs.getString("GROUPID"),rs.getString("ADMIN"),rs.getInt("USEREXPENSE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                
                FXMLLoader Loader = new FXMLLoader();
                groupNameGoes = table.getSelectionModel().getSelectedItem().getGroupId();
                Loader.setLocation(getClass().getResource("GroupEdit.fxml"));
                
                try {
                    Loader.load();
                } catch (IOException ex) {
                 ex.printStackTrace();
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                GroupEditController groupEditController = Loader.getController();
                
                groupEditController.setData(table.getSelectionModel().getSelectedItem().getGroupName(), 
                        table.getSelectionModel().getSelectedItem().getGroupId(),
                        table.getSelectionModel().getSelectedItem().getTotalExpense(),
                        table.getSelectionModel().getSelectedItem().getStatus());
                Parent p = Loader.getRoot();
                Scene signInScene = new Scene(p);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Groups");
                    window.setScene(signInScene);
                    window.show();
            }
        });
       
       
        
        
        col_name.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("totalExpense"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(oblist);
    }
    
    
    
    
    public void goToCreateGroup(ActionEvent event) throws IOException{
        Parent signIn = FXMLLoader.load(getClass().getResource("CreateGroup.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    //window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
    }
    
}

package logindemo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nakhla
 */
public class FriendsController implements Initializable {
    
    Connection con = MyConnection.connectdb();
    ResultSet rs;
    PreparedStatement ps = null;
    
    String username = FXMLDocumentController.myUsername;

    @FXML
    private TextField txFriend;
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private TableView<ModelTableFriends> table;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_name;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_userid;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_owe;
    
    @FXML
    private TableColumn<ModelTableFriends, String> col_gets;
    
    
    ObservableList<ModelTableFriends> oblist = FXCollections.observableArrayList();
    
    public void addFriends(ActionEvent event) throws SQLException{
        try { 
            String sql = "insert into FRIENDS "+ " (userid, friends_userid)" + " values (?, ?)";
            String frnd = txFriend.getText();
                ps=con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, frnd);
                ps.executeUpdate();
            String sql2 = "insert into FRIENDS "+ " (userid, friends_userid)" + " values (?, ?)";
                
                ps=con.prepareStatement(sql);
                ps.setString(1, frnd);
                ps.setString(2, username);
                ps.executeUpdate();
                //table.setItems(oblist);
                
                lblStatus.setText("Done");
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void back(ActionEvent event) throws IOException{
                    Parent signIn = FXMLLoader.load(getClass().getResource("TypeSelector.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String query = "select *  from USERINFO inner join FRIENDS on USERINFO.USERID = FRIENDS.FRIENDS_USERID and FRIENDS.USERID='"+username+"'";
            //String query = "select * from FRIENDS inner join USERINFO on FRIENDS.USERID = USERINFO.USERID and FRIENDS.USERID='"+username+"'";
            //"SELECT NAME,EMAIL FROM NAKHLA054.FRIENDS"
            rs = con.createStatement().executeQuery(query);
            
            while (rs.next()) {
                oblist.add(new ModelTableFriends(rs.getString("NAME"), rs.getString("USERID"),rs.getString("OWE"),rs.getString("GETS")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        col_owe.setCellValueFactory(new PropertyValueFactory<>("owe"));
        col_gets.setCellValueFactory(new PropertyValueFactory<>("gets"));
        table.setItems(oblist);
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                /*try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditFriendsInfo.fxml"));
                    Parent root1 = (Parent)fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
        }*/
                
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("EditFriendsInfo.fxml"));
                
                try {
                    Loader.load();
                } catch (IOException ex) {
                 ex.printStackTrace();
                    
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                EditFriendsInfoController editFriendsInfoController = Loader.getController();
                editFriendsInfoController.setData(table.getSelectionModel().getSelectedItem().getName(), 
                        table.getSelectionModel().getSelectedItem().getUserid(),
                        table.getSelectionModel().getSelectedItem().getOwe(),
                        table.getSelectionModel().getSelectedItem().getGets());
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            }
        });
    }    
    
}

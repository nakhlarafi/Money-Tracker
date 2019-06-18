package logindemo;
import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLDocumentController {
    
    Connection con = MyConnection.connectdb();
    PreparedStatement ps;
    ResultSet rs;
    public static String myUsername;
    
     /*public FXMLDocumentController() {
        initComponents();
        MyConnection.connectdb();
    }*/
    
    @FXML
    private AnchorPane rooPane;
    
    @FXML
    private Label labelStatus;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private PasswordField txPass;

    public FXMLDocumentController() {
        this.ps = null;
    }
    
    public void Login(ActionEvent event) throws Exception{
        try{
            String name = txtUsername.getText();
            myUsername = name;
            String pasw = txPass.getText();
            String login="SELECT password,userid FROM NAKHLA054.USERINFO WHERE USERID='"+name+"'";
            ps= con.prepareStatement(login); 
            rs=ps.executeQuery();
            if(rs.next()){
              String x = rs.getString(1);
              String y = rs.getString(2);
                System.out.println(y);
                boolean xx = Password.check(pasw, x);
                if(xx){
                    Parent signIn = FXMLLoader.load(getClass().getResource("TypeSelector.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Which one?");
                    window.setScene(signInScene);
                    window.show();
                }
                else{
                      labelStatus.setText("Access Denied");
                }
            }
            else{
                labelStatus.setText("Username Doesn't exist");
                }
        }catch(Exception ex){
            System.out.println(ex);
        }  
    }
    
    
    public void ForgetPass(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Forget.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
        }
        
    }
    
    
    public void Registration(ActionEvent event){
        try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch (Exception e) {
        }
    } 
    
}

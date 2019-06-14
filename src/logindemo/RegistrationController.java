package logindemo;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.xml.bind.DatatypeConverter;

public class RegistrationController implements Initializable {
    
    Connection con = MyConnection.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    //Password p = new Password();

    @FXML
    private Label txLabel;
    
    @FXML
    private TextField txName;
    
    @FXML
    private TextField txUsername;
    
    @FXML
    private PasswordField txPass;
    
    @FXML
    private PasswordField txPass1;
    
    @FXML
    private TextField txEmail;
    
    @FXML
    private TextField txQue;
    
    //String getPass = txPass.getText();
    
    //String hashPass = getHash((txPass.getText()).getBytes(), "SHA-256");
    
    public String getHash(byte[] inputBytes, String algorithm){
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashValue;
    }
    
    public void Register(ActionEvent event){
        try {
            String uname = txUsername.getText();
            String pasw = txPass.getText();
            String pasw1 = txPass1.getText();
            String login="SELECT userid FROM NAKHLA054.USERINFO WHERE USERID='"+uname+"'";
            ps= con.prepareStatement(login); 
            rs=ps.executeQuery();
            if(rs.next()){
                txLabel.setText("Username Exist");
            }
            else{
                if(pasw.equals(pasw1)){
                pasw = Password.getSaltedHash(pasw);
                String sql = "insert into USERINFO "+ " (NAME, EMAIL, USERID,PASSWORD,QUE)" + " values (?, ?, ?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setString(1, txName.getText());
                ps.setString(2, txEmail.getText());
                ps.setString(3, uname);
                ps.setString(4, pasw);
                ps.setString(5, txQue.getText());
                ps.executeUpdate();
                txLabel.setText("Done");
                String sql2 = "insert into password "+ " (USERID,PASSWORD)" + " values (?, ?)";
                ps=con.prepareStatement(sql2);
                ps.setString(1, uname);
                ps.setString(2, pasw);
                ps.executeUpdate();
               
                }
                else{
                    txLabel.setText("Password doesn't match.");
                }
            }
           
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

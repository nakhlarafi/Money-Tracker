package logindemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 16101197
 */
public class GroupsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Client;
import packages.entities.Coach;
import packages.services.ServiceClient;
import packages.services.ServiceCoach;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class ModifiermdpController implements Initializable {

    @FXML
    private PasswordField nvmdp;
    @FXML
    private PasswordField nvmdp1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modif(ActionEvent event) throws IOException {
        String login=AdminSMSController.log  ; 
        ServiceClient ser= new ServiceClient(); 
        ServiceCoach s = new ServiceCoach() ; 
        if(AdminSMSController.genre.equals("Client")){
        if(nvmdp.getText().equals(nvmdp1.getText())) {
          Client c = new Client(login,nvmdp1.getText() ) ; 
          Notifications notificationBuilder = Notifications.create()
                                                     .title("Mot de passe  modifier")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      //notificationBuilder.show(); 
      ser.modifiermdp(c);

        
        
        
        }
        }
        else if (AdminSMSController.genre.equals("Coach")){
        
        
        if(nvmdp.getText().equals(nvmdp1.getText())) {
          Coach c = new Coach(login,nvmdp1.getText() ) ; 
          Notifications notificationBuilder = Notifications.create()
                                                     .title("Mot de passe  modifier")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      notificationBuilder.show(); 
      s.modifiermdp(c);

        
        
        
        }
        
        
        
        
        }
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Login.fxml"));
        
        Parent root = loader.load();
        nvmdp.getScene().setRoot(root);
    }
    
}

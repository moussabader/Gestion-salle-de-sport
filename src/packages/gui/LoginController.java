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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class LoginController implements Initializable {
public static int id  ; 
    @FXML
    private TextField login_text;
    @FXML
    private PasswordField mdp_text;
    @FXML
    private CheckBox checkpass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showpassword() ; 
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        ServiceClient ser= new ServiceClient();
        List<Client> li =ser.ListClasse(); 
        ServiceCoach se= new ServiceCoach();
        List<Coach> lco =se.ListClasse();
        int i = 0 , test=0 ; 
         
        if (login_text.getText().equals("admin")&&(mdp_text.getText().equals("admin"))){test=1 ; }
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getLogin_c().equals(login_text.getText())&& (li.get(i).getMdp_c().equals(mdp_text.getText())) )
        
        { test=2 ; }   }
        
        for ( i=0 ; i<lco.size();i++){
        if (lco.get(i).getLogin_co().equals(login_text.getText())&& (lco.get(i).getMdp_co().equals(mdp_text.getText())) )
        
        { test=3 ; }   }
       
        
        
        
                
        
        
        
        
        
        if (test==1){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuAdmin.fxml"));
        
        Parent root = loader.load();
        login_text.getScene().setRoot(root);
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
        notificationBuilder.show();
        
        }
        else if (test==2) {FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuClient.fxml"));
        
        Parent root = loader.load();
        login_text.getScene().setRoot(root);
        id=ser.getid(login_text.getText()) ;
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      notificationBuilder.show();
        
        
        
       
        
        }
        
        
        
        
        
        
        else if (test==3){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Gcoach.fxml"));
        Parent root = loader.load();
        login_text.getScene().setRoot(root);
        id=se.getid(login_text.getText()) ;  
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      notificationBuilder.show();
        
        }
        
        
        else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Id ou mdp invalid  ");
        al.setHeaderText(null);
        al.show() ;   }
        
        
    }
    @FXML
    public void showpassword(){
     if (checkpass.isSelected()){
 mdp_text.setPromptText(mdp_text.getText());
 mdp_text.setText(""); 
  mdp_text.setDisable(true);

  }else {
 mdp_text .setText(mdp_text.getPromptText());
 mdp_text.setPromptText("");
 mdp_text.setDisable(false);
 }
    
    
    
    
    }

    @FXML
    private void mdpoublier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Mdpoublier.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
       
        
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
















}

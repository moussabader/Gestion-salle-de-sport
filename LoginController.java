/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import Entities.Client;
import Entities.Coach;
import Service.ServiceClient;
import Service.ServiceCoach;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login_text;
    @FXML
    private TextField mdp_text;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        ServiceClient ser= new ServiceClient();
        
        List<Client> li =ser.ListClasse(); 
        ServiceCoach se= new ServiceCoach();
        
        List<Coach> l =se.ListClasse();
        int i = 0 , test=0 ; 
         
        if (login_text.getText().equals("admin")&&(mdp_text.getText().equals("admin"))){test=1 ; }
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getLogin_c().equals(login_text.getText())&& (li.get(i).getMdp_c().equals(mdp_text.getText())) )
        
        { test=2 ; }   }
        for ( int j=0 ; i<l.size();j++){
        if (l.get(i).getLogin_co().equals(login_text.getText())&& (l.get(i).getMdp_co().equals(mdp_text.getText())) )
        
        { test=3 ; }   }
        
        
        
                
        
        
        
        
        
        if (test==1){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminPage.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        }
        else if (test==2) {FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Gclient.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        GclientController controller = loader.getController();
        controller.initData(login_text.getText());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();}
        else if (test==3){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminCoach.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
       
        
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
        else {Notifications notificationBuilder = Notifications.create()
                                                     .title("client ajouter")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show();  }
        
        
    }
    
















}

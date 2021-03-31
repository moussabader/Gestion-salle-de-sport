/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminPageController implements Initializable {

    @FXML
    private Button a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void coach(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminCoach.fxml"));
        Parent root = loader.load();
        a.getScene().setRoot(root);
    }

    @FXML
    private void client(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminClient.fxml"));
        Parent root = loader.load();
        a.getScene().setRoot(root);
    }
      @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        a.getScene().setRoot(root);
        
       
        
        
        
        
    }
    
}

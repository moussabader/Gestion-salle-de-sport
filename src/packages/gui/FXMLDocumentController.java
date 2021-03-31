/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button events;
    @FXML
    private Button tickets;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
     void go_events(ActionEvent event)  {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("evenment.fxml"));
            Stage stage = (Stage) events.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
    }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
     void consulter_tickets(ActionEvent event)  {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("ticket.fxml"));
            Stage stage = (Stage) tickets.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
    }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
   
}

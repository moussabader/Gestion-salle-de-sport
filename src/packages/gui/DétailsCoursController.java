/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Cours;
import packages.entities.Reservation;
import packages.services.CoursService;
import packages.services.ReservationService;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class DétailsCoursController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label des;
    @FXML
    private Label nomcoa;
    @FXML
    private ImageView img;
    
    CoursService cs = new CoursService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cours c =cs.findCoursById(ClientCoursController.idL);
        nom.setText(c.getNom());
        nomcoa.setText(c.getNomCoach());
        des.setText(c.getDescription());
        img.setImage(new Image("/img/" + c.getImage()));

    }    

    @FXML
    private void reserver(ActionEvent event) {
        ReservationService rs = new ReservationService();
        Reservation r = new Reservation();
        
        r.setIdcour(cs.findCoursById(ClientCoursController.idL).getId());
        r.setIduser(1);
        rs.Reserver(r);
        
              
          try {
                           Parent root = FXMLLoader.load(getClass().getResource("/GUI/Planing.fxml"));
                            Stage myWindow = (Stage) img.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        
        
    }
    
}

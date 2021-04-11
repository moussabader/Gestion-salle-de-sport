/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Cours;
import packages.services.CoursService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ModifierCoursController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField des;
    @FXML
    private TextField nomCoach;
    @FXML
    private ImageView img;

    CoursService cs = new CoursService();
            
            /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        Cours c = cs.findCoursById(AllCoursController.idL);
        
        nom.setText(c.getNom());
        nomCoach.setText(c.getNomCoach());
        des.setText(c.getDescription());
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
        
        Cours c1 = new Cours();
        c1.setId(AllCoursController.idL);
        c1.setNom(nom.getText());
        c1.setNomCoach(nomCoach.getText());
        c1.setDescription(des.getText());
        cs.ModifierCours(c1);
        
          try {
                           Parent root = FXMLLoader.load(getClass().getResource("AllCours.fxml"));
                            Stage myWindow = (Stage) des.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AllCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        
    }
    
}

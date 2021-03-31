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
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class GcoachController implements Initializable {
 
    @FXML
    private TextField nco_text;
    @FXML
    private TextField pc_co;
    @FXML
    private ComboBox gco_combo;
    @FXML
    private Label genre_lab;
    @FXML
    private Label prenom_lab;
    @FXML
    private Label nom_lab;
    @FXML
    private TextField nom_text;
    int ids= LoginController.id ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Musculation","Cardio","Gymnastique");
         gco_combo.setItems(list);
    }    
    
       

   

    @FXML
    private void affiche() {
         
        
       Coach c = new Coach() ; 
      ServiceCoach ser= new ServiceCoach(); 
      c=ser.select(ids) ; 
      nom_lab.setText(c.getNom_co());
      prenom_lab.setText(c.getPrenom_co());
      genre_lab.setText(c.getGenre_co()); 
      nco_text.setText(c.getNom_co()); 
   pc_co.setText(c.getPrenom_co()); 
   gco_combo.setValue(c.getGenre_co()); 
   
    }
    
    
     @FXML
    private void modifierco(ActionEvent event) {
        if (verifUserChamps()){
        if(controlSaisie()){
            
        ServiceCoach sc = new ServiceCoach();
        int id =sc.getid(nom_text.getText()) ;
        

        Coach c = new Coach(id,nco_text.getText(),  pc_co.getText(), gco_combo.getSelectionModel().getSelectedItem().toString());

        sc.modifierco(c);
        
        nco_text.clear();
        pc_co.clear();
        
        gco_combo.getSelectionModel().clearSelection();
    
        
        Notifications notificationBuilder = Notifications.create()
                                                     .title("client modifier")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
        }}
                
    }
    public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(nco_text.getText())){
            alert.setContentText("Le nom de salle de sport ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }


public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        nco_text.setStyle(styledefault);
        pc_co.setStyle(styledefault);
        
       
 

        if (nco_text.getText().equals("")) {
            nco_text.setStyle(style);
            verif = 1;
        }
       
        if ( pc_co.getText().equals("")) {
             pc_co.setStyle(style);
            verif = 1;
        }
         
        
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
        
        
    
    
}

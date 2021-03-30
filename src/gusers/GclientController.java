/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import Entities.Client;
import Service.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class GclientController implements Initializable {
    private String nom ; 
    @FXML
    private TextField nom_text;
    @FXML
    private Label nom_l;
    @FXML
    private Label prenom_l;
    @FXML
    private Label genre_l;
    @FXML
    private TextField nc_text;
    @FXML
    private TextField pc_text;
    @FXML
    private DatePicker ddn_text;
    @FXML
    private ComboBox gc_combo;
    @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme","Enfant");
         gc_combo.setItems(list);

        
        
    } 
    
    
    
    
    @FXML
    public void affiche () 
    {
      int ids = LoginController.id ; 
      Client c = new Client() ; 
      ServiceClient ser= new ServiceClient(); 
      c=ser.select(ids) ; 
      nom_l.setText(c.getNom_c());
      prenom_l.setText(c.getPrenom_c());
      genre_l.setText(c.getGenre_c()); 
      nc_text.setText(c.getNom_c()); 
      pc_text.setText(c.getPrenom_c()); 
      gc_combo.setValue(c.getGenre_c()); 
   
      
      
        
    
    
    
    }

    @FXML
    private void modifierc(ActionEvent event) {
        if (verifUserChamps()){
        if(controlSaisie()){
            int ids = LoginController.id ; 
            
            
            java.util.Date date = 
    java.util.Date.from(ddn_text.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        

        Client c = new Client( ids, nc_text.getText(),  pc_text.getText(), gc_combo.getSelectionModel().getSelectedItem().toString(),sqlDate);
        ServiceClient sc = new ServiceClient();

        sc.modifiercl(c);
        
        nc_text.clear();
        pc_text.clear();
        
        gc_combo.getSelectionModel().clearSelection();
        ddn_text.getEditor().clear();
        
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
         

        if(checkIfStringContainsNumber(nc_text.getText())){
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

   
       
        nc_text.setStyle(styledefault);
        pc_text.setStyle(styledefault);
        
       
 

        if (nc_text.getText().equals("")) {
            nc_text.setStyle(style);
            verif = 1;
        }
       
        if ( pc_text.getText().equals("")) {
             pc_text.setStyle(style);
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

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        nc_text.getScene().setRoot(root);
    }
    
    
}

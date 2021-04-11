/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Avis;
import packages.services.AviCRUD;
import static packages.gui.Alertbox.display;
import packages.entities.Avis;

import packages.entities.Produit;
import packages.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AjoutAviController implements Initializable {

    private DatePicker datp;
    @FXML
    private ComboBox<String> cbn;
    @FXML
    private TextField txtt;
    @FXML
    private TextArea txtc;
private ObservableList<String> data = FXCollections.observableArrayList("Excellent" , " Bien" , "Moyen" , "Bas" , "Mauvais");
    @FXML
    private AnchorPane AJt;
    @FXML
    private ComboBox<Produit> com;
    @FXML
    private Label nom;
   // private AnchorPane AJt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbn.setItems(data);
         com.setConverter(new StringConverter<Produit>() {
            @Override
            public String toString(Produit obj) {
                if (obj != null) {
                    return obj.getNom_produit();
                }
                return "";
            }

            @Override
            public Produit fromString(String string) {
                return null;
            }
        });
         
        try {
            String requete = "SELECT * FROM  Produit ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               
              com.getItems().add(new Produit(rs.getInt("id_produit"), rs.getString("nom_produit")));
                      
                      
                    //  cmbnom.getItems().addAll(rs.getString("prenomc"));
                  //    String h= cmbnom.getValue();
            }    
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                   
              
             try {
            String requete = "SELECT * FROM  client ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               
              com.getItems().add(new Produit(rs.getInt("id_c"), rs.getString("nom_c")));
                      
                      
                    //  cmbnom.getItems().addAll(rs.getString("prenomc"));
                  //    String h= cmbnom.getValue();
            }    
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                         
                      
    }
      


    @FXML
    private boolean insererAvi(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        if(txtt.getText() == null ||txtt.getText().trim().isEmpty()){
            alert.setContentText("Titre non vide");
            alert.showAndWait();
            return false;
         
              }
      else  if(txtc.getText() == null ||txtc.getText().trim().isEmpty()){
            alert.setContentText("Commentaire  non vide");
            alert.showAndWait();
            return false;
         
              }
        else{
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
LocalDateTime date = LocalDateTime.now();      
String titre= txtt.getText();
String commentaire = txtc.getText();
String dat = String.valueOf(dtf.format(date));
String niveau = cbn.getValue();
Produit produit = com.getValue();
String nom_produit = produit.getNom_produit();
Avis av = new Avis(titre, commentaire, dat, niveau, produit.getId_produit(), nom_produit);
//Avis av = new Avis(titre, commentaire, dat, niveau, 0, nomproduit);
AviCRUD VV = new AviCRUD();
    VV.ajouterAvis(av);
    display("Ajout","avec succée");
    Parent CalautoSc= FXMLLoader.load(getClass().getResource("aff.fxml"));
        datp.getScene().setRoot(CalautoSc);
        
          
        Notifications notificationBuilder = Notifications.create()
                .title("Avis")
                .text("Ajouter Nouvelle AVIS !")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))//Durée
                .position(Pos.BOTTOM_RIGHT)
               .onAction(new EventHandler<ActionEvent>(){
                    
                    
                    public void handle(ActionEvent event){
                        
                        System.out.println("Clicked on Notification"); 
                        
                    }
                    
                });
    
          notificationBuilder.darkStyle();
          //notificationBuilder.show();
        }
  
        return false;
   
    }  
    @FXML
    private void interfaceMenuClient(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            txtt.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

  
}

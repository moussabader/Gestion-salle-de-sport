/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import Entities.Coach;
import Service.ServiceCoach;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminCoachController implements Initializable {

    @FXML
    private TableView<Coach> table_coach;
    @FXML
    private TableColumn<String, Coach> nco_colone;
    @FXML
    private TableColumn<String, Coach> pco_colone;
    @FXML
    private TableColumn<String, Coach> gco_colone;
    @FXML
    private TableColumn<String, Coach> lco_colone;
    @FXML
    private TableColumn<String, Coach> mdpco_colone;
    @FXML
    private TextField nco_text;
    @FXML
    private TextField pco_text;
    @FXML
    private TextField lco_text;
    @FXML
    private ComboBox  gco_combo;
    @FXML
    private PasswordField mdpco_text;
    @FXML
    private TextField id_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("Cardio","Gymnastique","Musculation");
         gco_combo.setItems(list);
         populateTable () ; 
         
    } 
    
    
    
    /* Update Table */ 
    
    
    public void populateTable(){
        ServiceCoach ser= new ServiceCoach();
        
        List<Coach> li =ser.ListClasse();
                 ObservableList<Coach> data = FXCollections.observableArrayList(li);
                  

        
          
        nco_colone.setCellValueFactory(
                new PropertyValueFactory<>("nom_co"));
 
       
        pco_colone.setCellValueFactory(
                new PropertyValueFactory<>("prenom_co"));
 
        
        gco_colone.setCellValueFactory(
                new PropertyValueFactory<>("genre_co"));
        
        lco_colone.setCellValueFactory(
                new PropertyValueFactory<>("login_co"));
        
        mdpco_colone.setCellValueFactory(
                new PropertyValueFactory<>("mdp_co"));
        
        
        
        
        table_coach.setItems(data);
    }
    
  
    
    
    
    /* Ajouter */ 

    @FXML
    private void ajouterco(ActionEvent event) {
        
       ServiceCoach sc =new ServiceCoach();
              
              Coach C= new Coach( nco_text.getText(),  pco_text.getText(),gco_combo.getSelectionModel().getSelectedItem().toString(),  lco_text.getText() , mdpco_text.getText());
              
              
              
           sc.ajouterc(C);
           
          
         populateTable () ; 
          
        id_text.clear();
        nco_text.clear();
        pco_text.clear();
        gco_combo.getSelectionModel().clearSelection() ; 
        lco_text.clear();
        mdpco_text.clear();
      
  JOptionPane.showMessageDialog(null, "coach ajouté");
        
 }
    
    /* select from table */ 

    @FXML
    private void getselected(MouseEvent event) {
        
   Coach c = new Coach() ; 
   c=table_coach.getSelectionModel().getSelectedItem();  
   nco_text.setText(c.getNom_co()); 
   pco_text.setText(c.getPrenom_co()); 
   lco_text.setText(c.getLogin_co());
   mdpco_text.setText(c.getMdp_co());
 }

   /*Supprision*/ 
    
    @FXML
    private void supprimerco(ActionEvent event) {
        
         ServiceCoach sc=new ServiceCoach();
        Coach c =new Coach();
        
        c.setNom_co(nco_text.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            populateTable();
            System.out.println("suppression avec succées");
        }
        else{
            alert.close();
        } 
    }

    @FXML
    private void modifierco(ActionEvent event) {
        
       Coach c = new Coach( nco_text.getText(), pco_text.getText() ,gco_combo.getSelectionModel().getSelectedItem().toString(),lco_text.getText(),mdpco_text.getText());
        ServiceCoach sc = new ServiceCoach();

        sc.modifierc(c);
        populateTable(); 
        

           
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    






































}

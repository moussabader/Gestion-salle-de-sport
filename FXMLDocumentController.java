/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondesutulisateur;

import Entities.Coach;
import Service.ServiceCoach;
import com.oracle.net.Sdp;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Riva
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField idc;
    @FXML
    private TextField nomc;
    @FXML
    private TextField prenomc;
    @FXML
    private TextField genre;
    @FXML
    private TextField login;
    @FXML
    private TextField pw;
    @FXML
    private TableView<Coach> TableView;
  
    @FXML
    private TableColumn<Coach, Integer> idcolone;
    @FXML
    private TableColumn<Coach, String> nomcolone;
    @FXML
    private TableColumn<Coach, String> prenomcolone;
    @FXML
    private TableColumn<Coach, String> genrecolone;
    @FXML
    private TableColumn<Coach, String> logincolone;
    @FXML
    private TableColumn<Coach, String> mdpcolone;
    
    
    
    
    
    
   
  

    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.populateTable();

    }    
   public void populateTable(){
        ServiceCoach ser= new ServiceCoach();
        
        List<Coach> li =ser.ListClasse();
                 ObservableList<Coach> data = FXCollections.observableArrayList(li);
                  

        idcolone.setCellValueFactory(
                new PropertyValueFactory<>("idcoach"));
          
        nomcolone.setCellValueFactory(
                new PropertyValueFactory<>("nomc"));
 
       
        prenomcolone.setCellValueFactory(
                new PropertyValueFactory<>("prenomc"));
 
        
        genrecolone.setCellValueFactory(
                new PropertyValueFactory<>("genrec"));
        
        logincolone.setCellValueFactory(
                new PropertyValueFactory<>("loginc"));
        
        mdpcolone.setCellValueFactory(
                new PropertyValueFactory<>("pwc"));
        
        
        
        
        TableView.setItems(data);
    }
    @FXML
    private boolean ajouterc(ActionEvent event) {
        
        if(verifUserChamps()){
        ServiceCoach sc =new ServiceCoach();
        Coach c = new Coach();
        //int id = Integer.parseInt(string)
              String id=  idc.getText();
              
              int idd = Integer.parseInt(id);
              
              if((controlSaisie()==false) ) {
              
            
            return false;
            
        }
              
              Coach C= new Coach(idd, nomc.getText(),  prenomc.getText(),  genre.getText(),  login.getText() , pw.getText());
              
              
              ServiceCoach cs = new ServiceCoach(); 
              sc.ajouterc(C);
           
          
         List<Coach> lc = cs.ListClasse();
        
          ObservableList<Coach> data =
                  
        FXCollections.observableArrayList(lc );
          
        idc.clear();
        nomc.clear();
        prenomc.clear();
        genre.clear();
        login.clear();
        pw.clear();
      
      
 
 
        TableView.setItems(data);
        JOptionPane.showMessageDialog(null, "coach ajouté");
        }
        return false;
 
             
    }
    

    @FXML
    private void afficherc(ActionEvent event) throws SQLException {
        ServiceCoach sc =new ServiceCoach();
        ResultSet data = sc.afficherc(); }
    
    public boolean controlSaisie(){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(nomc.getText())){
            alert.setContentText("Le nom de coach ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(AlertType.ERROR);
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

   
       
        idc.setStyle(styledefault);
        nomc.setStyle(styledefault);
        prenomc.setStyle(styledefault);
        genre.setStyle(styledefault);
        login.setStyle(styledefault);
        pw.setStyle(styledefault);
       
 

        if (idc.getText().equals("")) {
            idc.setStyle(style);
            verif = 1;
        }
       
        if ( nomc.getText().equals("")) {
             nomc.setStyle(style);
            verif = 1;
        }
         
        if (prenomc.getText().equals("")) {
            prenomc.setStyle(style);
            verif = 1;
        }
       
        if (genre.getText().equals("")) {
            genre.setStyle(style);
            verif = 1;
        }
       
        if (login.getText().equals("")) {
            login.setStyle(style);
            verif = 1;
        }
        
        if (pw.getText().equals("")) {
            pw.setStyle(style);
            verif = 1;
        }
       
        
        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verfier votre champs!");
        return false;
    }

    @FXML
    private void supprimerc(ActionEvent event) throws SQLException {
        
        ServiceCoach sc=new ServiceCoach();
        Coach c =new Coach();

        c=this.TableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            this.populateTable();
            System.out.println("suppression avec succées");
        }
        else{
            alert.close();
        }
    }
    
    @FXML
    private void modifierc(ActionEvent event) throws ParseException {
       
            Coach cm = TableView.getSelectionModel().getSelectedItem();

            String Idcoach = idc.getText();
            String nom = nomc.getText();
            String prenom = prenomc.getText();
            String genrec = genre.getText();
            String loginc = login.getText();
            String pwc = pw.getText() ;
            
                

           
            Coach c = new Coach(cm.getIdcoach(), nom, prenom,genrec,loginc,pwc);
        ServiceCoach sc = new ServiceCoach();

        sc.modifierc(c);

        List<Coach> lc = sc.ListClasse();
        
        ObservableList<Coach> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
    }

    
    
    
    
    
}

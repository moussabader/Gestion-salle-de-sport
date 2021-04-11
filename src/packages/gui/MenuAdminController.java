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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuAdminController implements Initializable {

    @FXML
    private MenuBar menu;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    public void interfaceClients(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminClient.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceCoachs(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminCoach.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceAjoutProduit(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListProduit(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduit.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListCommande(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommandeAdmin.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceAjoutCours(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCours.fxml"));
        
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListCours() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllCours.fxml"));
        
        try {
            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        
    }
    /*public void interfacePlanning() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planing.fxml"));
        
        

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        

    }*/
    public void interfaceStatCours() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("statistique.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListReservation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGestionReservation.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceAjoutEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenment.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_event.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /*public void interfaceListTicket() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_ticket.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }*/
    public void interfaceListAvis() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminConsultAvis.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListReclamation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminConsultReclamation.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void Logout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        
        try {

            Parent root = loader.load();
            menu.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

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
public class MenuClientController implements Initializable {

    @FXML
    private Button btn_logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    public void interfaceProfilClient(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gclient.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListProduitsClient(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduitClient.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceAjoutCommande(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListCommandes(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommandeClient.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceListCoursClient(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientCours.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfacelistsuivi(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLShowSuivi.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void interfaceAjoutReservation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLReservation.fxml"));
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        
    }
    public void interfaceAjoutTicket() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListEventClient() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_event_client.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListTicketsClient() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_ticket.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceAjoutAvis() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutAvi.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListAvis() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Aff.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceAjoutReclamation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReclamation.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void interfaceListReclamation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consulter.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void Logout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        
        try {

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
public class MenuCoachController implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    private Hyperlink fblink;
    @FXML
    private Hyperlink iglink;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showSMpages();
        
    }
    
    public void interfaceProfilCoach(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gcoach.fxml"));
        
        
        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*public void interfacePlanningCoach() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planing.fxml"));

            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);

        
    }*/
    public void interfaceSuiviCoach(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSuivi.fxml"));
        
        
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
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            btn_logout.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void showSMpages() {
        fblink.setOnAction((t) -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/SWEAT-FIT-101446008663369"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        iglink.setOnAction((t) -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/sweatfitness8/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
    

}

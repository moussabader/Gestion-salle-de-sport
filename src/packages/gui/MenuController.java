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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuController implements Initializable {

    @FXML
    private Button res_client;
    @FXML
    private Button res_admin;
    @FXML
    private Button suivi_client;
    @FXML
    private Button suivi_coach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goToReseravtion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLReservation.fxml"));
        Parent root = loader.load();
        FXMLReservationController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToGestionReseravtion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGestionReservation.fxml"));
        Parent root = loader.load();
        FXMLGestionReservationController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void showSuivi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLShowSuivi.fxml"));
        Parent root = loader.load();
        FXMLShowSuiviController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToSuivi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSuivi.fxml"));
        Parent root = loader.load();
        FXMLSuiviController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

 

}

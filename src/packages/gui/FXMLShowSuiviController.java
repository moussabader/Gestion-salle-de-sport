/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Suivi;
import packages.services.SuiviCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLShowSuiviController implements Initializable {

    @FXML
    private TableView<Suivi> tablesuivi;
    @FXML
    private TableColumn<Suivi, String> col1Id;
    @FXML
    private TableColumn<Suivi, String> col2Id;
    @FXML
    private TableColumn<Suivi, String> col3Id;
    @FXML
    private TableColumn<Suivi, String> col4Id;
    
    
    private SuiviCRUD sc = new SuiviCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            col1Id.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("objectifs"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("conseils"));
            tablesuivi.setItems(sc.AfficherSuivi());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSuiviController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void backToMenu(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }
    
}

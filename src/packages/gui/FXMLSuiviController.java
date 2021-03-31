/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.entities.Suivi;
import packages.services.SuiviCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLSuiviController implements Initializable {

    @FXML
    private TextField nomcch;
    @FXML
    private TextField nomclt;
    @FXML
    private TextArea objctf;
    @FXML
    private TextArea cnsils;
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
    private int selectedId;
    boolean canModify = false;

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

        tablesuivi.getSelectionModel()
                .selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                        //Check whether item is selected and set value of selected item to Label
                        if (tablesuivi.getSelectionModel().getSelectedItem() != null) {
                            Suivi selectedSuivi = (Suivi) tablesuivi.getSelectionModel().getSelectedItem();
                            nomcch.setText(selectedSuivi.getNom_coach());
                            nomclt.setText(selectedSuivi.getNom_client());
                            objctf.setText(selectedSuivi.getObjectifs());
                            cnsils.setText(selectedSuivi.getConseils());


                            selectedId = selectedSuivi.getId_suivi();
                            canModify = true;

                        }
                    }
                }
                );
        // TODO
    }    

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        Suivi s = new Suivi();
        s.setNom_coach(nomcch.getText());
        s.setNom_client(nomclt.getText());
        s.setObjectifs(objctf.getText());
        s.setConseils(cnsils.getText());


        sc.AddSuivi(s);
        try {
            tablesuivi.setItems(sc.AfficherSuivi());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nomcch.clear();
        nomclt.clear();
        objctf.clear();
        cnsils.clear();
    }

    @FXML
    private void supprimerSuivi(ActionEvent event) throws SQLException{
        if (canModify) {
            sc.deleteSuivi(selectedId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer suivi");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Suivi supprimer");
            alert.showAndWait();
            try {
                tablesuivi.setItems(sc.AfficherSuivi());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSuiviController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer suivi");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
        nomcch.clear();
        nomclt.clear();
        objctf.clear();
        cnsils.clear();
        
    }

    @FXML
    private void modifierSuivi(ActionEvent event) {
        if (canModify) {
            Suivi s = new Suivi();
            s.setNom_coach(nomcch.getText());
            s.setNom_client(nomclt.getText());
            s.setObjectifs(objctf.getText());
            s.setConseils(cnsils.getText());

            sc.updateSuivi(selectedId, s);

            try {
                tablesuivi.setItems(sc.AfficherSuivi());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());                }
        } else {
            System.out.println("can't modify please select an item form the table");
        }
        nomcch.clear();
        nomclt.clear();
        objctf.clear();
        cnsils.clear();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }
    
}

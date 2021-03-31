/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.services.ReservationCRUD;
import packages.tools.SendMail;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLReservationController implements Initializable {

    @FXML
    private TableView<Reservation> tableres;
    @FXML
    private TableColumn<Reservation, String> col1Id;
    @FXML
    private TableColumn<Reservation, String> col2Id;
    @FXML
    private TableColumn<Reservation, java.sql.Date> col3Id;
    @FXML
    private TableColumn<Reservation, String> col4Id;

    private ReservationCRUD re = new ReservationCRUD();
    private int selectedId;
    boolean canModify = false;

    @FXML
    private TextField nomclt;
    @FXML
    private TextField nomcrs;
    @FXML
    private DatePicker dateres;
    //@FXML
    //private TableColumn<Reservation , Integer> col5Id;
    @FXML
    private TextField idres;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            col1Id.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("etat"));
            // col5Id.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
            tableres.setItems(re.AfficherReservation());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableres.getSelectionModel()
                .selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                        //Check whether item is selected and set value of selected item to Label
                        if (tableres.getSelectionModel().getSelectedItem() != null) {
                            Reservation selectedReservation = (Reservation) tableres.getSelectionModel().getSelectedItem();
                            nomclt.setText(selectedReservation.getNom_client());
                            nomcrs.setText(selectedReservation.getNom_cours());
                            dateres.setValue(selectedReservation.getDate_reservation().toLocalDate());

                            selectedId = selectedReservation.getId_reservation();
                            canModify = true;

                        }
                    }
                }
                );

    }

    @FXML
    private void ajouterReservation(ActionEvent event) {

        Reservation r = new Reservation();
        r.setNom_client(nomclt.getText());
        r.setNom_cours(nomcrs.getText());
        r.setDate_reservation(java.sql.Date.valueOf(dateres.getValue()));

        re.AddReservation(r);
//        SendMail.sendMail("soulaymennabil.kammoun@esprit.tn");

        try {
            tableres.setItems(re.AfficherReservation());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomclt.clear();
        nomcrs.clear();

    }

    @FXML
    private void modifierReservation(ActionEvent event) {
        if (canModify) {
            Reservation r = new Reservation();
            r.setNom_client(nomclt.getText());
            r.setNom_cours(nomcrs.getText());
            r.setDate_reservation(java.sql.Date.valueOf(dateres.getValue()));

            re.updateReservation(selectedId, r);
//            SendMail.sendMail("soulaymennabil.kammoun@esprit.tn");

            try {
                tableres.setItems(re.AfficherReservation());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't modify please select an item form the table");
        }
        nomclt.clear();
        nomcrs.clear();
    }

    @FXML
    private void supprimerReservation(ActionEvent event) throws SQLException {

        if (canModify) {
            re.deleteReservation(selectedId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer reservation");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Reservation supprimer");
            alert.showAndWait();
////           SendMail.sendMail("soulaymennabil.kammoun@esprit.tn");

            try {
                tableres.setItems(re.AfficherReservation());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer reservation");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
        nomclt.clear();
        nomcrs.clear();

    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

}

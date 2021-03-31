/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLGestionReservationController implements Initializable {

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
    private ComboBox etat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("Accepter","Reffuser");
         etat.setItems(list);
        
        try {
            col1Id.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("etat"));
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
                            selectedId = selectedReservation.getId_reservation();
                            canModify = true;
                        }
                    }
                }
                );
        // TODO
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
//            ComboBox Etat = (ComboBox) etat;
//            String natureT = (String) etat.getValue().toString();

    @FXML
    private void modifierEtat(ActionEvent event) {
        if (canModify) {
            Reservation r = new Reservation();
            r.setEtat(etat.getSelectionModel().getSelectedItem().toString());
//            r.setEtat(etat.getText());
            re.updateEtat(selectedId, r);
            try {
                tableres.setItems(re.AfficherReservation());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLGestionReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("can't modify please select an item form the table");
        }
        
    }

    @FXML
    private void Choix(ActionEvent event) {
    }

   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Cours;
import packages.services.CoursService;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packages.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class PlanningController implements Initializable {

        @FXML
    private TableView<Cours> table;
    @FXML
    private TableColumn<Cours, String> nom;
    @FXML
    private TableColumn<Cours, String> description;
    @FXML
    private TableColumn<Cours, String> caoch;
    
    private ObservableList<Cours> CoursData = FXCollections.observableArrayList();
    public static int idL = 0 ;
    ReservationService cs =  new ReservationService();
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Cours> listCours= new ArrayList<Cours>();
        listCours = (List<Cours>) cs.getAllCoursReserver(1);
        
        CoursData.clear();
        CoursData.addAll(listCours);
        table.setItems(CoursData);
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        caoch.setCellValueFactory(
            new PropertyValueFactory<>("nomCoach")
        );
     

    }    

    @FXML
    private void quitter(ActionEvent event) throws SQLDataException {
        Cours RecSelec = (Cours) table.getSelectionModel().getSelectedItem();

        cs.deleteReservation(1, RecSelec.getId());
        System.out.println("Controller.PlanningController.quitter()"+RecSelec.getId());
        resetTableData();
    }

           public void resetTableData() throws SQLDataException
    {
        List<Cours> listCours = new ArrayList<>();
        listCours = cs.getAllCoursReserver(1);
        ObservableList<Cours> data = FXCollections.observableArrayList(listCours);
        table.setItems(data);
    }
    
}

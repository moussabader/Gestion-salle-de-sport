/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Cours;
import packages.entities.Reservation;
import packages.services.CoursService;
import packages.services.ReservationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AllCoursController implements Initializable {

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
                CoursService cs =  new CoursService();
                ReservationService rs = new ReservationService();
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<?, ?> note;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Cours> listCours= new ArrayList<Cours>();
        listCours = cs.getAllCours();
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
        /*note.setCellValueFactory(
            new PropertyValueFactory<>("note")
        );*/
        
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
                  Cours rec = (Cours) table.getSelectionModel().getSelectedItem();
        
            idL = rec.getId();
                   try {
                           Parent root = FXMLLoader.load(getClass().getResource("/GUI/ModifierCours.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AllCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
        Cours RecSelec = (Cours) table.getSelectionModel().getSelectedItem();
        Cours r = new Cours();
        r = cs.findCoursById(RecSelec.getId());
        List<Reservation> list = new ArrayList<Reservation>();
        list = rs.getAllResrvation(RecSelec.getId());
        
        cs.deleteCours(r.getId());
        resetTableData();
        
    }
    
    
       public void resetTableData() throws SQLDataException
    {
        List<Cours> listCours = new ArrayList<>();
        listCours = cs.getAllCours();
        ObservableList<Cours> data = FXCollections.observableArrayList(listCours);
        table.setItems(data);
    }

    @FXML
    private void Recherche(ActionEvent event) {
        
        
          
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        caoch.setCellValueFactory(
            new PropertyValueFactory<>("nomCoach")
        );
        
        
        
        
                    List<Cours> list = cs.getAllCours();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Cours> filtredData= new FilteredList<>(CoursData, b ->true);
            recherche.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Cours> Cours;
                filtredData.setPredicate((Cours cours) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(cours.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (cours.getNomCoach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Cours> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
    }
    
}

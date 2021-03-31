/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Cours;
import packages.services.CoursService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ClientCoursController implements Initializable {

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
    @FXML
    private Label nbr;
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
             nbr.setText(String.valueOf(cs.NumCour()));
         } catch (SQLException ex) {
             Logger.getLogger(ClientCoursController.class.getName()).log(Level.SEVERE, null, ex);
         }
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
     
        
    }    

    @FXML
    private void Detail(ActionEvent event) {
        
                Cours RecSelec = (Cours) table.getSelectionModel().getSelectedItem();
               idL =RecSelec.getId();
                
          try {
                           Parent root = FXMLLoader.load(getClass().getResource("/GUI/DétailsCours.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        
    }
    

}

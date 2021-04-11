/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Suivi;
import packages.services.SuiviCRUD;
import packages.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLShowSuiviController implements Initializable {
    SuiviCRUD sf = new SuiviCRUD();  

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
    private final ObservableList<Suivi> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;

    @FXML
    private TextField recherche;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        Aff();
        RechercheAV();
    }  
    
       public void Aff(){
                        try {
            con = MyConnection.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet res = ste.executeQuery("select * from suivi");
            while(res.next()){
                Suivi f=new Suivi(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
                
                data.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            col1Id.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("objectifs"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("conseils"));

            tablesuivi.setItems(data);

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Suivi> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(suivi -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (suivi.getObjectifs().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(suivi.getNom_coach()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Suivi> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablesuivi.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablesuivi.setItems(sortedData);
    }

    @FXML
    private void backToMenu(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        retour.getScene().setRoot(root);
    }
    @FXML
    private void interfaceMenuClient(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tablesuivi.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

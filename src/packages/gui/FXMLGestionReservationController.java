/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.entities.Suivi;
import packages.services.ReservationCRUD;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Button retour;
    @FXML
    private TextField recherche;
        
    private final ObservableList<Reservation> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("Accepter","Reffuser");
         etat.setItems(list);
        
            
        ReservationCRUD rc = new ReservationCRUD();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Reservation");
        series.getData().add(new XYChart.Data<>("Reffuser", rc.calculer()));
        series.getData().add(new XYChart.Data<>("Accepter", rc.calculerapp()));
        barChart.getData().addAll(series);


       
        // TODO
        
        Aff();
        RechercheAV();
    }  
    
       public void Aff(){
                        try {
            con = MyConnection.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet res = ste.executeQuery("select * from reservation");
            while(res.next()){
                Reservation f=new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getString(5));
                
                data.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            col1Id.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("etat"));
            tableres.setItems(data);
            
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

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reservation> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reser -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reser.getNom_cours().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (reser.getEtat().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reservation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableres.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableres.setItems(sortedData);
    }
    
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        retour.getScene().setRoot(root);

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
                    ReservationCRUD rc = new ReservationCRUD();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Reservation");
        series.getData().add(new XYChart.Data<>("Reffuser", rc.calculer()));
        series.getData().add(new XYChart.Data<>("Accepter", rc.calculerapp()));
        barChart.getData().clear();
        barChart.getData().addAll(series);
        Aff();
        RechercheAV();
        
    }

    @FXML
    private void Choix(ActionEvent event) {
    }
    @FXML
    private void interfaceMenuAdmin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tableres.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

}

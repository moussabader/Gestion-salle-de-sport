/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.entities.Suivi;
import packages.services.ReservationCRUD;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label msg;
    @FXML
    private Button retour;
    @FXML
    private TextField recherche;
        
    private final ObservableList<Suivi> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;

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

    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    private boolean Validchamp(TextArea T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    @FXML
    private void ajouterSuivi(ActionEvent event) {
          if(Validchamp(nomcch)&&Validchamp(nomclt)&&Validchamp(objctf)&&Validchamp(cnsils))
          {
                      Suivi s = new Suivi();
        s.setNom_coach(nomcch.getText());
        s.setNom_client(nomclt.getText());
        s.setObjectifs(objctf.getText());
        s.setConseils(cnsils.getText());


        sc.AddSuivi(s);
        try {
            tablesuivi.setItems(sc.AfficherSuivi());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomcch.clear();
        nomclt.clear();
        objctf.clear();
        cnsils.clear();
        msg.setText("");
        
        Aff();
        RechercheAV();
          }
          else
          {
           msg.setText("Verifier les champs");
          }

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
        
        Aff();
        RechercheAV();
    }

    @FXML
    private void modifierSuivi(ActionEvent event) {
                  if(Validchamp(nomcch)&&Validchamp(nomclt)&&Validchamp(objctf)&&Validchamp(cnsils))
          {
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
                Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't modify please select an item form the table");
        }
        nomcch.clear();
        nomclt.clear();
        objctf.clear();
        cnsils.clear();
                
        msg.setText("");
                
        Aff();
        RechercheAV();
          }
          else
          {
           msg.setText("Verifier les champs");
          }
    }

    @FXML
    public void ReturnMenuCoach(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuCoach.fxml"));

        try {

            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}

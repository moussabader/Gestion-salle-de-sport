/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Reservation;
import packages.services.ReservationCRUD;
import packages.tools.MyConnection;

import com.sun.javafx.iio.ImageStorage.ImageType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import org.controlsfx.control.Notifications;
import net.glxn.qrgen.QRCode;
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
    private Label msg;
    @FXML
    private Button retour;
    @FXML
    private TextField recherche;

    private final ObservableList<Reservation> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;
    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
      
        private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException {

        if(Validchamp(nomclt)&&Validchamp(nomcrs))
        {
                   Reservation r = new Reservation();
        r.setNom_client(nomclt.getText());
        r.setNom_cours(nomcrs.getText());
        r.setDate_reservation(java.sql.Date.valueOf(dateres.getValue()));
        r.setEtat("En attente");
        re.AddReservation(r);
//        SendMail.sendMail("soulaymennabil.kammoun@esprit.tn");

        Notifications notificationBuilder = Notifications.create()
        .title("Succes").text("Votre Reservation est ajouté").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_RIGHT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       //notificationBuilder.show();
       
        QRcode();
        
        try {
            tableres.setItems(re.AfficherReservation());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomclt.clear();
        nomcrs.clear();
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
    private void modifierReservation(ActionEvent event) {
                if(Validchamp(nomclt)&&Validchamp(nomcrs))
        {
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
        Aff();
        RechercheAV();

    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController s2 = loader.getController();
        retour.getScene().setRoot(root);

    }
    public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode() throws FileNotFoundException, IOException {
        String contenue = "Nom client: " + nomclt.getText() + "\n" + "Nom cours: " + nomcrs.getText(); 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\qr\\" + nomclt.getText() + ".jpg");
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final

    }@FXML
    private void interfaceMenuClient(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

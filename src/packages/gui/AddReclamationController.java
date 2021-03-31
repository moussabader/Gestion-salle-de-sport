/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import packages.entities.Coach;
import packages.entities.Reclamation;
import static packages.gui.Alertbox.display;
import packages.services.ReclamationCRUD;
import packages.tools.MyConnection;
import static packages.tools.MyConnection.instance;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import packages.services.ReclamationCRUD;


/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AddReclamationController implements Initializable {

    @FXML
    private TextArea tfdescription;
    @FXML
    private Button btnAjouter;
    private TextField cc;
    @FXML
    private ComboBox<Coach> cmbnom;
    @FXML
    private Label nomUser;
    @FXML
    private Label prenomUser;
    @FXML
    private ComboBox<String> ct;
private ObservableList<String> data = FXCollections.observableArrayList("Trop d'absences" ," Non engagé  "   );


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cmbnom.setConverter(new StringConverter<Coach>() {
            @Override
            public String toString(Coach obj) {
                if (obj != null) {
                    return obj.getNom_co()+" "+obj.getPrenom_co();
                }
                return "";
            }

            @Override
            public Coach fromString(String string) {
                return null;
            }
        });

        ct.setItems(data);
        // TODO

        try {
            String requete = "SELECT * FROM  coach";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                cmbnom.getItems().add(new Coach(rs.getInt("id_co"), rs.getString("nomc"), rs.getString("prenomc")));

                //  cmbnom.getItems().addAll(rs.getString("prenomc"));
                //    String h= cmbnom.getValue();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private boolean saveReclamation(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        
        
        if(tfdescription.getText() == null ||tfdescription.getText().trim().isEmpty()){
            alert.setContentText("Remplire description");
            alert.showAndWait();
            return false;
         
              }
        else {
            //  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
            // LocalDateTime date = LocalDateTime.now();  
            String description = tfdescription.getText();
            Coach coach = cmbnom.getValue();
            String prenomc = coach.getPrenom_co();
            String categorie = ct.getValue();
            // String dat = String.valueOf(dtf.format(date));
//Reclamation RR = new Reclamation( description, prenomc,categorie);
            Reclamation RR = new Reclamation(description, prenomc, categorie, coach.getId_co());
            ReclamationCRUD RC = new ReclamationCRUD();
            RC.ajouterReclamtion(RR);
            display("Ajout", "avec succée");
            Parent CalautoSc = FXMLLoader.load(getClass().getResource("consulter.fxml"));
            Scene Calculautoscene = new Scene(CalautoSc);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(Calculautoscene);
            window.show();
        }
        return false;
        
    }

   
}
    

    





       
        
    



    

        
    

    








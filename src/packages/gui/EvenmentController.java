/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.tools.cnxBD;
import packages.entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class EvenmentController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField tel;
    @FXML
    private TextField date;
    @FXML
    private TextField location;

    /**
     * Initializes the controller class.
     */
     Event asso;

   PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
     cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = myc.getConnection();
    @FXML
    private Text show;
    @FXML
    private Button consulter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_event(ActionEvent event) throws SQLException, Exception {
        if (nom.getText().isEmpty() || type.getText().isEmpty() || tel.getText().isEmpty()|| date.getText().isEmpty()
                || location.getText().isEmpty()) {
            show.setText("Enter all details");
            Notifications.create()
                    .title("Fields Are Empty")
                    .text("Enter all details")
                    .showError();
            
        } else {
        
        String st = "INSERT INTO event (nom_event,type_sport,phone_number, datee , location) VALUES (?,?,?,?,?)";
         preparedStatement = (PreparedStatement) cnx.prepareStatement(st);
         preparedStatement.setString(1, nom.getText());
         preparedStatement.setString(2, type.getText());
         preparedStatement.setString(3, tel.getText());
         preparedStatement.setString(4,date.getText());
         preparedStatement.setString(5, location.getText());
         preparedStatement.executeUpdate();
         show.setText("Added Successfully");
               Notifications.create()
                 .title("Added Seccessfully")
                 .text("Success")
                 .showInformation();
        mail.sendmail("ibrahim.jouini@esprit.tn");
        }
    }

    @FXML
    private void consulter_event(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("show_event.fxml"));
            Stage stage = (Stage) consulter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

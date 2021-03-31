/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.tools.cnxBD;
//import Crud.ticketCrud;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class TicketController implements Initializable {
 PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
     cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = myc.getConnection();
    @FXML
    private TextField px;
    @FXML
    private TextField nbr;
    @FXML
    private Button valider;
    @FXML
    private TextField nom_event;
    @FXML
    private Button afficher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
         
    }

   /* private void ajouter_ticket(ActionTicket ticket) throws SQLException, Exception {
      
    }*/

    @FXML
    private void ajouter_ticket(ActionEvent event) throws SQLException, Exception {
          if (nom_event.getText().isEmpty() || px.getText().isEmpty() || nbr.getText().isEmpty()) {
           // show.setText("Enter all details");
            Notifications.create()
                    .title("Fields Are Empty")
                    .text("Enter all details")
                    .showError();
            
        } else {
        
        String st = "INSERT INTO ticket (nom_event ,prix ,nombre) VALUES (?,?,?)";
         preparedStatement = (PreparedStatement) cnx.prepareStatement(st);
         preparedStatement.setString(1, nom_event.getText());
         preparedStatement.setString(2, px.getText());
         preparedStatement.setString(3, nbr.getText());
       
         preparedStatement.executeUpdate();
         //show.setText("Added Successfully");
               Notifications.create()
                 .title("Added Seccessfully")
                 .text("Success")
                 .showInformation();
        mail.sendmail("ibrahim.jouini@esprit.tn");
        }
    }

    /* void consulter_ticket(ActionEvent event)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("show_ticket.fxml"));
            Stage stage = (Stage)consulter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void afficher_tickets(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("show_ticket.fxml"));
            Stage stage = (Stage)afficher.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
   }
    
    
    
   
    

    


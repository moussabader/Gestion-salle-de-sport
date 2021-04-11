/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import Alert.AlertDialog;
import packages.entities.Cours;
import packages.entities.Reservation;
import packages.entities.Vote;
import packages.services.CoursService;
import packages.services.ReservationService;
import packages.services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class DetailsCoursController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label des;
    @FXML
    private Label nomcoa;
    @FXML
    private ImageView img;
    
    CoursService cs = new CoursService();
    @FXML
    private Button type_vote_oui;
    @FXML
    private Button type_vote_nom;
    @FXML
    private Label nbrlike;
    @FXML
    private Label nbrdeslike;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cours c =cs.findCoursById(ClientCoursController.idL);
        nom.setText(c.getNom());
        nomcoa.setText(c.getNomCoach());
        des.setText(c.getDescription());
        img.setImage(new Image("/img/" + c.getImage()));
        
        VoteService v = new VoteService();
        try {
            nbrlike.setText(String.valueOf(v.NumLike(ClientCoursController.idL)));
            nbrdeslike.setText(String.valueOf(v.NumdeLike(ClientCoursController.idL)));

        } catch (SQLException ex) {
            Logger.getLogger(DetailsCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void reserver(ActionEvent event) {
        ReservationService rs = new ReservationService();
        Reservation r = new Reservation();
        
        r.setIdcour(cs.findCoursById(ClientCoursController.idL).getId());
        r.setIduser(1);
        rs.Reserver(r);
        
              
          try {
                           Parent root = FXMLLoader.load(getClass().getResource("Planing.fxml"));
                            Stage myWindow = (Stage) img.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        
        
    }

    @FXML
    private void Like(ActionEvent event) throws SQLException {
        
          VoteService sv  = new VoteService();
        if (sv.user_vote(1,ClientCoursController.idL)== null)
        {
            Vote v = new Vote ();
        v.setId_client(1);
        v.setType_vote(1);
        v.setId_cour(ClientCoursController.idL);
       sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/DétailsCours.fxml"));
            Stage myWindow =  (Stage) nom.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(DetailsCoursController.class.getName()).log(Level.SEVERE,null,ex);
           
        } 
        }
        else
        {
              AlertDialog.showNotification("Error !","Vous avez deja fait une reaction",AlertDialog.image_cross);
        
        }
       
    }

    @FXML
    private void Deslike(ActionEvent event) throws SQLException {
        
        
             VoteService sv  = new VoteService();
  if (sv.user_vote(1,ClientCoursController.idL)== null)
  {
       Vote v = new Vote ();
        v.setId_client(1);
        v.setType_vote(2);
        v.setId_cour(ClientCoursController.idL);
        sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/DétailsCours.fxml"));
            Stage myWindow =  (Stage) nom.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Cours");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(DetailsCoursController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
  }
  else
  {
         AlertDialog.showNotification("Error !","Vous avez deja fait une reaction",AlertDialog.image_cross);
        
  }
       
    }
    
}

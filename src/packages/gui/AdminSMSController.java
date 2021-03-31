/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;


import packages.entities.Client;
import packages.entities.Coach;
import packages.services.ServiceClient;
import packages.services.ServiceCoach;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminSMSController implements Initializable {
   

private  int randomnumber = ( int )( Math.random() * 9999 ); 
    @FXML
    private TextField id_text;
    @FXML
    private ComboBox combo;
    @FXML
    private TextField verif_text;
    @FXML
    private Label verif_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Coach","Client");
         combo.setItems(list);
        
    }    

    @FXML
    private void send(ActionEvent event) {
       
    verif_label.setText(""+randomnumber);

    ServiceClient ser= new ServiceClient();  
    ServiceCoach s=new ServiceCoach();
        if(combo.getSelectionModel().getSelectedItem().toString().equals("Client")){
        
        
            
      //if(ser.verif(id_text.getText())){      
     //  send("Votre nouveaux mot de passe est : "+randomnumber) ;      
      
      Notifications notificationBuilder = Notifications.create()
                                                     .title("mail envoyee")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      notificationBuilder.show(); 
      
         
         
        
       // }
     /* else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }*/
        
        
        
        }
      
      
        
      
      
      
      
      
      else if(combo.getSelectionModel().getSelectedItem().toString().equals("Coach")){
        
          
        //  if(s.verif(id_text.getText())){
        
       // send("Code de verification : "+randomnumber) ; 
       
          
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Mail envoyee")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
        notificationBuilder.show(); 
          
        /*  }
          else  {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }*/
       }
        else{Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Choisir le genre !! ");
        al.setHeaderText(null);
        al.show() ;  }
                 
        
    }
    



    
     
      public void send(String s){
          String ACCOUNT_SID =
            "AC22ed7d71966330795b31f5f01b6a1079";
     String AUTH_TOKEN =
            "2e1d55432f34a723fa8f91e6000fcb78";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber("+21628141524"),
        new PhoneNumber("+12026186166"),s).create();
    
    }    

    @FXML
    private void verif(ActionEvent event) throws IOException {
        if(verif_text.getText().equals(""+randomnumber)){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminCoach.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
       
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        
        
        }
        
    }

    

}
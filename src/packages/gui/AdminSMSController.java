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
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminSMSController implements Initializable {
   

private  int randomnumber = ( int )( Math.random() * 9999 ); 
public static String log ; 
public static String genre ; 
    @FXML
    private TextField id_text;
    @FXML
    private ComboBox combo;
    @FXML
    private Label lab;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Coach","Client");
         combo.setItems(list);
        
    }    

   /* private void send(ActionEvent event) {
       
   

    ServiceClient ser= new ServiceClient();  
    ServiceCoach s=new ServiceCoach();
        if(combo.getSelectionModel().getSelectedItem().toString().equals("Client")){
        
        
            
      if(ser.verif(id_text.getText())){      
     //  send("Votre nouveaux mot de passe est : "+randomnumber) ;      
      
      Notifications notificationBuilder = Notifications.create()
                                                     .title("Message envoyee")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      notificationBuilder.show(); 
      
        TextInputDialog dialog = new TextInputDialog("Genuine coder") ; 
        dialog.setTitle("text input dialog"); 
        dialog.setHeaderText("text input dialog"); 
        dialog.setContentText("Entre the code"); 
        Optional<String> result = dialog.showAndWait() ; 
        if(result.isPresent()){System.out.println(""+result.get());} 
         
        
       }
     /* else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }*/
        
        
        
       // }
      
      
        
      
      
      
      
      
      //else if(combo.getSelectionModel().getSelectedItem().toString().equals("Coach")){ 
        
          
        //  if(s.verif(id_text.getText())){
        
       // send("Code de verification : "+randomnumber) ; 
       
          
       /* Notifications notificationBuilder = Notifications.create()
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
       /*}
        else{Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Choisir le genre !! ");
        al.setHeaderText(null);
        al.show() ;  }
                 
        
    }*/
    



    
     
      public void sendsms(String s){
          String ACCOUNT_SID =
            "AC22ed7d71966330795b31f5f01b6a1079";
     String AUTH_TOKEN =
            "563c4c885b2f72548d76546fb02a73da";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber("+21628141524"),
        new PhoneNumber("+12026186166"),s).create();
    
    }    

   

    @FXML
    private void sendtext(ActionEvent event) throws IOException {
        //lab.setText(""+randomnumber); 
        ServiceClient ser= new ServiceClient();  
    ServiceCoach s=new ServiceCoach();
        if(combo.getSelectionModel().getSelectedItem().toString().equals("Client")){
        
        genre="Client" ;
            
      if(ser.verif(id_text.getText())){      
      log=id_text.getText() ; 
        sendsms("votre code est"+randomnumber) ; 
        TextInputDialog dialog = new TextInputDialog("Genuine coder") ; 
        dialog.setTitle("text input dialog"); 
        dialog.setHeaderText("text input dialog"); 
        dialog.setContentText("Entre the code"); 
        Optional<String> result = dialog.showAndWait() ; 
        if((result.isPresent())&&(result.get().equals(""+randomnumber)) ){
            
            FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("modifiermdp.fxml"));
        
        Parent root = loader.load();
        id_text.getScene().setRoot(root);}
        else {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid number ");
        al.setHeaderText(null);
        al.show() ;
        }
         
        
       }
      else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }
        }
        else if (combo.getSelectionModel().getSelectedItem().toString().equals("Coach")){
        
        
         
        genre="Coach" ;
            
      if(s.verif(id_text.getText())){      
      log=id_text.getText() ; 
      sendsms("votre code est"+randomnumber)  ;  
        TextInputDialog dialog = new TextInputDialog("Genuine coder") ; 
        dialog.setTitle("text input dialog"); 
        dialog.setHeaderText("text input dialog"); 
        dialog.setContentText("Entre the code"); 
        Optional<String> result = dialog.showAndWait() ; 
        if((result.isPresent())&&(result.get().equals(""+randomnumber)) ){
            
            FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("modifiermdp.fxml"));
        
        Parent root = loader.load();
        id_text.getScene().setRoot(root);}
        else {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid number ");
        al.setHeaderText(null);
        al.show() ;
        }
         
        
       }
      else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }
        
        
        
        
        
        
        
        
        
        
        
        
        }    
        
        
        
        
        
         
    
    
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MdpoublierPage.fxml"));
        Parent root = loader.load();
        id_text.getScene().setRoot(root);
        
    }

    



















}
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class MdpoublierController implements Initializable {

    @FXML
    private TextField id_text;
    
    @FXML
    private ComboBox g_combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Coach","Client");
         g_combo.setItems(list);
        
    }    

    @FXML
    private void find(ActionEvent event) throws Exception {
    String rand= UUID.randomUUID().toString() ;
    ServiceClient ser= new ServiceClient();  
    ServiceCoach s=new ServiceCoach();
        if(g_combo.getSelectionModel().getSelectedItem().toString().equals("Client")){
        
        
            
      if(ser.verif(id_text.getText())){      
            
      Client c = new Client(id_text.getText(), rand) ; 
      Notifications notificationBuilder = Notifications.create()
                                                     .title("mail envoyee")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      //notificationBuilder.show(); 
      ser.modifiermdp(c);
         
         
        sendMail("iheb.harrath@esprit.tn", "Client", rand);
        }
      else {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }
        
        
        
        }
      
      
        
      
      
      
      
      
      else if(g_combo.getSelectionModel().getSelectedItem().toString().equals("Coach")){
        
          
          if(s.verif(id_text.getText())){
        Coach co = new Coach(id_text.getText(),rand);
        
        s.modifiermdp(co) ; 
        sendMail("iheb.harrath@esprit.tn", "Coach", rand);   
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Mail envoyee")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
        //notificationBuilder.show(); 
          
          }
          else  {Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Invalid id ");
        al.setHeaderText(null);
        al.show() ; }
       }
        else{Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Choisir le genre !! ");
        al.setHeaderText(null);
        al.show() ;  }
                 
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Login.fxml"));
        
        Parent root = loader.load();
        id_text.getScene().setRoot(root);
         
       
       
      
    }
    

   
	public  void sendMail(String recepient, String lastname, String code) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "iharrath2016@gmail.com"; //mail
        //Your gmail password
        String password = "21860726iheb harrath";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, lastname, code);

        //Send mail
        Transport.send(message);
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient, String LastName, String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Mots passe oublié");
            String htmlCode = "Cher/Chere "+LastName+", <br/>"
            		+ "Votre code d'activation: "+code+". <br/>"
            				+ "Cordialement,<br/> Esprit";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
        	System.out.print(ex.getMessage());
        }
        return null;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MdpoublierPage.fxml"));
        Parent root = loader.load();
        id_text.getScene().setRoot(root);
        
    }
    
}
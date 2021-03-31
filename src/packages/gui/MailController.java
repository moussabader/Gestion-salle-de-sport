



package packages.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailController implements Initializable {

    @FXML
    private TextField tfU;
    @FXML
    private TextField tfA;
    @FXML
    private TextField tfS;
    @FXML
    private TextArea tfM;
    @FXML
    private Button btE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
    
       
    


    @FXML
    private void Envoyer(MouseEvent event) {
          String ToEmail = tfU.getText();
       String FromEmail = tfA.getText();
       String FromEmailPasseword ="14254374";
       String Subjects =tfS.getText();
       Properties properties = new Properties();
       
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        
        
          Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
               return new javax.mail.PasswordAuthentication(FromEmail,FromEmailPasseword);
           }
           
            
        });
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
            message.setSubject(Subjects);
            message.setText(tfM.getText());
            Transport.send(message);
        } catch (Exception ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        
          }

    }
    
}
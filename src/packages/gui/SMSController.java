/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class SMSController implements Initializable {

    @FXML
    private TextField tet;
    @FXML
    private Button sm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void smssms(ActionEvent event) {
        
        String ACCOUNT_SID = "AC1f79dfa787d928396e6872e8d9029c3f";
     String AUTH_TOKEN = "21d490ff95bf269048361f98f3141993";

   
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21692854404"),
                new com.twilio.type.PhoneNumber("+14434007789"),
                "Where's Wallace?")
            .create();

        System.out.println(message.getSid());
    }
    }
    


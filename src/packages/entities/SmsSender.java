/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author asus
 */
public class SmsSender {
    

    /**
     * @param args the command line arguments
     */
    public static final String ACCOUNT_SID =
            "ACf6086d89e5e18bc32038e44af052eeec";
    public static final String AUTH_TOKEN =
            "5f7a3d5292aa7a8bd7dd2080d79425d5";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
        Message message; 
        message = Message 
                .creator(new PhoneNumber("+21699709503"), // to
                        new PhoneNumber("+19195900145"), // from
                        ""+s)
                .create();
  System.out.println("aaslema");
        System.out.println(message.getSid());
    
}
    
}

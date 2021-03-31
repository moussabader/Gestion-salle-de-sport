/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author ayoub
 */
public class mail {
     public static void sendmail(String recepient) throws Exception {

        String login = "ayoub.selmani.1@esprit.tn";
        String pass = "181JMT1346";
        String host = "smtp.gmail.com";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        // prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", login);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");

        prop.put("mail.smtp.ssl.trust", "smtp.aol.com");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, pass);
            }
        });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        Transport transport = session.getTransport("smtp");
//        transport.connect();
        //  transport.sendMessage(msg, msg.getAllRecipients());
        Message message = prepareMessage(session, login, recepient);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String login, String recepient) {

        try {

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(login));

            InternetAddress toAdresse = new InternetAddress(recepient);

            msg.setRecipient(Message.RecipientType.TO, toAdresse);
            msg.setSubject("SWEATFITNESS ");
            //  msg.setText("merci ");
            String htmlCode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "<title>Demystifying Email Design</title>\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                    + "</head>\n"
                    + "<body style=\"margin: 0; padding: 0;\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n"
                    + "        <tr>\n"
                    + "            <td style=\"padding: 10px 0 30px 0;\">\n"
                    + "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\n"
                    + "                    <tr>\n"
                    + "                        <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n"
                    + "                            <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/h1.gif\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    
                    + "                                <tr>\n"
                    + "                                    <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n"
                    + "                                        <b>We can't help evryone but Everyone can help someone</b>\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <td style=\"padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n"
                    + "                                      hey there is a new event , check our platform for more informations .\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                            </table>\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                </table>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "    </table>\n"
                    + "</body>\n"
                    + "</html>\n"
                    + "\n"
                    + "";
            msg.setContent(htmlCode, "text/html");

            return msg;
        } catch (Exception ex) {
        }
        return null;
    }
    
}

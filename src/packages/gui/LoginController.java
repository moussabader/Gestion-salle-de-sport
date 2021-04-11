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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class LoginController implements Initializable {
public static int id  ; 
private int occ = 0 ; 
public static List<Client> Mylist = new ArrayList<>(); 


    @FXML
    private TextField login_text;
    @FXML
    private PasswordField mdp_text;
    @FXML
    private CheckBox checkpass;
    @FXML
    private CheckBox check;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showpassword() ; 
     
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, InterruptedException {
        
        ServiceClient ser= new ServiceClient();
        
        List<Client> li =ser.ListClasse(); 
        ServiceCoach se= new ServiceCoach();
        List<Coach> lco =se.ListClasse();
        int i = 0 , test=0;  
         
        if (login_text.getText().equals("admin")&&(mdp_text.getText().equals("admin"))){test=1 ; }
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getLogin_c().equals(login_text.getText())&& (li.get(i).getMdp_c().equals(mdp_text.getText())) )
        
        { test=2 ; }   }
        
        for ( i=0 ; i<lco.size();i++){
        if (lco.get(i).getLogin_co().equals(login_text.getText())&& (lco.get(i).getMdp_co().equals(mdp_text.getText())) )
        
        { test=3 ; }   }
       
        
        
        
                
        
        if(occ==3){delay() ;System.out.println("test "); }
         
        
        if (test==1){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuAdmin.fxml"));
        
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("menu.css").toString());
        login_text.getScene().setRoot(root);
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
        //notificationBuilder.show();
        
        }
        else if (test==2) {FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuClient.fxml"));
        
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("menu.css").toString());
        login_text.getScene().setRoot(root);
        id=ser.getid(login_text.getText()) ;
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
        //notificationBuilder.show();
        
        
        
       
        
        }
        
        
        
        
        
        
        else if (test==3){
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuCoach.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("menu.css").toString());
        login_text.getScene().setRoot(root);
        id=se.getid(login_text.getText()) ;  
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Succusfuly ")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
      //notificationBuilder.show();
        
        }
        
        
        else {
        occ=occ+1 ;
            System.out.println(""+occ);
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Id ou mdp invalid  ");
        al.setHeaderText(null);
        al.show() ;
         
        
        }
        
       
        
        
    }
    @FXML
    public void showpassword(){
     if (checkpass.isSelected()){
 mdp_text.setPromptText(mdp_text.getText());
 mdp_text.setText(""); 
  mdp_text.setDisable(true);

  }else {
 mdp_text .setText(mdp_text.getPromptText());
 mdp_text.setPromptText("");
 mdp_text.setDisable(false);
 }
    
    
    
    
    }

    @FXML
    private void mdpoublier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MdpoublierPage.fxml"));
        Parent root = loader.load();
        mdp_text.getScene().setRoot(root);
        
    }





private void delay() throws InterruptedException {

        Thread.sleep(5000);

    }

    @FXML
    private List<Client> enregistre() {
        Client c = new Client () ; 
        
        if(check.isSelected()){
        c.setLogin_c(login_text.getText()); 
        c.setMdp_c(mdp_text.getText()); 
        Mylist.add(c) ; 
            System.out.println(""+login_text.getText()) ;
       }
       return Mylist ;  
    }

    @FXML
    private void select(ActionEvent event) {
        
         Mylist=enregistre() ;
        for (int i=0;i<Mylist.size();i++){
        if(Mylist.get(i).getLogin_c().equals(login_text.getText())){
        
        mdp_text.setText(Mylist.get(i).getMdp_c()); 
        }
        
        }
    }










}

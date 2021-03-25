/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import Entities.Client;
import Entities.Coach;
import Service.ServiceClient;
import Service.ServiceCoach;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminClientController implements Initializable {

    @FXML
    private TableView<Client> table_client;
    @FXML
    private TableColumn<String, Client> nc_colone;
    @FXML
    private TableColumn<String, Client> pc_colone;
    @FXML
    private TableColumn<Date, Client> ddn_colone;
    @FXML
    private TableColumn<String, Client> gc_colone;
    @FXML
    private TableColumn<String, Client> lc_colone;
    @FXML
    private TableColumn<String, Client> mdpc_colone;
    @FXML
    private TextField nc_text;
    @FXML
    private TextField pc_text;
    @FXML
    private TextField lc_text;
    @FXML
    private DatePicker ddn_text;
    @FXML
    private ComboBox gc_combo;
    @FXML
    private PasswordField mdpc_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme","Enfant");
         gc_combo.setItems(list);
         
         populateTable() ; 
        
    }  
    
    
    /*update table*/ 
    
    public void populateTable(){
        ServiceClient ser= new ServiceClient();
        
        List<Client> li =ser.ListClasse();
        ObservableList<Client> data = FXCollections.observableArrayList(li);
                  

        
          
        nc_colone.setCellValueFactory(
                new PropertyValueFactory<>("nom_c"));
 
       
        pc_colone.setCellValueFactory(
                new PropertyValueFactory<>("prenom_c"));
       ddn_colone.setCellValueFactory(  
                new PropertyValueFactory<>("ddn")) ; 
        
        gc_colone.setCellValueFactory(
                new PropertyValueFactory<>("genre_c"));
        
        lc_colone.setCellValueFactory(
                new PropertyValueFactory<>("login_c"));
        
        mdpc_colone.setCellValueFactory(
                new PropertyValueFactory<>("mdp_c"));
        
        
        
        
        table_client.setItems(data);
    }

    @FXML
    private void ajouterc(ActionEvent event) {
        
        ServiceClient sc =new ServiceClient();
        
        
          java.util.Date date = 
    java.util.Date.from(ddn_text.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(date.getTime());

              
              Client C= new Client( nc_text.getText(),  pc_text.getText(), gc_combo.getSelectionModel().getSelectedItem().toString(),  lc_text.getText() , mdpc_text.getText(),sqlDate );
              
              
              
           sc.ajouterc(C);
           
          
         populateTable () ; 
         Notifications notificationBuilder = Notifications.create()
                                                     .title("client ajouter")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
                 
                                                      
                 





//JOptionPane.showMessageDialog(null, "client ajouté");
         
    }
    
   @FXML
    private void getselected(MouseEvent event) {
        
   Client c = new Client() ; 
   c=table_client.getSelectionModel().getSelectedItem();  
   nc_text.setText(c.getNom_c()); 
   pc_text.setText(c.getPrenom_c()); 
   lc_text.setText(c.getLogin_c());
   mdpc_text.setText(c.getMdp_c());
   gc_combo.setValue(c.getGenre_c()); 
   
   
 } 
    
    
    
    
    
    
    
    

    @FXML
    private void modifierc(ActionEvent event) {
        java.util.Date date = 
    java.util.Date.from(ddn_text.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Client c = new Client(  nc_text.getText(),  pc_text.getText(), gc_combo.getSelectionModel().getSelectedItem().toString(),  lc_text.getText() , mdpc_text.getText(),sqlDate);
        ServiceClient sc = new ServiceClient();

        sc.modifierc(c);
        populateTable(); 
        
        
        
        
    }

    @FXML
    private void supprimerc(ActionEvent event) {
        
        
         ServiceClient sc=new ServiceClient();
        Client c =new Client();
        
        c.setNom_c(nc_text.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            populateTable();
            System.out.println("suppression avec succées");
        }
        else{
            alert.close();
        } 
    }
    
    
    @FXML
     public void calcul(ActionEvent event) throws IOException{
        ServiceClient ser= new ServiceClient();
        
        List<Client> li =ser.ListClasse(); 
        int i = 0, nh = 0 ,nf=0 , ne = 0 ; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getGenre_c().equals("Homme"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getGenre_c().equals("Femme")){nf=nf+1 ; } 
        if (li.get(i).getGenre_c().equals("Enfant")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Clientstat.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ClientstatController controller = loader.getController();
        controller.initData(nh,nf,ne);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        
        
        
                
        
  }
    










}

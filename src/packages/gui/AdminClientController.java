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
import packages.tools.MyConnection;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Swing;
import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Base64;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import static javafx.scene.input.KeyCode.D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminClientController implements Initializable {
public static int  nh = 0 ,nf=0 , ne = 0 ;  
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
    @FXML
    private CheckBox checkbox;
    @FXML
    private Pane pn;
    @FXML
    private TextField recherche_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme","Enfant");
         gc_combo.setItems(list);
         
         new BounceIn(pn).play(); 
         populateTable() ; 
         recherche() ; 
      
        
        
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
        String mdp ; 
        List<Client> li =sc.ListClasse(); 

        if(verifUserChamps() ){ 
    if ( controlSaisie()){
        if(checklogin()){
    
          java.util.Date date = 
    java.util.Date.from(ddn_text.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
mdp= encrypt(mdpc_text.getText()) ;

              
              Client C= new Client( nc_text.getText(),  pc_text.getText(), gc_combo.getSelectionModel().getSelectedItem().toString(),  lc_text.getText() , mdp,sqlDate );
              
        
              
               
           
              
        sc.ajouterc(C);
        nc_text.clear();
        pc_text.clear();
        lc_text.clear();
        mdpc_text.clear(); 
        gc_combo.getSelectionModel().clearSelection();
        ddn_text.getEditor().clear();
           
          
         populateTable () ; 
         Notifications notificationBuilder = Notifications.create()
                                                     .title("client ajouter")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
                 
                                                      
    }}
}




         
    }
    
   @FXML
    private void getselected(MouseEvent event) {
        
   Client c = new Client() ; 
   c=table_client.getSelectionModel().getSelectedItem();  
   nc_text.setText(c.getNom_c()); 
   pc_text.setText(c.getPrenom_c()); 
   lc_text.setText(c.getLogin_c());
   String de = decrypt(c.getMdp_c()) ;
   mdpc_text.setText(de);
   gc_combo.setValue(c.getGenre_c()); 
   
   
 } 
    
    
    
    
    
    
    
    

    @FXML
    private void modifierc(ActionEvent event) {
        if (verifUserChamps()){
        if(controlSaisie()){
            ServiceClient sc = new ServiceClient(); 
            int id = sc.getid(lc_text.getText()) ; 
            java.util.Date date = 
    java.util.Date.from(ddn_text.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Client c = new Client( id,nc_text.getText(),  pc_text.getText(), gc_combo.getSelectionModel().getSelectedItem().toString(),  lc_text.getText() , mdpc_text.getText(),sqlDate);
        

        sc.modifierc(c);
        populateTable();
        nc_text.clear();
        pc_text.clear();
        lc_text.clear();
        mdpc_text.clear(); 
        gc_combo.getSelectionModel().clearSelection();
        ddn_text.getEditor().clear();
        
        Notifications notificationBuilder = Notifications.create()
                                                     .title("client modifier")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
        }}
        
        
        
        
    }

    @FXML
    private void supprimerc(ActionEvent event) {
        
        
         ServiceClient sc=new ServiceClient();
        Client c =new Client();
        int id = sc.getid(lc_text.getText()) ; 
        c.setId_c(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            populateTable();
            Notifications notificationBuilder = Notifications.create()
                                                     .title("client supprimer")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
         nc_text.clear();
        pc_text.clear();
        lc_text.clear();
        mdpc_text.clear(); 
        gc_combo.getSelectionModel().clearSelection();
        ddn_text.getEditor().clear();
        }
        else{
            alert.close();
        } 
    }
    
    
    @FXML
     public void calcul(ActionEvent event) throws IOException{
        ServiceClient ser= new ServiceClient();
        
        List<Client> li =ser.ListClasse(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getGenre_c().equals("Homme"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getGenre_c().equals("Femme")){nf=nf+1 ; } 
        if (li.get(i).getGenre_c().equals("Enfant")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Clientstat.fxml"));
         Parent root = loader.load();
        lc_text.getScene().setRoot(root);
        
        
        
        
        
                
        
  }
     public String encrypt(String pw){
     
     return Base64.getEncoder().encodeToString(pw.getBytes()) ;
    }
     public String decrypt(String pw){
     
     return new String (Base64.getMimeDecoder().decode(pw)) ;
    }

    @FXML
    private void showpw(ActionEvent event) {
        if (checkbox.isSelected()){
 mdpc_text.setPromptText(mdpc_text.getText());
 mdpc_text.setText(""); 
  mdpc_text.setDisable(true);

  }else {
 mdpc_text .setText(mdpc_text.getPromptText());
 mdpc_text.setPromptText("");
 mdpc_text.setDisable(false);
 }
    }
    

public boolean controlSaisie(){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(nc_text.getText())){
            alert.setContentText("Le nom de salle de sport ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }


public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        nc_text.setStyle(styledefault);
        pc_text.setStyle(styledefault);
        lc_text.setStyle(styledefault);
        mdpc_text.setStyle(styledefault);
     
       
 

        if (nc_text.getText().equals("")) {
            nc_text.setStyle(style);
            verif = 1;
        }
       
        if ( pc_text.getText().equals("")) {
             pc_text.setStyle(style);
            verif = 1;
        }
         
        if (lc_text.getText().equals("")) {
            lc_text.setStyle(style);
            verif = 1;
        }
       
        if (mdpc_text.getText().equals("")) {
            mdpc_text.setStyle(style);
            verif = 1;
        }
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
public boolean checklogin (){
ServiceClient ser= new ServiceClient();
List<Client> li =ser.ListClasse();
boolean test=true ; 
for(int i =0 ; i<li.size();i++){
if (li.get(i).getLogin_c().equals(lc_text.getText())){
test=false ; 
 }}
if(test==false){  
Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("User login existe déja");
        al.setHeaderText(null);
        al.show() ;} 


return test ; }

 @FXML
    private void Clear(ActionEvent event) {
        
        
        nc_text.clear();
        pc_text.clear();
        lc_text.clear();
        mdpc_text.clear(); 
        gc_combo.getSelectionModel().clearSelection();
        ddn_text.getEditor().clear();

    }

   

    private void recherche() {
        recherche_text.textProperty().addListener((observable, oldValue, newValue) -> {
    ServiceClient ser= new ServiceClient();
String rech = oldValue+newValue ;         
        List<Client> li =ser.ListClasse1(rech);
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
});
    }
    

  @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminPage.fxml"));
        Parent root = loader.load();
        lc_text.getScene().setRoot(root);
    }

    @FXML
    private void printpdf(ActionEvent event) throws FileNotFoundException, Exception {
                File out = new File("tableclient.pdf") ; 
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ; 
        Page page = new Page(pdf, A4.PORTRAIT)  ; 
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ; 
        List<List<Cell>> tabledata = new ArrayList<List<Cell>>() ;       
        List<Cell> tablerow = new ArrayList<Cell>() ; 
        Cell cell = new Cell(f1,nc_colone.getText());
        tablerow.add(cell) ; 
        
          cell = new Cell(f1,pc_colone.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,ddn_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,gc_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,lc_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,mdpc_colone.getText());
        tablerow.add(cell) ;
        
    tabledata.add(tablerow) ; 
    
    
     
    
   Client co = new Client() ; 
   co=table_client.getSelectionModel().getSelectedItem();  
        Cell nc = new Cell(f2, co.getNom_c()) ; 
        Cell prc = new Cell(f2,co.getPrenom_c()) ;
         
        
        
        Cell gc = new Cell(f2,co.getGenre_c()) ; 
        Cell lc = new Cell(f2,co.getLogin_c()) ; 
        Cell mdpc = new Cell(f2,co.getMdp_c()) ; 
        tablerow = new ArrayList<Cell>() ; 
        tablerow.add(nc) ; tablerow.add(prc) ; tablerow.add(gc) ; tablerow.add(lc) ; tablerow.add(mdpc) ; 
        
    tabledata.add(tablerow) ; 
    table.setData(tabledata);
    table.setPosition(10f, 60f);
    table.setColumnWidth(0, 90f); 
    table.setColumnWidth(1, 90f); 
    table.setColumnWidth(2, 90f); 
    table.setColumnWidth(3, 90f); 
    table.setColumnWidth(4, 250f);  
    
    
    while(true){
    table.drawOn(page) ; 
    if(!table.hasMoreData()){
    table.resetRenderedPagesCount(); 
    break ; 
    
    }
    
    page=new Page(pdf,A4.PORTRAIT) ; 
    
    
    }
    
    pdf.flush();
    fos.close(); 
        System.out.println("saved to "+out.getAbsolutePath());
        
        
    }























}

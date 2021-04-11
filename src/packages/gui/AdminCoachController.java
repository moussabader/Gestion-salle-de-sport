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
import animatefx.animation.BounceIn;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class AdminCoachController implements Initializable {
public static int gym = 0 ,card =0 , musc = 0  ;
    @FXML
    private TableView<Coach> table_coach;
    @FXML
    private TableColumn<String, Coach> nco_colone;
    @FXML
    private TableColumn<String, Coach> pco_colone;
    @FXML
    private TableColumn<String, Coach> gco_colone;
    @FXML
    private TableColumn<String, Coach> lco_colone;
    @FXML
    private TableColumn<String, Coach> mdpco_colone;
    @FXML
    private TextField nco_text;
    @FXML
    private TextField pco_text;
    @FXML
    private TextField lco_text;
    @FXML
    private ComboBox  gco_combo;
    @FXML
    private PasswordField mdpco_text;
    @FXML
    private CheckBox checkbox;
    @FXML
    private TextField recherche_text;
    @FXML
    private Pane pn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("Cardio","Gymnastique","Musculation");
         gco_combo.setItems(list);
         populateTable () ; 
         recherche() ; 
         new BounceIn(pn).play(); 
         
    } 
    
    
    
    /* Update Table */ 
    
    
    public void populateTable(){
        ServiceCoach ser= new ServiceCoach();
        
        List<Coach> li =ser.ListClasse();
                 ObservableList<Coach> data = FXCollections.observableArrayList(li);
                  

        
          
        nco_colone.setCellValueFactory(
                new PropertyValueFactory<>("nom_co"));
 
       
        pco_colone.setCellValueFactory(
                new PropertyValueFactory<>("prenom_co"));
 
        
        gco_colone.setCellValueFactory(
                new PropertyValueFactory<>("genre_co"));
        
        lco_colone.setCellValueFactory(
                new PropertyValueFactory<>("login_co"));
        
        mdpco_colone.setCellValueFactory(
                new PropertyValueFactory<>("mdp_co"));
        
        
        
        
        table_coach.setItems(data);
    }
    
  
    
    
    
    /* Ajouter */ 

    @FXML
    private void ajouterco(ActionEvent event) {
        
        ServiceCoach sc =new ServiceCoach();
        String mdp ; 
        List<Coach> li =sc.ListClasse(); 

        if(verifUserChamps() ){ 
    if ( controlSaisie()){
        if(checklogin()){
    
          
       mdp= encrypt(mdpco_text.getText()) ;

              
              Coach C= new Coach ( nco_text.getText(),  pco_text.getText(), gco_combo.getSelectionModel().getSelectedItem().toString(),  lco_text.getText() , mdp );
              
        
              
               
           
              
        sc.ajouterc(C);
        nco_text.clear();
        pco_text.clear();
        lco_text.clear();
        mdpco_text.clear(); 
        gco_combo.getSelectionModel().clearSelection();
        
           
          
         populateTable () ; 
         Notifications notificationBuilder = Notifications.create()
                                                     .title("client ajouter")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
                 
                                                      
    }}
}}
    
    /* select from table */ 

    @FXML
    private void getselected(MouseEvent event) {
        
   Coach c = new Coach() ; 
   c=table_coach.getSelectionModel().getSelectedItem();  
   nco_text.setText(c.getNom_co()); 
   pco_text.setText(c.getPrenom_co()); 
   lco_text.setText(c.getLogin_co());
   String de = decrypt(c.getMdp_co()) ;
   
   mdpco_text.setText(de);
    gco_combo.setValue(c.getGenre_co()); 
 }

   /*Supprision*/ 
    
   
    
    
    
    
    
    @FXML
    private void modifierco(ActionEvent event) {
        if (verifUserChamps()){
        if(controlSaisie()){
            
           ServiceCoach sc = new ServiceCoach();
           int id = sc.getid(lco_text.getText()) ; 
        Coach c = new Coach(  id,nco_text.getText(),  pco_text.getText(), gco_combo.getSelectionModel().getSelectedItem().toString(),  lco_text.getText() , mdpco_text.getText());
        

        sc.modifierc(c);
        populateTable();
        nco_text.clear();
        pco_text.clear();
        lco_text.clear();
        mdpco_text.clear(); 
        gco_combo.getSelectionModel().clearSelection();
        
        
        Notifications notificationBuilder = Notifications.create()
                                                     .title("coach modifier")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
        }}
        
        
        
        
    }

    @FXML
    private void supprimerco(ActionEvent event) {
        
        
        ServiceCoach sc=new ServiceCoach();
        Coach c =new Coach();
        int id = sc.getid(lco_text.getText()) ; 
        c.setId_co(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            populateTable();
            Notifications notificationBuilder = Notifications.create()
                                                     .title("coach supprimer")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
         nco_text.clear();
        pco_text.clear();
        lco_text.clear();
        mdpco_text.clear(); 
        gco_combo.getSelectionModel().clearSelection();
        
        }
        else{
            alert.close();
        } 
    }
    
    
    @FXML
     public void calcul(ActionEvent event) throws IOException{
        ServiceCoach ser= new ServiceCoach();
        
        List<Coach> li =ser.ListClasse(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getGenre_co().equals("Gymnastique"))
        
        {gym=gym+1;}  ;
        if (li.get(i).getGenre_co().equals("Cardio")){card=card+1 ; } 
        if (li.get(i).getGenre_co().equals("Musculation")){musc=musc+1 ; }  }
        
               FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Coachstat.fxml"));
        Parent root = loader.load();
        lco_text.getScene().setRoot(root);
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
 mdpco_text.setPromptText(mdpco_text.getText());
 mdpco_text.setText(""); 
  mdpco_text.setDisable(true);

  }else {
 mdpco_text .setText(mdpco_text.getPromptText());
 mdpco_text.setPromptText("");
 mdpco_text.setDisable(false);
 }
    }
    

public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(nco_text.getText())){
            alert.setContentText("Le nom de salle de sport ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
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

   
       
        nco_text.setStyle(styledefault);
        pco_text.setStyle(styledefault);
        lco_text.setStyle(styledefault);
        mdpco_text.setStyle(styledefault);
     
       
 

        if (nco_text.getText().equals("")) {
            nco_text.setStyle(style);
            verif = 1;
        }
       
        if ( pco_text.getText().equals("")) {
             pco_text.setStyle(style);
            verif = 1;
        }
         
        if (lco_text.getText().equals("")) {
            lco_text.setStyle(style);
            verif = 1;
        }
       
        if (mdpco_text.getText().equals("")) {
            mdpco_text.setStyle(style);
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
ServiceCoach ser= new ServiceCoach();
List<Coach> li =ser.ListClasse();
boolean test=true ; 
for(int i =0 ; i<li.size();i++){
if (li.get(i).getLogin_co().equals(lco_text.getText())){
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
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminPage.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
       
        
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void printpdf(ActionEvent event) throws FileNotFoundException, Exception {
        File out = new File("tablecoach.pdf") ; 
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ; 
        Page page = new Page(pdf, A4.PORTRAIT)  ; 
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ; 
        List<List<Cell>> tabledata = new ArrayList<List<Cell>>() ;       
        List<Cell> tablerow = new ArrayList<Cell>() ; 
        Cell cell = new Cell(f1,nco_colone.getText());
        tablerow.add(cell) ; 
        
          cell = new Cell(f1,pco_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,gco_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,lco_colone.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,mdpco_colone.getText());
        tablerow.add(cell) ;
        
    tabledata.add(tablerow) ; 
    
    
     
    
   Coach co = new Coach() ; 
   co=table_coach.getSelectionModel().getSelectedItem();  
        Cell nco = new Cell(f2, co.getNom_co()) ; 
        Cell prco = new Cell(f2,co.getPrenom_co()) ;
        Cell gco = new Cell(f2,co.getGenre_co()) ; 
        Cell lco = new Cell(f2,co.getLogin_co()) ; 
        Cell mdpco = new Cell(f2,co.getMdp_co()) ; 
        tablerow = new ArrayList<Cell>() ; 
        tablerow.add(nco) ; tablerow.add(prco) ; tablerow.add(gco) ; tablerow.add(lco) ; tablerow.add(mdpco) ; 
        
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

    @FXML
    private void clear(ActionEvent event) {
        nco_text.clear();
        pco_text.clear();
        lco_text.clear();
        mdpco_text.clear(); 
        gco_combo.getSelectionModel().clearSelection();
        
    }
    
    
    private void recherche() {
        recherche_text.textProperty().addListener((observable, oldValue, newValue) -> {
    ServiceCoach ser= new ServiceCoach();
String rech = oldValue+newValue ;         
        List<Coach> li =ser.ListClasse1(rech);
        ObservableList<Coach> data = FXCollections.observableArrayList(li);
                  

        
          
        nco_colone.setCellValueFactory(
                new PropertyValueFactory<>("nom_co"));
 
       
        pco_colone.setCellValueFactory(
                new PropertyValueFactory<>("prenom_co"));
       
        
        gco_colone.setCellValueFactory(
                new PropertyValueFactory<>("genre_co"));
        
        lco_colone.setCellValueFactory(
                new PropertyValueFactory<>("login_co"));
        
        mdpco_colone.setCellValueFactory(
                new PropertyValueFactory<>("mdp_co"));
        
        
        
        
        table_coach.setItems(data);
});
    }
    @FXML
    private void interfaceMenuAdmin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            table_coach.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }






}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.entities.Avis;

import packages.tools.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class SupprimerModifierAvisController implements Initializable {

    @FXML
    private TextField tit;
    @FXML
    private TextArea com;
    private TextField dd;
    @FXML
    private TextField nn;
    @FXML
    private Button btmodif;
    @FXML
    private TableColumn<Avis, Integer> colID;
    @FXML
    private TableColumn<Avis, String> colTitre;
    @FXML
    private TableColumn<Avis, String> colCom;
    @FXML
    private TableColumn<Avis, String> colN;
    @FXML
    private TableView<Avis> tv;
    @FXML
    private Label lbid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showavi();// TODO
    }    

   public ObservableList<Avis> getAviList(){
        ObservableList<Avis> avislist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM avis";
        
        Statement st;
        
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            Avis avis;
            while(rs.next() ){
                avis = new Avis(rs.getInt("idavis"),rs.getString("titre"),rs.getString("commentaire"),rs.getString("niveau"));
                avislist.add(avis);
                
            }
          
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return avislist;
    }

        
        public void showavi(){
            ObservableList<Avis> list=  getAviList();          
            colID.setCellValueFactory(new PropertyValueFactory<Avis ,Integer>("idavis"));
         
         colTitre.setCellValueFactory(new PropertyValueFactory<Avis , String>("Titre"));
        colCom.setCellValueFactory(new PropertyValueFactory<Avis , String>("commentaire"));
        
       
        colN.setCellValueFactory(new PropertyValueFactory<Avis , String>("Niveau") );
        
         tv.setItems(list);
         
      
    }
        
   
    
    public Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/geeks";
        String login="root";
        String pwd="";
        try{
           Connection conn = DriverManager.getConnection(url, login, pwd);
            return conn;
        }catch(SQLException ex){
            System.out.println("Error2: "+ ex.getMessage());
            return null;
        }
    }
    


    private void executeUpdate(String query) {
        Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    

    @FXML
    private void selectid(MouseEvent event) {
        
        lbid.setVisible(false);
        Avis avis= tv.getSelectionModel().getSelectedItem();
       lbid.setText(String.valueOf(avis.getIdavis()));
       tit.setText(avis.getTitre());
       com.setText(avis.getCommentaire());
       nn.setText(avis.getNiveau());
       
    }

    @FXML
    private void ModifButton(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
   LocalDateTime date = LocalDateTime.now();     
        String query ="UPdate avis SET titre='"+tit.getText()+"',commentaire='"+ com.getText()+"',Niveau='"+ nn.getText()+"',Date='" +String.valueOf(dtf.format(date))+"' WHERE IDavis="+Integer.parseInt(lbid.getText());
    executeUpdate(query);
    showavi();
    }


    }

    

   
    


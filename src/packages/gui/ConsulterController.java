/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;


import packages.entities.Reclamation;
import packages.tools.MyConnection;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class ConsulterController implements Initializable {

    @FXML
    private TableView<Reclamation> tab;
    @FXML
    private TableColumn<Reclamation, String> cnom;
    @FXML
    private TableColumn<Reclamation, String> cdescription;
   
    
     public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, Integer> cid;
    @FXML
    private TableColumn<Reclamation, String> ccoach;
    private Label b1;
    @FXML
    private TextField rech;
    @FXML
    private Label bb;
    @FXML
    private TableColumn<Reclamation, String> ccat;
    @FXML
    private Button sup;
    
    
    
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
    public ObservableList<Reclamation> getReclamationList(){
        ObservableList<Reclamation> reclamationlist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from reclamation ";
        
        Statement st;
        
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            Reclamation reclam;
            while(rs.next() ){
                reclam = new Reclamation(rs.getInt("ID"),rs.getString("Description"),rs.getString("prenomc"),rs.getString("Categorie"));
                reclamationlist.add(reclam);
                
            }
          
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reclamationlist;
    }

   public void showreclamation(){
            ObservableList<Reclamation> list=  getReclamationList();          
            cid.setCellValueFactory(new PropertyValueFactory<Reclamation , Integer>("Id"));
        
         cdescription.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description"));
        
        
        ccoach.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Prenomc") );
        ccat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("categorie") );
         
        
        
         tab.setItems(list);
         
         
      
    }
         






    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       showreclamation(); // TODO
       showrec();
    }    

    
    
    public void showrec(){
    ObservableList<Reclamation> list = getReclamationList();
         
               
        cdescription.setCellValueFactory(new PropertyValueFactory<>("Description"));        
        ccoach.setCellValueFactory(new PropertyValueFactory<>("Prenomc"));
        ccat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        cid.setCellValueFactory(new PropertyValueFactory<>("Id"));    
        
        tab.setItems(list);
 
      
    FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);
		
		rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getPrenomc().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Reclamation.getPrenomc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tab.setItems(sortedData);
}
    private void getAdd(MouseEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("Supreclamation.fxml"));
 
Scene scene = new Scene (parent);
Stage stage = new Stage();
stage.setScene(scene);
stage.show(); 
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            
        }
        
    }

    
   
    

    @FXML
    private void selectid(MouseEvent event) {
        
        Reclamation rec = tab.getSelectionModel().getSelectedItem();
       bb.setText(String.valueOf(rec.getId()));
       
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
    private void supprimerreclamation(ActionEvent event) {
        String query ="DELETE FROM Reclamation WHERE id="+Integer.parseInt(bb.getText())+"";
        executeUpdate(query);
       showreclamation();
    }
     
}

   
   
    


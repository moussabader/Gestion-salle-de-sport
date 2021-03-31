/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;


import packages.entities.Avis;
import packages.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AffController implements Initializable {

    @FXML
    private TableColumn<Avis, Integer> ii;
    @FXML
    private TableColumn<Avis, String> tt;
    @FXML
    private TableColumn<Avis, String> cc;
    @FXML
    private TableColumn<Avis, String> dd;
    @FXML
    private TableColumn<Avis, String> nn;
    @FXML
    private TableView<Avis> table;
     public ObservableList<Avis> data = FXCollections.observableArrayList();
    @FXML
    private Button bt_supp;
    @FXML
    private Button bt_modifier;
    @FXML
    private Label lbID;
    @FXML
    private TextField filterField;
    @FXML
    private Label nbavis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       showavi();
        
       try {
       
       String req2 = "select COUNT(*) FROM avis";
                
        Statement st=   MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs2 = st.executeQuery(req2);
                
         while (rs2.next()){                   
            
             int count = rs2.getInt(1);
             nbavis.setText(Integer.toString(count)+" "+"Avis");
                    }
                   
            
        } catch (SQLException ex) {
           
        }
        
        
        
        // TODO
        showavii(); 
        
    }    

public void showavii(){
    ObservableList<Avis> list = getAvisList();
    
     tt.setCellValueFactory(new PropertyValueFactory<>("Titre"));       
        cc.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));        
        nn.setCellValueFactory(new PropertyValueFactory<>("niveau"));        
        
        
        table.setItems(list);
 
      
    FilteredList<Avis> filteredData = new FilteredList<>(list, b -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Avis -> {
				// If filter text is empty, display all avi.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first niveau and last niveau of every avi with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Avis.getNiveau().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Avis.getNiveau().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Avis> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
                
}

    public ObservableList<Avis> getAvisList(){
        ObservableList<Avis> avislist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM avis";
        
        Statement st;
        
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            Avis avi;
            while(rs.next() ){
              avi = new Avis(rs.getInt("idavis"),rs.getString("titre"),rs.getString("commentaire"),rs.getString("date"),rs.getString("niveau"));
                avislist.add(avi);
                
            }
          
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return avislist;
    }

        
        public void showavi(){
            ObservableList<Avis> list=  getAvisList();          
            ii.setCellValueFactory(new PropertyValueFactory<Avis ,Integer>("idavis"));
         
         tt.setCellValueFactory(new PropertyValueFactory<Avis , String>("Titre"));
        cc.setCellValueFactory(new PropertyValueFactory<Avis , String>("commentaire"));
        
        dd.setCellValueFactory(new PropertyValueFactory<Avis , String>("Date") );
        nn.setCellValueFactory(new PropertyValueFactory<Avis , String>("Niveau") );
        
         table.setItems(list);
         
      
    }

    @FXML
    private void loadAjoutavis(ActionEvent event) throws IOException {
       try{
            Parent parent = FXMLLoader.load(getClass().getResource("AjoutAvi.fxml"));
 
Scene scene = new Scene (parent);
Stage stage = new Stage();
stage.setScene(scene);
stage.show();
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            
        }
    }

    @FXML
    private void loadSuppmod(ActionEvent event) throws IOException{
         try{
            Parent parent = FXMLLoader.load(getClass().getResource("SupprimerModifierAvis.fxml"));
 
Scene scene = new Scene (parent);
Stage stage = new Stage();
stage.setScene(scene);
stage.show();
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            
        }
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
          Avis avis= table.getSelectionModel().getSelectedItem();
       lbID.setText(String.valueOf(avis.getIdavis()));
    }

    @FXML
    private void deleteButton(ActionEvent event) {
        
        String query ="DELETE FROM avis WHERE idavis="+Integer.parseInt(lbID.getText())+"";
    executeUpdate(query);
    showavi();
    }

    
}

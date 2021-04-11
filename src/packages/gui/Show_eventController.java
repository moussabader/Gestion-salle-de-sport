/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.tools.cnxBD;
import packages.entities.Event;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packages.entities.Avis;
import packages.entities.Produit;
import packages.tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class Show_eventController implements Initializable {

     PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
    private ObservableList<ObservableList> data;
    String SQL = "SELECT * from event";
    
    cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = myc.getConnection();
    @FXML
    private TableView tbl_event;
    @FXML
    private Button edit;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    public static String ref_event="";
       public static String nom_event="";
       
       private void fetColumnList() {

        try {
            ResultSet rs = cnx.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
              TableColumn test = new TableColumn("test");
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
              
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                
                tbl_event.getColumns().removeAll(col);
                tbl_event.getColumns().addAll(col);
                
                System.out.println("Column [" + i + "]");
                 tbl_event.setRowFactory(tv -> {

    // Define our new TableRow
    
    TableRow<Event> row = new TableRow<>();
     edit.setOnMouseClicked(( event) -> {
         System.out.println("hahhaha");
        
          
     Object selectedItems = tbl_event.getSelectionModel().getSelectedItems().get(0); ;
       /*  Object selectedItems2 = tbl_prom.getSelectionModel().getSelectedItems().get(1); ;
     Object selectedItems3 = tbl_prom.getSelectionModel().getSelectedItems().get(2); ;
     Object selectedItems4 = tbl_prom.getSelectionModel().getSelectedItems().get(3); ;*/

    Show_eventController.ref_event = selectedItems.toString().split(",")[0].substring(1);
      /*  Show_promController.dure = Integer.parseInt( selectedItems3.toString().split(",")[0].substring(1));
        Show_promController.prix = Integer.parseInt( selectedItems4.toString().split(",")[0].substring(1));*/

     /*Show_promController.dure = (int) selectedItems2;
     Show_promController.prix = (int)selectedItems1;*/
     System.out.println(Show_eventController.ref_event);
             
           try {
            Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
            edit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

     //Show_promController.
     });
    btnsupp.setOnMouseClicked(( event) -> {
        Object selectedItems = tbl_event.getSelectionModel().getSelectedItems().get(0);
        String data1 = selectedItems.toString().split(",")[0].substring(1);
        try {
            delete(data1);
        } catch (SQLException ex) {
            Logger.getLogger(Show_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    return row;
});
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
       
        
    }
       
       public void delete(String ref) throws SQLException {
        //PreparedStatement pre=(PreparedStatement) connection.preparedStatement("delete from event where id="+ref);
        
        String st = "delete from event where id=?";
            preparedStatement = (PreparedStatement) cnx.prepareStatement(st);
            preparedStatement.setString(1, ref);
            preparedStatement.execute();
            fetRowList();
       
    }
    
    private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = cnx.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }
            tbl_event.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    /*public void showeventsfilter() throws SQLException{
        List<Event> eventlist = new ArrayList<>();
        
            String req = "SELECT * FROM event";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                Event e = new Event();
                e.setNom_event(rs.getString("nom_event"));
                e.settype_sport(rs.getString("type_sport"));
                e.setPhone_number(Integer.parseInt(rs.getString("phone_number")));
                e.setLocation(rs.getString("location"));
                eventlist.add(e);
            }
        
    ObservableList<Event> list = FXCollections.observableArrayList(eventlist);

    FilteredList<Event> filteredData = new FilteredList<>(list, b -> true);
		
		filterField.textProperty().addListener((obs, oldValue, newValue) -> {
            
                    filteredData.setPredicate(e -> e.getnom_event().toLowerCase().contains(newValue.toLowerCase().trim()));
                
            });
        tbl_event.setItems(filteredData);
                
}*/
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetColumnList();
        
        fetRowList(); 
         
    }
    @FXML
    private void interfaceMenuAdmin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tbl_event.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

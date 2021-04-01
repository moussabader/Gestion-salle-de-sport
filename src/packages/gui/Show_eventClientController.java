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

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class Show_eventClientController implements Initializable {

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
            Stage stage = (Stage) edit.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
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
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetColumnList();
        
        fetRowList(); 
    }    
    
}

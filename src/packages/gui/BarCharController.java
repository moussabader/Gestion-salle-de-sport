/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.tools.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;



/**
 * FXML Controller class
 *
 * @author touil
 */
public class BarCharController implements Initializable {

    private CategoryAxis x;
    @FXML
    private PieChart piechart;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
   
   ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        statistique();
        
    }
    private void statistique(){
    
     data = FXCollections.observableArrayList();
        
          try {
       
              connection = MyConnection.getInstance().getCnx();
       String req2 = "select count(id), categorie from reclamation GROUP BY categorie ";
       preparedStatement = connection.prepareStatement(req2);
       ResultSet resultSet = preparedStatement.executeQuery();
       
      
         while (resultSet.next()){                   
            
              data.add(new PieChart.Data(resultSet.getString(2), resultSet.getInt(1)));
            
                    }
         
                   
            
        } catch (SQLException ex) {
           
        }
          piechart.setTitle("Catégorie Réclamation");
          piechart.setLegendSide(Side.LEFT);
          piechart.setData(data);
          
          
      
    }
    
}


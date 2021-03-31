/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AlertAvisController implements Initializable {

    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<PieChart.Data>PieChartData=FXCollections.observableArrayList(
new PieChart.Data("Excellent",50),
new PieChart.Data("Bien",20),
new PieChart.Data("Moyen",15),
new PieChart.Data("Bas",10),
new PieChart.Data("Mauvais",5));
        
        PieChart.setData(PieChartData);
        PieChart.setStartAngle(90);
        
        
        // TODO
    


    
}
}
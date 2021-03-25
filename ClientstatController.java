/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class ClientstatController implements Initializable {
private int nh,nf,ne ; 
    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void initData(int nho , int nfe , int nen )
    {
        nh=nho ; nf=nfe;ne=nen ; 
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Homme", nh),new PieChart.Data("Femme", nf),new PieChart.Data("Enfant", ne)
                
                
           );
        pie.setData(ol);

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminClient.fxml"));
        
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
       
        
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

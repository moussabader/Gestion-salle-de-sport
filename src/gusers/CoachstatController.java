/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class CoachstatController implements Initializable {
    
    @FXML
    private PieChart pie;
    @FXML
    private Label gym_label;
    @FXML
    private Label musc_label;
    @FXML
    private Label card_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    int gym=AdminCoachController.gym ,card=AdminCoachController.card,musc=AdminCoachController.musc;
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Gymnastique", gym),new PieChart.Data("Cardio", card),new PieChart.Data("Musculation", musc)
                
                
           );
        gym_label.setText(""+gym);card_label.setText(""+card);musc_label.setText(""+musc);
        pie.setData(ol);
    }    


    @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AdminCoach.fxml"));
        
        
        
        Parent root = loader.load();
        gym_label.getScene().setRoot(root);
        
    }
    
}

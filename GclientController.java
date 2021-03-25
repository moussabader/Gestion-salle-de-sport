/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusers;

import Entities.Client;
import Service.ServiceClient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hiheb
 */
public class GclientController implements Initializable {
    private String nom ; 
    @FXML
    private TextField nom_text;
    @FXML
    private Label nom_l;
    @FXML
    private Label prenom_l;
    @FXML
    private Label genre_l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    } 
    
    
    
    public void initData(String n )
    {
        nom=n; 
        nom_text.setText(nom);
    }
    public void affiche () 
    {
      Client c = new Client() ; 
      ServiceClient ser= new ServiceClient(); 
      c=ser.select(nom_text.getText()) ; 
      nom_l.setText(c.getNom_c());
      prenom_l.setText(c.getPrenom_c());
      genre_l.setText(c.getGenre_c()); 
      
      
        
    
    
    
    }
    
    
}

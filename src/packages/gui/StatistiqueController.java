/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.services.CoursService;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import packages.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart pc;
 ObservableList <PieChart.Data> ol = FXCollections.observableArrayList();
     
    private final Connection cnx;
    private static CoursService instance;
    
    public StatistiqueController() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static CoursService getInstance()
    {
        if (instance == null) {
            instance = new CoursService();
        }
        return instance; 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc.setTitle("resultat");

 
       try {
            String requete = "SELECT cours.nom, Count(*) AS Nombre_de_Fois FROM `cours` INNER JOIN reservations ON cours.id=reservations.id_cours  GROUP BY cours.nom";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
                while(rs.next())
                {
                    
                        ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                        
                             
                        
                        
           
                                                  pc.setData(ol);
       
        pc.setLegendSide(Side.LEFT);
        
        FadeTransition f = new FadeTransition(Duration.seconds(4),pc);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();  
            }
                    
           } catch (SQLException ex) {
                Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
                  
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import packages.entities.LigneCommande;
import packages.entities.Produit;
import packages.tools.MyConnection;



public class StatistiquesProduitController implements Initializable {
    @FXML
    private PieChart chart;
    @FXML
    private Label caption;
    @FXML
    private Label caption2;
    @FXML
    private Label caption3;
    @FXML
    private Button btn_list_pr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Statistiques des produits selon le nombre des commandes");
        
        List<LigneCommande> lclist = new ArrayList<>();
        LigneCommande lc = new LigneCommande();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        double total=0;
        
        try {
            String req = "SELECT * from produit";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                
                lc.setQuantite_commande(rs.getInt("quantite_commande"));
                lc.setNom_produit(rs.getString("nom_produit"));
                total += lc.getQuantite_commande();
                pieChartData.add(new PieChart.Data(lc.getNom_produit(), lc.getQuantite_commande()));
                lclist.add(lc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        chart.setData(pieChartData);
        FadeTransition transition = new FadeTransition(Duration.seconds(3),chart);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
        
        //System.out.println(chart.getData());
        chart.getData().sort(new Comparator<Data> () {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.getPieValue()<o2.getPieValue()) {
                 return 1;
                }
                return -1;
            }
        });
        //System.out.println(chart.getData());
        
        DecimalFormat df = new DecimalFormat("###.00");
      
        double max = chart.getData().get(0).getPieValue();
        double max2 = chart.getData().get(1).getPieValue();
        double max3 = chart.getData().get(2).getPieValue();
        String cap = df.format((max/total)*100);
        String cap2 = df.format((max2/total)*100);
        String cap3 = df.format((max3/total)*100);
        caption.setText(cap+"%");
        caption2.setText(cap2+"%");
        caption3.setText(cap3+"%");
        
        /*for (int i = 0; i <lclist.size()-1; i++) {
            double x = chart.getData().get(i).getPieValue();
            double y = chart.getData().get(i+1).getPieValue();
            
            if (x > y ) { // condition manque la comparaison avec max
                max = x;
                cap = df.format((max/total)*100);
                
            } else{
                max = y;
                cap = df.format((max/total)*100);
                          }   
        }
        caption.setText(cap+"%"); 
        
        for (int i = 0; i <lclist.size()-1; i++) {
            double x = chart.getData().get(i).getPieValue();
            double y = chart.getData().get(i+1).getPieValue();
            
            if (x > y &&  x!=max  ) {
                max2 = x;
                cap2 = df.format((max2/total)*100);
                
            } else if (x <= y &&  y!=max  ){
                max2 = y;
                cap2 = df.format((max2/total)*100);
                           
            }   
        }
        caption2.setText(cap2+"%");
        
        for (int i = 0; i <lclist.size()-1; i++) {
            double x = chart.getData().get(i).getPieValue();
            double y = chart.getData().get(i+1).getPieValue();
            
            if (x > y &&  x!=max &&  x!=max2  ) {
                max3 = x;
                cap3 = df.format((max3/total)*100);
                
            } else if (x <= y &&  y!=max &&  y!=max2   ){
                max3 = y;
                cap3 = df.format((max3/total)*100);
                           
            }   
        }
        caption3.setText(cap3+"%");*/

    }
    
        /*for ( PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    //caption.setTranslateX(e.getSceneX());
                    //caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }
    }*/
    public void showListProduits(){
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduit.fxml"));

        try {
            Parent root = loader.load();
            chart.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

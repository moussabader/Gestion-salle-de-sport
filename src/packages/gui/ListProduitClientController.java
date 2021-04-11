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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import packages.entities.Produit;
import packages.services.ProduitCRUD;
import packages.tools.MyConnection;


public class ListProduitClientController implements Initializable {

    @FXML
    private TableView<Produit> tv_pr_client;
    @FXML
    private TableColumn<Produit, String> col_nom_c;
    @FXML
    private TableColumn<Produit, String> col_marque_c;
    @FXML
    private TableColumn<Produit, Double> col_prix_c;
    @FXML
    private Button btn_commanderpr;
    @FXML
    private GridPane grid_img;
    @FXML
    private TextField txt_search;
    @FXML
    private ChoiceBox<String> list_search;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduits();
        filteredSearch();
        list_search.getItems().addAll("Nom Produit", "Marque Produit", "Prix");
        list_search.setValue("Nom Produit");
        txt_search.setPromptText("Rechercher ");
    
    }
    
    public void showProduits() {
        ProduitCRUD pc = new ProduitCRUD();
        List<Produit> listPr = pc.afficherProduits();
        ObservableList<Produit> list = FXCollections.observableArrayList(listPr);     
        
        //col_id.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nom_c.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        col_marque_c.setCellValueFactory(new PropertyValueFactory<>("marque_produit"));
        col_prix_c.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        tv_pr_client.setItems(list);
    }

    @FXML
    private void afficherInterfaceAjoutCmd(ActionEvent event) {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
        
        try {
        Parent root = loader.load();
        Produit p = tv_pr_client.getSelectionModel().getSelectedItem();  
        
        AjouterCommandeController acc = loader.getController();
        
        acc.setIDpr(""+p.getId_produit());
        
        tv_pr_client.getScene().setRoot(root);
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void showImage(MouseEvent event) {
      Produit p = tv_pr_client.getSelectionModel().getSelectedItem();
      String path = p.getImage_path();
      grid_img.getChildren().clear();
      grid_img.add(new ImageView(new Image("file:/"+path, 193, 200, false, false)), 0, 0);
    }
    @FXML
    public void filteredSearch(){
        ProduitCRUD pc = new ProduitCRUD();
        List<Produit> listPr = pc.afficherProduits();
        ObservableList<Produit> list = FXCollections.observableArrayList(listPr);
        FilteredList<Produit> flProduit = new FilteredList(list,p -> true);
        
        txt_search.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (list_search.getValue()) {
                case "Nom Produit":
                    flProduit.setPredicate(p -> p.getNom_produit().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Marque Produit":
                    flProduit.setPredicate(p -> p.getMarque_produit().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Prix":
                    flProduit.setPredicate(p -> String.valueOf(p.getPrix()).contains(newValue.trim()));
                    break;
            }
            
        });
        tv_pr_client.setItems(flProduit);
        list_search.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
        {
            if(newVal!= null){
                txt_search.setText("");
            }
        });
    }
    @FXML
    private void interfaceMenuClient(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tv_pr_client.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packages.entities.Produit;
import packages.services.ProduitCRUD;


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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduits();
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
    
}

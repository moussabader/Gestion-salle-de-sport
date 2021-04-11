/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import packages.entities.Produit;
import packages.services.ProduitCRUD;


public class ListProduitController implements Initializable {

    @FXML
    private TableView<Produit> tv_produit;
    @FXML
    private TableColumn<Produit, Integer> col_id;
    @FXML
    private TableColumn<Produit, String> col_nom;
    @FXML
    private TableColumn<Produit, String> col_marque;
    @FXML
    private TableColumn<Produit, Integer> col_qte;
    @FXML
    private TableColumn<Produit, Double> col_prix;
    @FXML
    private TableColumn<Produit, String> col_img;
    @FXML
    private Button btn_ed_pr;
    @FXML
    private Button btn_list_cmd;
    @FXML
    private Button btn_add_pr;
    @FXML
    private Button btn_stats;
    @FXML
    private Button btn_del_pr;
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
        list_search.getItems().addAll("Nom Produit", "Marque Produit", "Quantité", "Prix");
        list_search.setValue("Nom Produit");
        txt_search.setPromptText("Rechercher ");

    }  
    
    /*public ObservableList<Produit> getProduits() {
        ObservableList<Produit> listPr = FXCollections.observableArrayList();
        ProduitCRUD pc = new ProduitCRUD();
        listPr = (ObservableList<Produit>) pc.afficherProduits();
        return listPr;
    }*/
    
    public void showProduits() {
        ProduitCRUD pc = new ProduitCRUD();
        List<Produit> listPr = pc.afficherProduits();
        ObservableList<Produit> list = FXCollections.observableArrayList(listPr);     
        
        //col_id.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque_produit"));
        col_qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image_path"));
        
        tv_produit.setItems(list);
 
    }

    public void afficherInterfaceModif() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
        
        try {
        Parent root = loader.load();
        Produit p = tv_produit.getSelectionModel().getSelectedItem();  
        
        ModifierProduitController mpc = loader.getController();
        mpc.setEditNompr(p.getNom_produit());
        mpc.setEditMarquepr(p.getMarque_produit());
        mpc.setEditQtepr(p.getQuantite());
        mpc.setEditPrixpr(""+p.getPrix());
        mpc.setEditImgpr(p.getImage_path());
        mpc.setEditIDpr(""+p.getId_produit());
        
        tv_produit.getScene().setRoot(root);
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void afficherInterfaceAjout() {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));

        try {
            Parent root = loader.load();
            tv_produit.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerProduit () {
        ObservableList<Produit> lp = tv_produit.getSelectionModel().getSelectedItems();
        Produit p = tv_produit.getSelectionModel().getSelectedItem();
        ProduitCRUD pc = new ProduitCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Demande de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sûr de vouloir supprimer le produit " + p.getNom_produit());
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            pc.supprimerProduit(p.getId_produit());
            showProduits();
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("Le produit " + p.getNom_produit() + " a été supprimé");
            resAlert.showAndWait();
        } else {
            alert.close();
        }
    }
    @FXML
    private void showImage(MouseEvent event) {
      Produit p = tv_produit.getSelectionModel().getSelectedItem();
      String path = p.getImage_path();
      grid_img.getChildren().clear();
      grid_img.add(new ImageView(new Image("file:/"+path, 193, 200, false, false)), 0, 0);
    }
    public void afficherListCmd() {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommandeAdmin.fxml"));

        try {
            Parent root = loader.load();
            tv_produit.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void filteredSearch(){
        ProduitCRUD pc = new ProduitCRUD();
        List<Produit> listPr = pc.afficherProduits();
        ObservableList<Produit> list = FXCollections.observableArrayList(listPr);
        FilteredList<Produit> flProduit = new FilteredList(list,p -> true);
        //tv_produit.setItems(flProduit);
        txt_search.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (list_search.getValue()) {
                case "Nom Produit":
                    flProduit.setPredicate(p -> p.getNom_produit().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Marque Produit":
                    flProduit.setPredicate(p -> p.getMarque_produit().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Quantité":
                    flProduit.setPredicate(p -> String.valueOf(p.getQuantite()).contains(newValue.trim()));
                    break;
                case "Prix":
                    flProduit.setPredicate(p -> String.valueOf(p.getPrix()).contains(newValue.trim()));
                    break;
            }
            
        });
        tv_produit.setItems(flProduit);
        list_search.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
        {
            if(newVal!= null){
                txt_search.setText("");
            }
        });
    }
    public void showStats() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiquesProduit.fxml"));

        
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tv_produit.getScene().setRoot(root);

        
        
    }
    @FXML
    private void interfaceMenuAdmin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            tv_produit.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}



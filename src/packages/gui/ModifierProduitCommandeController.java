/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import packages.entities.Produit;
import packages.services.ProduitCRUD;


public class ModifierProduitCommandeController implements Initializable {

    @FXML
    private TableView<Produit> tv_editprcmd;
    @FXML
    private TableColumn<Produit, String> col_nom_prcmd;
    @FXML
    private TableColumn<Produit, String> col_marque_prcmd;
    @FXML
    private TableColumn<Produit, Double> col_prix_prcmd;
    @FXML
    private Button btn_choisir_prcmd;
    @FXML
    private DatePicker edited_date;
    @FXML
    private Spinner<Integer> edited_qte;
    @FXML
    private Spinner<Integer> edited_qte_old;
    @FXML
    private TextField edited_idcmd;
    @FXML
    private TextField edited_idpr_old;
    @FXML
    private TextField edited_nompr;
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
        col_nom_prcmd.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        col_marque_prcmd.setCellValueFactory(new PropertyValueFactory<>("marque_produit"));
        col_prix_prcmd.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        tv_editprcmd.setItems(list);
    }
    
    @FXML
    public void afficherInterfaceModif() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommande.fxml"));
        
        try {
        Parent root = loader.load();
        Produit p = tv_editprcmd.getSelectionModel().getSelectedItem();  
        
        ModifierCommandeController mcc = loader.getController();
        mcc.setEditPrCmd(""+p.getId_produit());
        mcc.setEditDateCmd(edited_date.getValue().toString());
        mcc.setEditQtepr(edited_qte.getValue());
        mcc.setEditIdCmd(edited_idcmd.getText());
        mcc.setEditQteprOld(edited_qte_old.getValue());
        mcc.setEditPrCmdOld(edited_idpr_old.getText());
        mcc.setNomPr(""+p.getNom_produit());
        
        tv_editprcmd.getScene().setRoot(root);
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void setEditedDate(LocalDate date){
        this.edited_date.setValue(date);
    }
    public void setEditedQte(int i) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,i,1);
        this.edited_qte.setValueFactory(svf);
    }
    public void setEditedQteOld(int i) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,i,1);
        this.edited_qte_old.setValueFactory(svf);
    }
    public void setEditedIdCmd(String msg){
        this.edited_idcmd.setText(msg);
    }
    public void setEditedIdPrOld(String msg){
        this.edited_idpr_old.setText(msg);
    }
    public void setEditedNomPr(String msg){
        this.edited_nompr.setText(msg);
    }
    @FXML
    private void showImage(MouseEvent event) {
      Produit p = tv_editprcmd.getSelectionModel().getSelectedItem();
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
        tv_editprcmd.setItems(flProduit);
        list_search.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
        {
            if(newVal!= null){
                txt_search.setText("");
            }
        });
    }
}

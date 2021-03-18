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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;
import packages.services.CommandeCRUD;
import packages.services.ProduitCRUD;
import packages.tools.MyConnection;


public class ListCommandeClientController implements Initializable {

    @FXML
    private TableView<Commande> tv_cmd_client;
    @FXML
    private TableColumn<Commande, Integer> col_datecmd;
    @FXML
    private TableColumn<Commande, String> col_idcmd;
    @FXML
    private TableColumn<Commande, Double> col_mtcmd;
    @FXML
    private TableView<LigneCommande> tv_lc_client;
    @FXML
    private TableColumn<LigneCommande, Integer> col_idcmd_lc;
    @FXML
    private TableColumn<LigneCommande, Integer> col_idpr_lc;
    @FXML
    private TableColumn<LigneCommande, String> col_nompr_lc;
    @FXML
    private TableColumn<LigneCommande, Integer> col_qtecmd_lc;
    @FXML
    private Button btn_edit_cmd;
    @FXML
    private Button btn_listpr;
    @FXML
    private Button btn_del_cmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCommandes();
    }
    
    public void showCommandes() {
        CommandeCRUD cc = new CommandeCRUD();
        List<Commande> listcmd = cc.afficherCommandes();
        ObservableList<Commande> list = FXCollections.observableArrayList(listcmd);   
        
        col_idcmd.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_datecmd.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        col_mtcmd.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tv_cmd_client.setItems(list);
    }
    
    public void showCommandeDetails() {

        Commande c = tv_cmd_client.getSelectionModel().getSelectedItem();
        int idc = c.getId_commande();
        CommandeCRUD cc = new CommandeCRUD();
        List<LigneCommande> listlc= cc .afficherProduitCommandes(idc);
        ObservableList<LigneCommande> list = FXCollections.observableArrayList(listlc);
        
        col_idcmd_lc.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_idpr_lc.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nompr_lc.setCellValueFactory(new PropertyValueFactory<>("nom_produit") );
        col_qtecmd_lc.setCellValueFactory(new PropertyValueFactory<>("quantite_commande") );     
        tv_lc_client.setItems(list);
        
    }
    
    public void afficherListeProduits() {
        
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduitClient.fxml"));
        try {
            Parent root = loader.load();
            tv_cmd_client.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    
    public void afficherInterfaceModif(){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommande.fxml"));
        
        try {
        Parent root = loader.load();
        Commande c = tv_cmd_client.getSelectionModel().getSelectedItem();
        
        List<LigneCommande> lignescommandesListe = new ArrayList<>();
        LigneCommande lc = new LigneCommande();
        try {
            String req = "SELECT * FROM lignecommande WHERE id_commande="+String.valueOf(c.getId_commande());
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                
                lc.setQuantite_commande(rs.getInt("quantite_commande"));
                lc.setId_commande(rs.getInt("id_commande"));
                lc.setId_produit(rs.getInt("id_produit"));
                lc.setNom_produit(rs.getString("nom_produit"));
                lignescommandesListe.add(lc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        int qte = lc.getQuantite_commande();
        int idc = lc.getId_commande();
        int idp = lc.getId_produit();
        String nomp = lc.getNom_produit();
        
        ModifierCommandeController mcc = loader.getController();
        mcc.setEditDateCmd(c.getDate_commande());
        mcc.setEditQtepr(qte);
        mcc.setEditQteprOld(qte);
        mcc.setEditIdCmd(""+idc);
        mcc.setEditPrCmd(""+idp);
        mcc.setEditPrCmdOld(""+idp);
        mcc.setNomPr(nomp);
        
        tv_cmd_client.getScene().setRoot(root);

        
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerCommande(){
        
        ObservableList<Commande> lc = tv_cmd_client.getSelectionModel().getSelectedItems();
        Commande c = tv_cmd_client.getSelectionModel().getSelectedItem();
        CommandeCRUD cc = new CommandeCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Demande de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sûr de vouloir supprimer la commande ");
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            cc.supprimerCommande(c.getId_commande());
            //cc.supprimerProduitCommande(c.getId_commande());
            showCommandes();
            tv_lc_client.refresh();
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("La commande a été supprimé");
            resAlert.showAndWait();
        } else {
            alert.close();
        }
    }
    
}

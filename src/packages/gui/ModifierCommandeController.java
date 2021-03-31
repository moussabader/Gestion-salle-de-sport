/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;
import packages.services.CommandeCRUD;
import packages.tools.MyConnection;


public class ModifierCommandeController implements Initializable {

    @FXML
    private Button btn_edit_prcmd;
    @FXML
    private TextField edit_prcmd;
    @FXML
    private TextField edit_prcmd_old;
    @FXML
    private DatePicker edit_datecmd;
    @FXML
    private Spinner<Integer> edit_qtecmd;
    @FXML
    private Spinner<Integer> edit_qtecmd_old;
    @FXML
    private Button btn_valider_edit;
    @FXML
    private TextField edit_id_cmd;
    @FXML
    private TextField nom_pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setEditDateCmd(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(msg, formatter);
        this.edit_datecmd.setValue(localDate);
    }
    public void setEditQtepr(int i) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,i,1);
        this.edit_qtecmd.setValueFactory(svf);
    }
    public void setEditQteprOld(int i) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,i,1);
        this.edit_qtecmd_old.setValueFactory(svf);
    }
    public void setEditPrCmd(String msg){
        this.edit_prcmd.setText(msg);
    }
    public void setEditPrCmdOld(String msg){
        this.edit_prcmd_old.setText(msg);
    }
    public void setEditIdCmd(String msg) {
        this.edit_id_cmd.setText(msg);
    }
    public void setNomPr(String msg){
        this.nom_pr.setText(msg);
    }
    
    @FXML
    public void afficherInterfaceEditPrCmd(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitCommande.fxml"));

        try {
            Parent root = loader.load();
            ModifierProduitCommandeController mpcc = loader.getController();
            mpcc.setEditedDate(edit_datecmd.getValue());
            mpcc.setEditedQte(edit_qtecmd.getValue());
            mpcc.setEditedIdCmd(edit_id_cmd.getText());
            mpcc.setEditedQteOld(edit_qtecmd_old.getValue());
            mpcc.setEditedIdPrOld(edit_prcmd_old.getText());
            mpcc.setEditedNomPr(nom_pr.getText());
            
            edit_datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierCommande() {

        try {
            
            String date = edit_datecmd.getValue().toString();
            int qte = edit_qtecmd.getValue();
            int qte_old = edit_qtecmd_old.getValue();
            int id_pr = Integer.parseInt(edit_prcmd.getText());
            int idp_old = Integer.parseInt(edit_prcmd_old.getText());
            int idc = Integer.parseInt(edit_id_cmd.getText());
            
            //récupérer la quantité actuelle du produit
            List<Produit> lp = new ArrayList<>();
            try {
                String req1 = "SELECT quantite FROM produit WHERE id_produit=" + id_pr;
                Statement st1 = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st1.executeQuery(req1);
                Produit p = new Produit();
                while (rs.next()) {
                    p.setQuantite(rs.getInt("quantite"));
                    lp.add(p);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            int qtep = lp.get(0).getQuantite();
            //Test quantité et modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
            Parent root = loader.load();
            AjouterCommandeController acc = loader.getController();
            
            if (acc.verifQuantite(qte, id_pr)) {
                
                Commande c = new Commande(date);
                LigneCommande lc = new LigneCommande(qte);
                CommandeCRUD cc = new CommandeCRUD();
                
                cc.modifierCommande(c, idc);
                cc.modifierProduitCommande(id_pr, lc, idc);
                cc.updateQuantite(qte, id_pr);
                cc.updateQuantiteOld(qte_old, idp_old);
                
                try {
                    String req3 = "UPDATE commande SET montant=? WHERE id_commande=?";
                    PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(req3);
                    CommandeCRUD ccd = new CommandeCRUD();
                    double mt = ccd.calculerMontant(idc, id_pr);
                    pst3.setDouble(1, mt);
                    pst3.setInt(2, idc);
                    pst3.executeUpdate();
                    System.out.println("montant mise à jour !");
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès!");
                alert.setHeaderText(null);
                alert.setContentText("La commande a été modifiée avec succès");
                alert.showAndWait();
                afficherListeCommandes();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La quantité disponible de ce produit est " + qtep + " uniquement!");
                alert.showAndWait();
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void afficherListeCommandes(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommandeClient.fxml"));

        try {
            Parent root = loader.load();
            edit_datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

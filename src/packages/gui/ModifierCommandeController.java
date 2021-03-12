/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.services.CommandeCRUD;


public class ModifierCommandeController implements Initializable {

    @FXML
    private Button btn_edit_prcmd;
    @FXML
    private TextField edit_prcmd;
    @FXML
    private DatePicker edit_datecmd;
    @FXML
    private Spinner<Integer> edit_qtecmd;
    @FXML
    private Button btn_valider_edit;
    @FXML
    private TextField edit_id_cmd;

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
    public void setEditPrCmd(String msg){
        this.edit_prcmd.setText(msg);
    }
    public void setEditIdCmd(String msg) {
        this.edit_id_cmd.setText(msg);
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
            edit_datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierCommande() {
        
        String date = edit_datecmd.getValue().toString();
        int qte = edit_qtecmd.getValue();
        int id_pr = Integer.parseInt(edit_prcmd.getText());
        int idc = Integer.parseInt(edit_id_cmd.getText());
        
        Commande c = new Commande(date);
        LigneCommande lc = new LigneCommande(qte);
        CommandeCRUD cc = new CommandeCRUD();
        
        cc.modifierCommande(c, idc);
        cc.modifierProduitCommande(id_pr, lc, idc);
        
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

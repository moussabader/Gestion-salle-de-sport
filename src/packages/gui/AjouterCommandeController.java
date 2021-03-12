/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import com.sun.prism.shader.Solid_ImagePattern_Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.services.CommandeCRUD;
import packages.tools.MyConnection;


public class AjouterCommandeController implements Initializable {

    @FXML
    private DatePicker datecmd;
    @FXML
    private Spinner<Integer> qtecmd;
    @FXML
    private Button btn_listcmd;
    @FXML
    private Button btn_ajoutcmd;
    @FXML
    private Button btn_listpr;
    @FXML
    private TextField id_pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        qtecmd.setValueFactory(svf);
        datecmd.setValue(LocalDate.now());
    }
    
    public void setIDpr(String msg) {
        this.id_pr.setText(msg);
    }

    @FXML
    private void ajouterCommande(ActionEvent event) {
        

            String date_commande = datecmd.getValue().toString();
            int quantite_commande = qtecmd.getValue();

            Commande c = new Commande(date_commande);
            CommandeCRUD cc = new CommandeCRUD();
            cc.ajouterCommande(c);

            List<Commande> commandesListe = new ArrayList<>();
            try {
                String req = "SELECT * FROM commande";
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    Commande c1 = new Commande();
                    c1.setId_commande(rs.getInt("id_commande"));
                    commandesListe.add(c1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            int idc = commandesListe.get(commandesListe.size() - 1).getId_commande();

            LigneCommande lc = new LigneCommande(quantite_commande);
            lc.setId_produit(Integer.parseInt(id_pr.getText()));
            int idp = lc.getId_produit();

            cc.ajouterProduitCommande(idc, idp, lc);
            double mt = cc.calculerMontant(idc, idp);
            cc.updateMontant(mt, idc);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("La commande est ajouté avec succès");
            alert.showAndWait();
            afficherListeCommandes();
    }
    
    public void afficherListeProduits() {
        
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduitClient.fxml"));
        try {
            Parent root = loader.load();
            datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    public void afficherListeCommandes() {
        
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommandeClient.fxml"));
        try {
            Parent root = loader.load();
            datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    
}

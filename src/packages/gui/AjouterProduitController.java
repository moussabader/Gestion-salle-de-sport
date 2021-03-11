/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import packages.entities.Produit;
import packages.services.ProduitCRUD;



public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nompr;
    @FXML
    private TextField marquepr;
    @FXML
    private TextField prixpr;
    @FXML
    private TextField imgpr;
    @FXML
    private Button btnajouterpr;
    @FXML
    private Spinner<Integer> qtepr;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qtepr.setValueFactory(svf);  
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
        if (validateNumber() && validateFields()) {
            String nom_produit = nompr.getText();
            String marque_produit = marquepr.getText();
            int quantite_produit = Integer.parseInt(qtepr.getValue().toString());
            double prix_produit = Double.parseDouble(prixpr.getText());
            String imagepath = imgpr.getText();

            Produit p = new Produit(nom_produit, marque_produit, quantite_produit, prix_produit, imagepath);
            ProduitCRUD pc = new ProduitCRUD();
            pc.ajouterProduit(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Le produit "+nom_produit+" est ajouté avec succès");
            alert.showAndWait();
        } else if(validateNumber()==false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un prix unitaire valide");
            alert.showAndWait();
        } else if(validateFields()==false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }

    }
    
    public void afficherListeProduits() {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduit.fxml"));

        try {
            Parent root = loader.load();
            nompr.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        }
    //Test de validation de prix 
    private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(prixpr.getText());
        if(m.find() && m.group().equals(prixpr.getText())) {
            return true;
        } else {
            return false;
        }
    }
    // Test de validation de saisie
    private boolean validateFields(){
        if(nompr.getText().isEmpty() || marquepr.getText().isEmpty() || prixpr.getText().isEmpty() || imgpr.getText().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    
}

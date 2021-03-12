/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import packages.entities.Produit;
import packages.services.ProduitCRUD;


public class ModifierProduitController implements Initializable {

    @FXML
    private TextField edit_marquepr;
    @FXML
    private TextField edit_nompr;
    @FXML
    private TextField edit_prixpr;
    @FXML
    private TextField edit_imgpr;
    @FXML
    private Spinner<Integer> edit_qtepr;
    @FXML 
    private TextField edit_idpr;
    
    @FXML
    private Button btn_valider_edit;
    
    private List<String> listfiles;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listfiles = new ArrayList<>();
        listfiles.add("*.png");
        listfiles.add("*.jpg");
        
    }
    public void setEditIDpr(String msg){
        this.edit_idpr.setText(msg);
    }
    
    public void setEditNompr(String msg) {
        this.edit_nompr.setText(msg);
    }

    public void setEditMarquepr(String msg) {
        this.edit_marquepr.setText(msg);
    }

    public void setEditQtepr(int i) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,i,1);
        this.edit_qtepr.setValueFactory(svf);
    }

    public void setEditPrixpr(String msg) {
        this.edit_prixpr.setText(msg);
    }

    public void setEditImgpr(String msg) {
        this.edit_imgpr.setText(msg);
    }
    
    public void modifierProduit() {
        if (validateNumber() && validateFields()) {
        String nom = edit_nompr.getText();
        String marque = edit_marquepr.getText();
        int qte = edit_qtepr.getValue();
        double prix = Double.parseDouble(edit_prixpr.getText());
        String img = edit_imgpr.getText();
        int id = Integer.parseInt(edit_idpr.getText());
        
        Produit p = new Produit(nom,marque,qte,prix,img);
        ProduitCRUD pc= new ProduitCRUD();
        pc.modifierProduit(p,id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été modifié avec succès");
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
            edit_nompr.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        //Test de validation de prix 
    private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(edit_prixpr.getText());
        if(m.find() && m.group().equals(edit_prixpr.getText())) {
            return true;
        } else {
            return false;
        }
    }
    // Test de validation de saisie
    private boolean validateFields(){
        if(edit_nompr.getText().isEmpty() || edit_marquepr.getText().isEmpty() || edit_prixpr.getText().isEmpty() || edit_imgpr.getText().isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    
    public void fileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", listfiles));
        File f = fc.showOpenDialog(null);
        if(f != null) {
            edit_imgpr.setText(f.getAbsolutePath());
        }
    }
}

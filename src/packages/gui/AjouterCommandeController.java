/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.prism.shader.Solid_ImagePattern_Loader;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;
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
        //id de client saisie manuellement
        int idcl = 1;
        String date_commande = datecmd.getValue().toString();
        int quantite_commande = qtecmd.getValue();
        int idp = Integer.parseInt(id_pr.getText());
        //récupérer la quantité actuelle du produit
        List<Produit> lp = new ArrayList<>();
        LigneCommande lcmd = new LigneCommande();
        try {
            String req1 = "SELECT quantite,nom_produit FROM produit WHERE id_produit=" + idp;
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(req1);
            Produit p = new Produit();
            while (rs.next()) {
                p.setQuantite(rs.getInt("quantite"));
                p.setNom_produit(rs.getString("nom_produit"));
                lp.add(p);
            }
            String req2 ="SELECT nom_c,prenom_c FROM client WHERE id_c="+idcl;
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            while (rs2.next()) {
                String nom = rs2.getString("nom_c");
                String prenom = rs2.getString("prenom_c");
                lcmd.setNom_client(nom+" "+prenom);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String nomcl = lcmd.getNom_client();
        int qtep = lp.get(0).getQuantite();
        String nom = lp.get(0).getNom_produit();
        //test et ajout
        if (verifQuantite(quantite_commande, idp)) {
            
            Commande c = new Commande(date_commande);
            CommandeCRUD cc = new CommandeCRUD();
            cc.ajouterCommande(c,idcl);
            //récupérer l'id de commande ajouté
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
            cc.ajouterProduitCommande(idc, idp, lc, idcl);
            // MAJ du montant et du quantité
            double mt = cc.calculerMontant(idc, idp);
            cc.updateMontant(mt, idc);
            cc.updateQuantite(lc.getQuantite_commande(), idp);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("La commande est ajouté avec succès");
            alert.showAndWait();
            makeFile(date_commande,nom,quantite_commande,mt,idc,nomcl);
            afficherListeCommandes();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La quantité disponible de ce produit est " + qtep + " uniquement!");
            alert.showAndWait();
        }
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
    public boolean verifQuantite(int qtec, int idp){
        List<Produit> lp = new ArrayList<>();
        try {
            String req1 = "SELECT quantite FROM produit WHERE id_produit=" + idp;
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
        if (qtep < qtec) {
            return false;
        } else {
            return true;
        }

    }
    public void makeFile(String date, String nom, int qte, double mt, int idc, String nomcl) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDFont font = PDType1Font.HELVETICA;
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(font, 20);
            contentStream.newLineAtOffset(170, 700);
            contentStream.showText("BON DE COMMANDE N°" + idc);
            contentStream.newLineAtOffset(-140, -150);
            contentStream.setFont(font, 16);
            contentStream.showText("Date de commande : " + date);
            contentStream.newLineAtOffset(-10, -50);
            contentStream.showText("  Nom de Produit : " + nom);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("  Quantité comandée : " + qte);
            contentStream.newLineAtOffset(10, -50);
            contentStream.showText("Montant total : " + mt + " Dt");
            contentStream.endText();
            
            String code = "Commande N° " + String.valueOf(idc) + "\n Nom du Client : "+nomcl;
            BufferedImage buffer = generateEAN13BarcodeImage(code);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(buffer, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageInByte, "image");
            contentStream.drawImage(pdImage, 200, 30);
            contentStream.close();
            
            String home = System.getProperty("user.home");
            //System.out.println(home);
            document.save(home + "\\Desktop\\BON DE COMMANDE N°" + idc + ".pdf");
            document.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
  	
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        ByteMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    @FXML
    private void interfaceMenuClient(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));

        try {
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            datecmd.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.services;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;
import packages.interfaces.ICommande;
import packages.tools.MyConnection;


public class CommandeCRUD implements ICommande{

    @Override
    public void ajouterCommande(Commande c) {
        try{
        String req = "INSERT INTO commande (date_commande)" 
                + "VALUES (?)";
        
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
        
        String date = c.getDate_commande();
        Date sqldate = Date.valueOf(date);
        pst.setDate(1, sqldate);
        pst.executeUpdate();
        System.out.println("Commande ajoutée");
        
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void modifierCommande(Commande c, int i) {
        try {
            String req = "UPDATE commande SET date_commande=?"
                    + " WHERE id_commande=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            String date = c.getDate_commande();
            Date sqldate = Date.valueOf(date);
            pst.setDate(1, sqldate);
            pst.setInt(2, i);

            pst.executeUpdate();
            System.out.println("Commande modifiée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterProduitCommande(String date, int idp, LigneCommande lc) {
    try{
        Date sqldate = Date.valueOf(date);
        String req = "SELECT id_commande FROM commande WHERE date_commande="+sqldate;
        Statement st = MyConnection.getInstance().getCnx().prepareStatement(req);
        ResultSet res = st.executeQuery(req);
     
        String req2 = "INSERT INTO lignecommande (id_commande,id_produit,quantite_commande)" 
                + "VALUES (?,?,?)";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req2);
        
        while(res.next()){
        pst.setInt(1,res.getInt("id_commande"));
        }
        pst.setInt(2,idp);
        pst.setInt(3,lc.getQuantite_commande());

        pst.executeUpdate();
        System.out.println("Ligne Commande ajoutée");
        
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierProduitCommande(Produit p,LigneCommande lc, int i) {
        try {
            String req = "UPDATE lignecommande SET quantite_commande=?, id_produit=? "
                    + " WHERE id_commande=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1,lc.getQuantite_commande());
            pst.setInt(2,p.getId_produit());
            pst.setInt(3, i);

            pst.executeUpdate();
            System.out.println("Ligne Commande modifiée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCommande(int i) {
        try {
            String req = "DELETE FROM commande where id_commande=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, i);
            int res = pst.executeUpdate();
            if (res == 0) {
                System.out.println("Commande introuvable!");
            } else {
                System.out.println("Commande supprimée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProduitCommande(int i) {
        try {
            String req = "DELETE FROM lignecommande where id_commande=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, i);
            int res = pst.executeUpdate();
            if (res == 0) {
                System.out.println("Ligne Commande introuvable!");
            } else {
                System.out.println("Ligne Commande supprimée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> afficherCommandes() {
    List<Commande> commandesListe = new ArrayList<>();
        try {
            String req = "SELECT * FROM commande";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                Commande c = new Commande();
                c.setId_commande(rs.getInt("id_commande"));
                Date date = rs.getDate("date_commande");
                c.setDate_commande(date.toString());
                commandesListe.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandesListe;
    }

    @Override
    public List<LigneCommande> afficherProduitCommandes() {
    List<LigneCommande> lignescommandesListe = new ArrayList<>();
        try {
            String req = "SELECT * FROM lignecommande";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                LigneCommande lc = new LigneCommande();
                lc.setQuantite_commande(rs.getInt("quantite_commande"));
                lignescommandesListe.add(lc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lignescommandesListe;
    }
}


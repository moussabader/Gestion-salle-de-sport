/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.services;

import java.sql.SQLException;
import java.util.List;
import packages.entities.Produit;
import packages.interfaces.IProduit;
import packages.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ProduitCRUD implements IProduit{

    
    
    @Override
    public void ajouterProduit(Produit t) {
        
        try{
        String req = "INSERT INTO produit (nom_produit,marque_produit,quantite,prix,image_path)" 
                + "VALUES (?,?,?,?,?)";
        
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
        
        pst.setString(1, t.getNom_produit());
        pst.setString(2,t.getMarque_produit());
        pst.setInt(3,t.getQuantite());
        pst.setDouble(4,t.getPrix());
        pst.setString(5,t.getImage_path());
       
        pst.executeUpdate();
        System.out.println("Produit ajouté");
        
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierProduit(Produit t, int id) {
        try {
            String req = "UPDATE produit SET nom_produit=?,marque_produit=?,quantite=?,prix=?,image_path=?"
                    + " WHERE id_produit=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            
            pst.setString(1, t.getNom_produit());
            pst.setString(2,t.getMarque_produit());
            pst.setInt(3,t.getQuantite());
            pst.setDouble(4,t.getPrix());
            pst.setString(5,t.getImage_path());
            pst.setInt(6,id);
            
            pst.executeUpdate();
            System.out.println("Produit modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProduit(int id) {
        try {
            String req = "DELETE FROM produit where id_produit=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1,id);
            int res = pst.executeUpdate();
                if (res==0) {
                    System.out.println("produit introuvable!");
                }else{
                    System.out.println("Produit supprimé");
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> afficherProduits() {
        List<Produit> produitsList = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                Produit p = new Produit();
                p.setId_produit(rs.getInt("id_produit"));
                p.setNom_produit(rs.getString("nom_produit"));
                p.setMarque_produit(rs.getString("marque_produit"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix(rs.getDouble("prix"));
                p.setImage_path(rs.getString("image_path"));
                produitsList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return produitsList;
    }

    /*@Override
    public void modifierProduit(Produit t) {
        try {
            String req = "UPDATE produit SET nom_produit=?,marque_produit=?,quantite=?,prix=?,image_path=?"
                    + " WHERE id_produit=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setString(1, t.getNom_produit());
            pst.setString(2,t.getMarque_produit());
            pst.setInt(3,t.getQuantite());
            pst.setInt(4,t.getPrix());
            pst.setString(5,t.getImage_path());
            pst.setInt(6,t.getId_produit());
            
            pst.executeUpdate();
            System.out.println("Produit modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
}


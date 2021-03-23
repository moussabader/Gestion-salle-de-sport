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
    public void ajouterProduitCommande(int idc, int idp, LigneCommande lc) {
    
        List<Produit> lp = new ArrayList<>();
        try {
            /*Date sqldate = Date.valueOf(date);
        String req = "SELECT id_commande FROM commande WHERE date_commande="+sqldate;
        Statement st = MyConnection.getInstance().getCnx().prepareStatement(req);
        ResultSet res = st.executeQuery(req);*/
            String req1 = "SELECT nom_produit FROM produit WHERE id_produit=" + idp;
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(req1);
            Produit p = new Produit();
            while (rs.next()) {
                p.setNom_produit(rs.getString("nom_produit"));
                lp.add(p);
            }
            String nomp = lp.get(0).getNom_produit();
            String req2 = "INSERT INTO lignecommande (id_produit,quantite_commande,id_commande,nom_produit)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req2);
            pst.setInt(1, idp);
            pst.setInt(2, lc.getQuantite_commande());
            pst.setInt(3, idc);
            pst.setString(4, nomp);
            pst.executeUpdate();
            System.out.println("Ligne Commande ajoutée");
            String req3 = "UPDATE produit SET quantite_commande=quantite_commande+? WHERE nom_produit=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(req3);
            pst2.setInt(1, lc.getQuantite_commande());
            pst2.setString(2, nomp);
            pst2.executeUpdate();
            /*String req3 = "UPDATE commande SET montant=? WHERE id_commande=?";
            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(req3);
            CommandeCRUD ccd=new CommandeCRUD();
            double mt=ccd.calculerMontant(idc,idp);
            pst3.setDouble(1, mt);
            pst3.setInt(2, idc);
            pst3.executeUpdate();
             */
            /*while(res.next()){
            pst.setInt(1,res.getInt("id_commande"));
            }*/
            //System.out.println("montant mise à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierProduitCommande(int idp,LigneCommande lc, int idc) {
        List<Produit> lp = new ArrayList<>();
        try {
            String req1 = "SELECT nom_produit FROM produit WHERE id_produit=" + idp;
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(req1);
            Produit p = new Produit();
            while (rs.next()) {
                p.setNom_produit(rs.getString("nom_produit"));
                lp.add(p);
            }
            String nomp = lp.get(0).getNom_produit();
            String req = "UPDATE lignecommande SET quantite_commande=?, id_produit=?, nom_produit=? "
                    + " WHERE id_commande=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1, lc.getQuantite_commande());
            pst.setInt(2, idp);
            pst.setString(3, nomp);
            pst.setInt(4, idc);

            /* String req3 = "UPDATE commande SET montant=? WHERE id_commande=?";
            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(req3);
            CommandeCRUD ccd = new CommandeCRUD();
            double mt = ccd.calculerMontant(idc, idp);
            pst3.setDouble(1, mt);
            pst3.setInt(2, idc);
            pst3.executeUpdate();*/
            pst.executeUpdate();
            System.out.println("Ligne Commande modifiée!");
            //System.out.println("montant mise à jour !");
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
                c.setMontant(rs.getDouble("montant"));
                commandesListe.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandesListe;
    }

    @Override
    public List<LigneCommande> afficherProduitCommandes(int idc) {
    List<LigneCommande> lignescommandesListe = new ArrayList<>();
        try {
            String req = "SELECT * FROM lignecommande WHERE id_commande="+String.valueOf(idc);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                LigneCommande lc = new LigneCommande();
                lc.setQuantite_commande(rs.getInt("quantite_commande"));
                lc.setId_commande(rs.getInt("id_commande"));
                lc.setId_produit(rs.getInt("id_produit"));
                lc.setNom_produit(rs.getString("nom_produit"));
                lignescommandesListe.add(lc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lignescommandesListe;
    }

    @Override
    public double calculerMontant(int idc,int idp) {
        int qte=0;
        double pu=0;
        try {
            String req = "SELECT * FROM lignecommande";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next()){
                LigneCommande lc=new LigneCommande();
                lc.setId_commande(rs.getInt("id_commande"));
                lc.setId_produit(rs.getInt("id_produit"));
                lc.setQuantite_commande(rs.getInt("quantite_commande"));
                if (lc.getId_commande() == idc && lc.getId_produit() == idp) {
                    qte = lc.getQuantite_commande();
                }
            }
            String req2 = "SELECT * FROM produit";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs2 =  st2.executeQuery(req2);
                while (rs2.next()) {                    
                    Produit p = new Produit();
                    p.setId_produit(rs2.getInt("id_produit"));
                    p.setPrix(rs2.getDouble("prix"));
                    if (p.getId_produit()==idp) {
                        pu=p.getPrix();
                    }
                }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return qte*pu;
    }

    @Override
    public void updateMontant(double mt,int idc) {
        try {
            String req1 = "SELECT montant FROM commande WHERE id_commande="+idc;
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(req1);
            Commande c = new Commande();
            while (rs.next()) {
            c.setMontant(rs.getDouble("montant"));    
            }
        String req = "UPDATE commande SET montant=?+? WHERE id_commande=?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
        //CommandeCRUD ccd=new CommandeCRUD();
        //double mt1=ccd.calculerMontant(0,0);
        pst.setDouble(1,c.getMontant() );
        pst.setDouble(2, mt);
        pst.setInt(3, idc);
        pst.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void updateQuantite(int qte,int idp) {
        
        try {
         
            String req = "UPDATE produit SET quantite=quantite-? WHERE id_produit=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setDouble(1, qte);
            pst.setInt(2, idp);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateQuantiteOld(int qte, int idp) {
        try {
         
            String req = "UPDATE produit SET quantite=quantite+? WHERE id_produit=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setDouble(1, qte);
            pst.setInt(2, idp);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


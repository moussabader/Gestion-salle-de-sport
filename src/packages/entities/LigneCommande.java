/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.entities;


public class LigneCommande {

    private int quantite_commande;
    private int id_produit;
    private int id_commande;
    private String nom_produit;
    private String nom_client;

    

    public LigneCommande() {
    }

    public LigneCommande(int quantite_commande) {
        this.quantite_commande = quantite_commande;
    }

    public int getQuantite_commande() {
        return quantite_commande;
    }

    public void setQuantite_commande(int quantite_commande) {
        this.quantite_commande = quantite_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }
    
    
    
}

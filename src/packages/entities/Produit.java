/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.entities;

import packages.services.ProduitCRUD;


public class Produit {
    private static int count;
    private int id_produit;
    private String nom_produit;
    private String marque_produit;
    private int quantite;
    private double prix;
    private String image_path ;

    public Produit() {
        this.id_produit = ++count;
        count=id_produit;
        
    }

    public Produit(String nom_produit, String marque_produit, int quantite, double prix, String image_path) {

        this.nom_produit = nom_produit;
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix = prix;
        this.image_path = image_path;
    }

    public Produit(int idproduit, String nomproduit) {
        this.id_produit = idproduit;
        this.nom_produit = nomproduit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getMarque_produit() {
        return marque_produit;
    }

    public void setMarque_produit(String marque_produit) {
        this.marque_produit = marque_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", marque_produit=" + marque_produit + ", quantite=" + quantite + ", prix=" + prix + ", image_path=" + image_path + '}';
    }
    
    
    
}

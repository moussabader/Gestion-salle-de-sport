/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

/**
 *
 * @author 21692
 */
public class Avis {
    
    
  int  idavis;
private String titre ;
private String commentaire ;
 private String date ;
 private String  niveau; 
  private int id_produit ;
  private String nom_produit;
  private int id_c;

    public Avis(int idavis, String titre, String commentaire, String date, String niveau, int id_produit, int id_c) {
        this.idavis = idavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
        this.id_produit = id_produit;
        this.id_c = id_c;
    }

    public Avis() {
    }

    public Avis(int idavis, String titre, String commentaire, String date, String niveau) {
        this.idavis = idavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
    }

    public Avis(String titre, String commentaire, String date, String niveau, int idproduit, String nomproduit) {
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
        this.id_produit = idproduit;
        this.nom_produit = nomproduit;
    }

    public Avis(int idavis, String titre, String commentaire, String niveau) {
        this.idavis = idavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.niveau = niveau;
    }

    public Avis(int idavis) {
        this.idavis = idavis;
    }

    public Avis(int idavis, String titre, String commentaire, String date, String niveau, int id_produit, String nom_produit) {
        this.idavis = idavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
    }

    public int getIdavis() {
        return idavis;
    }

    public String getTitre() {
        return titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getDate() {
        return date;
    }

    public String getNiveau() {
        return niveau;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public int getId_c() {
        return id_c;
    }

    public Avis( String titre, String commentaire, String date, String niveau, int id_produit, String nom_produit, int id_c) {
        
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.id_c = id_c;
    }

    public Avis(String titre, String commentaire, String date, String niveau, String nomproduit) {
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.niveau = niveau;
        this.nom_produit = nomproduit;
    }

    public Avis(int idavis, String titre, String commentaire, String date, int id_produit) {
        this.idavis = idavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date = date;
        this.id_produit = id_produit;
    }

    public String getNomproduit() {
        return nom_produit;
    }

    public void setId_avis(int idavis) {
        this.idavis = idavis;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }
  
  
  
  
    
}

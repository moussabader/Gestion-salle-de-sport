/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.interfaces;

import java.util.List;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;


public interface ICommande {
    
    public void ajouterCommande(Commande c);
    public void ajouterProduitCommande(int idc, int idp, LigneCommande lc);
    public void modifierCommande(Commande c, int i);
    public void modifierProduitCommande(Produit p,LigneCommande lc, int i);
    public void supprimerCommande(int i);
    public void supprimerProduitCommande(int i);
    public List<Commande> afficherCommandes();
    public List<LigneCommande> afficherProduitCommandes();
    public double calculerMontant (int idc , int idp);
    public void updateMontant(double mt,int idc);
}
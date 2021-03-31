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
    
    public void ajouterCommande(Commande c, int idcl);
    public void ajouterProduitCommande(int idc, int idp, LigneCommande lc, int idcl);
    public void modifierCommande(Commande c, int i);
    public void modifierProduitCommande(int idp,LigneCommande lc, int i);
    public void supprimerCommande(int i);
    public void supprimerProduitCommande(int i);
    public List<Commande> afficherCommandes();
    public List<Commande> afficherCommandesClient(int idcl);
    public List<LigneCommande> afficherProduitCommandes(int idc);
    public double calculerMontant (int idc , int idp);
    public void updateMontant(double mt,int idc);
    public void updateQuantite(int q,int idp);
    public void updateQuantiteOld(int q,int idp);
}

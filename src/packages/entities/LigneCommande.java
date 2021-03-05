/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.entities;


public class LigneCommande {

    private int quantite_commande;
    

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
    
    
}

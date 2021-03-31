/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.interfaces;

import java.util.List;
import packages.entities.Produit;


public interface IProduit {
    public  void ajouterProduit(Produit p);
    public  void modifierProduit(Produit p, int i);
    public  void supprimerProduit(int i);
    public List<Produit> afficherProduits();
    
}

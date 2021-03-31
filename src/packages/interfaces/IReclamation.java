/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.interfaces;

import packages.entities.Coach;
import java.util.List;

/**
 *
 * @author 21692
 */
public interface IReclamation <R> {
    public void ajouterReclamtion(R r);
    public void supprimerReclamation(R r);
    public void consulterReclamation(R r);
    public List<R> afficheReclamation();
    
    
}

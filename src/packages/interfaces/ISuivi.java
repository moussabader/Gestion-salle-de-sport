/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.interfaces;


import packages.entities.Suivi;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ISuivi {
    public void AddSuivi(Suivi s);
    public List<Suivi>AfficherSuivi() throws SQLException;
    public void updateSuivi(int id, Suivi s);
    public void deleteSuivi(int id);
    
}

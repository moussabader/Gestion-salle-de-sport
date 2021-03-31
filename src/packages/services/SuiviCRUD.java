/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;

import packages.entities.Reservation;
import packages.entities.Suivi;
import packages.interfaces.ISuivi;
import packages.tools.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class SuiviCRUD implements ISuivi {
    
    Connection cnx ;
     public SuiviCRUD() {
        cnx= MyConnection.getInstance().getCnx();
    }

    @Override
    public void AddSuivi (Suivi s) {
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="insert into suivi(id_suivi,nom_coach,nom_client,objectifs,conseils) values("+s.getId_suivi()+",'"+s.getNom_coach()+"','"+s.getNom_client()+"','"+s.getObjectifs()+"','"+s.getConseils()+"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Suivi> AfficherSuivi() throws SQLException {
        Statement stm=cnx.createStatement();
            String query="select * from `suivi` ";
            
            ResultSet  rst=stm.executeQuery(query);
            
            ObservableList<Suivi>Suivis= FXCollections.observableArrayList();
            
            while(rst.next())
            {
                Suivi s = new Suivi() ;
                s.setId_suivi(rst.getInt("id_suivi"));
                s.setNom_coach(rst.getString("nom_coach"));
                s.setNom_client(rst.getString("nom_client"));
                s.setObjectifs(rst.getString("objectifs"));
                s.setConseils(rst.getString("conseils"));
                Suivis.add(s);
            }
      
        return Suivis;
    }

    @Override
    public void updateSuivi(int id, Suivi s) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "update suivi set nom_coach = '" + s.getNom_coach()+ "', nom_client= '" + s.getNom_client()+ "', objectifs = '" + s.getObjectifs()+ "', conseils = '" + s.getConseils()+ "' where id_suivi= " + id + "";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteSuivi(int id) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM suivi where id_suivi=" + id + "";
            stm.executeUpdate(query);
            System.out.println("Suivi Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Coach;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiheb
 */
public class ServiceCoach {
Connection cnx;
    private PreparedStatement ste ;

    public ServiceCoach() {
    cnx =MyConnection.getInstance().getConnection();        
    }
    
    
    public void ajouterc(Coach c) {
       String req ="INSERT INTO coach (nom_co, prenom_co, genre_co, login_co ,mdp_co) VALUES (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            
            
            ste.setString(1,c.getNom_co());
            ste.setString(2,c.getPrenom_co());
	    ste.setString(3,c.getGenre_co());
            ste.setString(4,c.getLogin_co());
            ste.setString(5,c.getMdp_co());
            ste.executeUpdate();
            System.out.println("Coach ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
      }
    }

   public List<Coach> ListClasse() {
        List<Coach> Mylist = new ArrayList<>();
        try {
            String requete = "select * from coach";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Coach pre = new Coach();
              
            pre.setId_co(e.getInt("id_co"));
            pre.setNom_co(e.getString("nom_co"));
            pre.setPrenom_co(e.getString("prenom_co"));
            pre.setGenre_co(e.getString("genre_co"));
            pre.setLogin_co(e.getString("login_co"));
            pre.setMdp_co(e.getString("mdp_co"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
   
   
    
    
    public void supprimerc (Coach c ) {
    String requete = "DELETE FROM coach where nom_co =?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom_co());
            pst.executeUpdate();
            System.out.println("coach Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (Coach c) {
    
        try {
            String requete = "update coach set prenom_co =?, genre_co =? , login_co =? , mdp_co =? where ? = nom_co";
            PreparedStatement pre = cnx.prepareStatement(requete);

            pre.setString(1, c.getPrenom_co());
            pre.setString(2, c.getGenre_co());
            pre.setString(3, c.getLogin_co());
            pre.setString(4, c.getMdp_co());
            pre.setString(5,c.getNom_co());

            pre.executeUpdate();
            System.out.println("Coach Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
        
    



















}

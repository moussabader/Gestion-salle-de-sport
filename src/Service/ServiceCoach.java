/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Client;
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
    String requete = "DELETE FROM coach where id_co =?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_co());
            pst.executeUpdate();
            System.out.println("coach Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (Coach c) {
    
        try {
            String requete = "update coach set  nom_co=? ,prenom_co =?, genre_co =? , login_co =? , mdp_co =? where ? = id_co";
            PreparedStatement pre = cnx.prepareStatement(requete);
            
            
            pre.setString(1,c.getNom_co());
            pre.setString(2, c.getPrenom_co());
            pre.setString(3, c.getGenre_co());
            pre.setString(4, c.getLogin_co());
            pre.setString(5, c.getMdp_co());
            pre.setInt(6,c.getId_co());

            pre.executeUpdate();
            System.out.println("Coach Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void modifiermdp (Coach c) {
    
        try {
            String requete = "update coach set mdp_co =? where ? = nom_co";
            PreparedStatement pre = cnx.prepareStatement(requete);

            
            pre.setString(1, c.getMdp_co());
            pre.setString(2,c.getNom_co());

            pre.executeUpdate();
            System.out.println("Client Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public Coach select(int c ){
        
    Coach cl = new Coach() ; 
    try {
            String requete = "select * from coach where ?= id_co";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c);
            ResultSet e = pst.executeQuery();
            while(e.next()){
            Coach pre = new Coach() ; 
            
            pre.setId_co(e.getInt("id_co"));
            pre.setNom_co(e.getString("nom_co"));
            pre.setPrenom_co(e.getString("prenom_co"));
            pre.setGenre_co(e.getString("genre_co"));
            pre.setLogin_co(e.getString("login_co"));
            pre.setMdp_co(e.getString("mdp_co"));
                        
             
            
             cl=pre;   }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
return cl ; 

}
     public void modifierco (Coach c) {
    
        try {
            String requete = "update coach set nom_co=?, prenom_co =? ,genre_co =?  where ? = id_co";
            PreparedStatement pre = cnx.prepareStatement(requete);

            pre.setInt(4, c.getId_co());
            pre.setString(1,c.getNom_co());
            pre.setString(2, c.getPrenom_co());
            
            pre.setString(3, c.getGenre_co());
            
            
           

            pre.executeUpdate();
            System.out.println("Coach Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



 public boolean verif (String c ) {  
    
    boolean verif = true ; 
        try {
           String requete = "select * from coach where ?= nom_co";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c);
            ResultSet e = pst.executeQuery();
            
            if(e.next()){ 
                verif= true ; 
                
            }else
                verif=false ; 
       
   
    }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    
    
   return verif ; 
    
}

public int getid (String n ){ 
    int cl=0 ; 
    try {
            String requete = "select * from coach where ?= login_co";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, n);
            ResultSet e = pst.executeQuery();
            while(e.next()){
            Coach pre = new Coach() ; 
            
            pre.setId_co(e.getInt("id_co"));
            cl=pre.getId_co();   }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
return cl ; 
    }

public List<Coach> ListClasse1(String c ) {
        List<Coach> Mylist = new ArrayList<>();
        try {
            String requete = "select * from coach where nom_co LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c);
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






}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Client;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiheb
 */
public class ServiceClient {
    Connection cnx;
    private PreparedStatement ste ;

    public ServiceClient() {
    cnx =MyConnection.getInstance().getConnection();        
    }
    
    
    public void ajouterc(Client c) {
       String req ="INSERT INTO client (nom_c, prenom_c, ddn,genre_c, login_c ,mdp_c) VALUES (?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            
            
            ste.setString(1,c.getNom_c());
            ste.setString(2,c.getPrenom_c());
            ste.setDate(3, new java.sql.Date(c.getDdn().getTime()));
            ste.setString(4,c.getGenre_c());
            ste.setString(5,c.getLogin_c());
            ste.setString(6,c.getMdp_c());
            ste.executeUpdate();
            System.out.println("Client ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
      }
    }

   public List<Client> ListClasse() {
        List<Client> Mylist = new ArrayList<>();
        try {
            String requete = "select * from client";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Client pre = new Client();
              
            pre.setId_c(e.getInt("id_c"));
            pre.setNom_c(e.getString("nom_c"));
            pre.setPrenom_c(e.getString("prenom_c"));
            pre.setDdn(e.getDate("ddn"));
            pre.setGenre_c(e.getString("genre_c"));
            pre.setLogin_c(e.getString("login_c"));
            pre.setMdp_c(e.getString("mdp_c"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
   
   
    
    
    public void supprimerc (Client c ) {
    String requete = "DELETE FROM client where nom_c =?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom_c());
            pst.executeUpdate();
            System.out.println("client Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (Client c) {
    
        try {
            String requete = "update client set prenom_c =?,ddn=? ,genre_c =? , login_c =? , mdp_c =? where ? = nom_c";
            PreparedStatement pre = cnx.prepareStatement(requete);

            pre.setString(1, c.getPrenom_c());
            pre.setDate(2, new java.sql.Date(c.getDdn().getTime()));
            pre.setString(3, c.getGenre_c());
            pre.setString(4, c.getLogin_c());
            pre.setString(5, c.getMdp_c());
            pre.setString(6,c.getNom_c());

            pre.executeUpdate();
            System.out.println("Client Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public Client select(String c ){
        
    Client cl = new Client() ; 
    try {
            String requete = "select * from client where ?= nom_c";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c);
            ResultSet e = pst.executeQuery();
            while(e.next()){
            Client pre = new Client() ; 
            pre.setId_c(e.getInt("id_c"));
            pre.setNom_c(e.getString("nom_c"));
            pre.setPrenom_c(e.getString("prenom_c"));
            pre.setDdn(e.getDate("ddn"));
            pre.setGenre_c(e.getString("genre_c"));
            pre.setLogin_c(e.getString("login_c"));
            pre.setMdp_c(e.getString("mdp_c")); 
            
             cl=pre;   }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
   
    
    return cl ; 
    
    
    
    
    
    
    
    
    
    
    
    }
    
    
 
    
    
    
    
    
    
    
    
   
    
}

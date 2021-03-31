/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;

import packages.entities.Coach;
import packages.entities.Reclamation;
import packages.interfaces.IReclamation;
import packages.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21692
 */

public class ReclamationCRUD implements IReclamation<Reclamation> {
    @Override
    public void ajouterReclamtion(Reclamation r ) {
        
           try {
                
            String requete ="INSERT INTO reclamation (description,prenomc ,categorie,id_co)" 
                    +"Values(?,?,?,?)";
             
            
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
           
         
pst.setString(1, r.getDescription());
pst.setString(2, r.getPrenomc());



pst.setString(3, r.getCategorie());
pst.setInt(4, r.getId_co());
   // System.out.println(requete);

pst.executeUpdate();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
       
    }
  
    

    @Override
    public void supprimerReclamation(Reclamation r) {
        
       
        try {
               
            String requete = "DELETE FROM Reclamation where id=?";
           PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("reclamation supprimée");
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
        
    }

    @Override
    public void consulterReclamation(Reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    

    
    
    
        
        
     
         
    

    
    public List<Reclamation> afficheReclamation(String r) {
        List<Reclamation> List = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where prenomc="+r;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Reclamation R = new Reclamation();
                R.setId(rs.getInt("id"));
                R.setNom_client(rs.getString(2));
                R.setDescription(rs.getString("description"));
                R.setPrenomc(rs.getString("prenomc"));
                R.setCategorie(rs.getString("categorie"));
                List.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
        
    }

    @Override
    public List<Reclamation> afficheReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    }

    
    


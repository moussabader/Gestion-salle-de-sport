/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;


import packages.entities.Avis;
import packages.interfaces.IAvis;
import packages.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author 21692
 */
public class AviCRUD implements IAvis<Avis> {
    public void ajouterAvis(Avis A) {
        try {
            String requete ="INSERT INTO Avis(titre,commentaire,date,niveau,id_produit,nom_produit)" 
                    +"Values(?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
         
pst.setString(1, A.getTitre());
pst.setString(2,A.getCommentaire());
pst.setString(3, A.getDate());
pst.setString(4, A.getNiveau());
pst.setInt(5, A.getId_produit());
pst.setString(6, A.getNom_produit());
//pst.setInt(7, A.getId_c());



pst.executeUpdate();
            


            System.out.println("Avis ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
  
   

  //  @Override
    public void consulterAvis(Avis a) {
    /*    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
public void nbLike(Avi a) {
        
        
        try {
            
                     
           String req = "INSERT INTO avi(nb_ajout)" 
                    +"Values(?)";
             
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            
         
           // pst.setInt(1, l.getId_pub());
            pst.setInt(1, a.getNb_ajout());
            //pst.setInt(2, l.getId_pub());
           // pst.setInt(3, l.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
          
        }
        
        */
        
    }   

    @Override
    public void supprimerAvis(Avis a) {
           try {
            String requete = "DELETE FROM Avis where idavis=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getIdavis());
            pst.executeUpdate();
            System.out.println("Avis supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   

    @Override
    public void modifierAvis(Avis a) {
try {
            String requete = "UPDATE Avis SET titre=?, commentaire=?,date=?,niveau? WHERE idavis=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, a.getTitre());
            pst.setString(2, a.getCommentaire());
               pst.setString(3, a.getDate());
                  pst.setString(4, a.getNiveau());
            pst.executeUpdate();
            System.out.println("Avi modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }  
}

   
    
    
    
    
    
    

    

  




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;

import packages.entities.Cours;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import packages.utils.DataSource;

/**
 *
 * @author Rzouga
 */
public class CoursService {
    
    
    private final Connection cnx;
    private static CoursService instance;
    
    public CoursService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static CoursService getInstance()
    {
        if (instance == null) {
            instance = new CoursService();
        }
        return instance; 
    }
    
    
        public boolean AjoutCours (Cours cours){
        
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `cours`(`nom`, `description`,`Coach`, `image`) VALUES (?,?,?,?)";
        PreparedStatement res=cnx.prepareStatement(req);
        
        res.setString(1, cours.getNom());
        res.setString(2, cours.getDescription());
        res.setString(3, cours.getNomCoach());
        res.setString(4, cours.getImage());

       
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
        
        
        
         public List<Cours> getAllCours(){
        
        List<Cours> list = new ArrayList<Cours>();
        int count =0;
        
        String requete="select * from cours";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Cours U = new Cours();
                U.setId(rs.getInt(1));
                U.setNom(rs.getString(2));
                U.setDescription(rs.getString(3));
                U.setNomCoach(rs.getString(4));
                U.setImage(rs.getString(5));
                
                list.add(U);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
      
}
         
         
         
  public Cours findCoursById(int id)
    {
        Cours u = new Cours();
        int count = 0;
           
        String requete="select * from cours where id="+id;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setNomCoach(rs.getString(4));
                u.setImage(rs.getString(5));
               
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return u;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
  
  
             public boolean deleteCours(int id) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `cours` WHERE `id` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
             
             
              public boolean ModifierCours   (Cours r) throws SQLDataException {

               
                String query = "UPDATE `cours` SET `nom`=?,`description`=?,`Coach`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                
                st.setString(1,r.getNom());
                st.setString(2,r.getDescription());
                st.setString(3,r.getNomCoach());
                st.setInt(4,r.getId());
              
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
              
              
                  
     public int NumCour () throws SQLException{
        
                    int c =0 ;
                    String query = "SELECT COUNT(*) FROM cours";
                    Statement st  = cnx.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                   c=c+ rs.getInt(1);
                     
                     }
                   //  System.out.println("9adechh"+c);
                     return c ;


 }
   
         
         

}

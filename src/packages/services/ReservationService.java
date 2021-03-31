/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;

import packages.entities.Cours;
import packages.entities.Reservation;
import java.sql.Connection;
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
public class ReservationService {
    
    
    private final Connection cnx;
    private static ReservationService instance;
    
    public ReservationService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static ReservationService getInstance()
    {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance; 
    }
    
    
        public boolean Reserver (Reservation r){
        
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `reservation`(`id_user`, `id_cours`) VALUES (?,?)";
        PreparedStatement res=cnx.prepareStatement(req);
        
        res.setInt(1, r.getIduser());
        res.setInt(2, r.getIdcour());
       
       
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
        
        
  public Cours findCoursByReservation(int id)
    {
        Cours u = new Cours();
        int count = 0;
           
        String requete="SELECT * FROM `cours` INNER JOIN reservation ON cours.id=reservation.id WHERE reservation.id_user="+id;
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
 
  
  
  public List<Reservation> getAllResrvation(int id){
        
        List<Reservation> list = new ArrayList<Reservation>();
        int count =0;
        
        String requete="select * from reservation where id_cours="+id;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Reservation U = new Reservation();
                U.setId_reservation(rs.getInt(1));
                U.setIdcour(rs.getInt(3));
                U.setIduser(rs.getInt(2));
               
                
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
  
  
  public List<Cours> getAllCoursReserver(int id){

            List<Cours> list = new ArrayList<Cours>();
        int count =0;
        
        String requete="SELECT * FROM `cours` INNER JOIN reservation ON cours.id=reservation.id_cours WHERE reservation.id_user="+id;
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
                 
   public boolean deleteReservation(int id,int idc) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `reservation` WHERE `id_user`='"+id+"'AND `id_cours`="+idc;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
        
}

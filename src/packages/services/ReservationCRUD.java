/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.services;

import packages.entities.Reservation;
import packages.interfaces.IReservation;
import packages.tools.MyConnection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ReservationCRUD implements IReservation {

    Connection cnx;

    public ReservationCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void AddReservation(Reservation r) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "insert into reservation(id_reservation,nom_client,nom_cours,date_reservation,etat) values(" + r.getId_reservation()+ ",'" + r.getNom_client() + "','" + r.getNom_cours() + "','" + r.getDate_reservation() + "','" + r.getEtat() + "')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Reservation> AfficherReservation() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `reservation` ";

        ResultSet rst = stm.executeQuery(query);

        ObservableList<Reservation> Reservations = FXCollections.observableArrayList();

        while (rst.next()) {
            Reservation r = new Reservation();
            r.setId_reservation(rst.getInt("id_reservation"));
            r.setNom_client(rst.getString("nom_client"));
            r.setNom_cours(rst.getString("nom_cours"));
            r.setDate_reservation(rst.getDate("date_reservation"));
            r.setEtat(rst.getString("etat"));
            Reservations.add(r);
        }

        return Reservations;
    }

    @Override
    public void updateReservation(int id, Reservation r) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "update reservation set nom_client = '" + r.getNom_client() + "', nom_cours = '" + r.getNom_cours() + "', date_reservation = '" + r.getDate_reservation() + "' where id_reservation = " + id + "";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateEtat(int id, Reservation r) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `reservation` SET `etat`= '" + r.getEtat() + "' where id_reservation = " + id + "";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteReservation(int id) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM reservation where id_reservation=" + id + "";
            stm.executeUpdate(query);
            System.out.println("reservation Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    
        public int calculer() {
          int l = 0 ;
         String requete = "SELECT COUNT(etat) FROM reservation WHERE etat=\"Reffuser\" " ;
        try {
           
           Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
            public int calculerapp() {
          int l = 0 ;
         String requete = "SELECT COUNT(Etat) FROM reservation WHERE Etat=\"Accepter\" " ;
        try {
           
           Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
            

}

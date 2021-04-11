/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.interfaces;

import packages.entities.Reservation;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public interface IReservation {
    public void AddReservation(Reservation r);
    public List<Reservation>AfficherReservation() throws SQLException;
    public void updateReservation(int nomid , Reservation r);
    public void updateEtat(int id ,Reservation t);
    public void deleteReservation(int id);
    
    
}

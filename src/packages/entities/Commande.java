/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.entities;

import java.util.Date;
import packages.services.CommandeCRUD;


public class Commande {
    
    private int id_commande;
    private String date_commande ;
    private double montant;
    private static int count;
    private int id_client;

    public Commande() {
        this.id_commande= ++count;
        count=id_commande;

    }

    public Commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", date_commande=" + date_commande + '}';
    }


}

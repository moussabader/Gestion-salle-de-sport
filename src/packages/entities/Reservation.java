/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

import java.sql.Date;

public class Reservation {

    private int id_reservation;
    private String nom_client;
    private String nom_cours;
    private Date date_reservation;
    private String etat;
    private int iduser ;
    private int idcour ;
    
    public Reservation() {
    }

    public Reservation(String nom_client) {
        this.nom_client = nom_client;
    }

    public Reservation(int id_reservation, String nom_client, String nom_cours, Date date_reservation, String etat) {
        this.id_reservation = id_reservation;
        this.nom_client = nom_client;
        this.nom_cours = nom_cours;
        this.date_reservation = date_reservation;
        this.etat = etat;
    }

    public Reservation(String nom_client, String nom_cours, Date date_reservation) {
        this.nom_client = nom_client;
        this.nom_cours = nom_cours;
        this.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nom_client=" + nom_client + ", nom_cours=" + nom_cours + ", date_reservation=" + date_reservation + ", etat=" + etat + '}';
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_reservation(int id_Reservation) {
        this.id_reservation = id_Reservation;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdcour() {
        return idcour;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }
   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;



/**
 *
 * @author asus
 */
public class Suivi {
    private int id_suivi;
    private String nom_coach;
    private String nom_client;
    private String objectifs;
    private String conseils;

    public Suivi() {
    }

    public Suivi(int id_suivi, String nom_coach, String nom_client, String objectifs, String conseils) {
        this.id_suivi = id_suivi;
        this.nom_coach = nom_coach;
        this.nom_client = nom_client;
        this.objectifs = objectifs;
        this.conseils = conseils;
    }

    @Override
    public String toString() {
        return "Suivi{" + "id_suivi=" + id_suivi + ", nom_coach=" + nom_coach + ", nom_client=" + nom_client + ", objectifs=" + objectifs + ", conseils=" + conseils + '}';
    }

    public int getId_suivi() {
        return id_suivi;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public String getConseils() {
        return conseils;
    }

    public void setId_suivi(int id_suivi) {
        this.id_suivi = id_suivi;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public void setConseils(String conseils) {
        this.conseils = conseils;
    }

    
    
    
    
}

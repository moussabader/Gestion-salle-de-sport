/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

import java.util.Date;

/**
 *
 * @author Rzouga
 */
public class Cours {
    
    private int id ;
    private String nom , description,nomCoach,image;
    private Date datec ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Cours() {
    }


    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public Cours(int id, String nom, String description, String nomCoach, String image, Date datec) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nomCoach = nomCoach;
        this.image = image;
        this.datec = datec;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nomCoach=" + nomCoach + ", image=" + image + ", datec=" + datec + '}';
    }
    
    
    
    
    
}

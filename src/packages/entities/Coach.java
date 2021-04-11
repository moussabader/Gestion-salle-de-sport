/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

/**
 *
 * @author hiheb
 */
public class Coach {
    int id_co ; 
    String nom_co , prenom_co , genre_co , login_co , mdp_co ; 



    public Coach(int id_co,String nom_co, String prenom_co, String genre_co) {
        this.id_co=id_co ; 
        this.nom_co = nom_co;
        this.prenom_co = prenom_co;
        this.genre_co = genre_co;
    }
    public Coach(int id_co, String nomc, String prenomc) {
        this.id_co = id_co;
        this.nom_co = nomc;
        this.prenom_co = prenomc;
    }
    
     public Coach(String login_co,String mdp_co){
        this.login_co=login_co ; 
        this.mdp_co=mdp_co ; }

    public Coach(int id_co, String nom_co, String prenom_co, String genre_co, String login_co, String mdp_co) {
        this.id_co = id_co;
        this.nom_co = nom_co;
        this.prenom_co = prenom_co;
        this.genre_co = genre_co;
        this.login_co = login_co;
        this.mdp_co = mdp_co;
    }

    public Coach(String nom_co, String prenom_co, String genre_co, String login_co, String mdp_co) {
        this.nom_co = nom_co;
        this.prenom_co = prenom_co;
        this.genre_co = genre_co;
        this.login_co = login_co;
        this.mdp_co = mdp_co;
    }
    public Coach(String nom_co, String prenom_co, String genre_co) {
        this.nom_co = nom_co;
        this.prenom_co = prenom_co;
        this.genre_co = genre_co;
        
    }

    public Coach() {
    }

    public int getId_co() {
        return id_co;
    }

    public void setId_co(int id_co) {
        this.id_co = id_co;
    }

    public String getNom_co() {
        return nom_co;
    }

    public void setNom_co(String nom_co) {
        this.nom_co = nom_co;
    }

    public String getPrenom_co() {
        return prenom_co;
    }

    public void setPrenom_co(String prenom_co) {
        this.prenom_co = prenom_co;
    }

    public String getGenre_co() {
        return genre_co;
    }

    public void setGenre_co(String genre_co) {
        this.genre_co = genre_co;
    }

    public String getLogin_co() {
        return login_co;
    }

    public void setLogin_co(String login_co) {
        this.login_co = login_co;
    }

    public String getMdp_co() {
        return mdp_co;
    }

    public void setMdp_co(String mdp_co) {
        this.mdp_co = mdp_co;
    }
    
}

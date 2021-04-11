/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

import java.util.Date;

/**
 *
 * @author hiheb
 */
public class Client {
   int id_c ; 
   String nom_c , prenom_c , genre_c , login_c , mdp_c ; 
   Date ddn ; 

    public Client() {
    }
    public Client(String login_c,String mdp_c){
        this.login_c=login_c ; 
        this.mdp_c=mdp_c ; }
    public Client(int id_c,String nom_c,String prenom_c , String genre_c , Date ddn ){
        this.id_c=id_c ; 
        this.nom_c=nom_c ; 
        this.prenom_c=prenom_c ;
    this.genre_c=genre_c ; 
    this.ddn=ddn ; }

    public Client( int id_c ,String nom_c, String prenom_c, String genre_c, String login_c, String mdp_c, Date ddn) {
        this.id_c=id_c ; 
        this.nom_c = nom_c;
        this.prenom_c = prenom_c;
        this.genre_c = genre_c;
        this.login_c = login_c;
        this.mdp_c = mdp_c;
        this.ddn = ddn;
    }
      public Client( String nom_c, String prenom_c, String genre_c, String login_c, String mdp_c, Date ddn) {
         
        this.nom_c = nom_c;
        this.prenom_c = prenom_c;
        this.genre_c = genre_c;
        this.login_c = login_c;
        this.mdp_c = mdp_c;
        this.ddn = ddn;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getPrenom_c() {
        return prenom_c;
    }

    public void setPrenom_c(String prenom_c) {
        this.prenom_c = prenom_c;
    }

    public String getGenre_c() {
        return genre_c;
    }

    public void setGenre_c(String genre_c) {
        this.genre_c = genre_c;
    }

    public String getLogin_c() {
        return login_c;
    }

    public void setLogin_c(String login_c) {
        this.login_c = login_c;
    }

    public String getMdp_c() {
        return mdp_c;
    }

    public void setMdp_c(String mdp_c) {
        this.mdp_c = mdp_c;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }
    
}

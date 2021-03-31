/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

/**
 *
 * @author 21692
 */
public class Reclamation {
    int id ;
    int id_co;
String nom_client;
    String description , categorie;
String prenomc;

 String date ;

    public Reclamation() {
       
    }

    public Reclamation( String description, String prenomc, String categorie,int id,int id_co) {
        this.id = id;
        this.id_co = id_co;
        this.nom_client = nom_client;
        this.description = description;
        this.categorie = categorie;
        this.prenomc = prenomc;
    }

    public Reclamation(String description, String prenomc, String categorie,int id_co) {
       
        this.description = description;
        this.prenomc = prenomc;
          this.categorie = categorie;
         this.id_co = id_co;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    


   


    
   

   
    public void setId_co(Integer id_co) {
        this.id_co= id_co;
    }

   

   

    public int getIdcoach() {
        return id_co;
    }

   
    

    public Reclamation(int id, String description, String prenomc, String categorie) {
        this.id = id;
       // this.nom_client = nom_client;
        this.description = description;
        
        this.prenomc = prenomc;
        this.categorie = categorie;
      //  this.idcoach = idcoach;
    }
    

    public String getCategorie() {
        return categorie;
    }

    
    public int getId() {
        return id;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getDescription() {
        return description;
    }

    public String getPrenomc() {
        return prenomc;
    }

    public int getId_co() {
        return id_co;
    }

   

    
    
    

   
    
   
    public void setId(int id) {
        this.id = id;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrenomc(String prenomc) {
        this.prenomc = prenomc;
    }

    public Reclamation(int id, String description, String prenomc) {
        this.id = id;
        this.description = description;
        this.prenomc = prenomc;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_co=" + id_co + ", nom_client=" + nom_client + ", description=" + description + ", categorie=" + categorie + ", prenomc=" + prenomc + '}';
    }

    

  
  
    
    
}

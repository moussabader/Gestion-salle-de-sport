/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

/**
 *
 * @author ayoub
 */
public class Ticket {
    private int idt;
    private String nom_event;
    private int prix;
    private int nombre;

    public Ticket() {
    }

    public Ticket( String nom_event, int prix, int nombre) {
        
        this.nom_event = nom_event;
        this.prix = prix;
        this.nombre = nombre;
    }

    public Ticket(int idt, String nom_event, int prix, int nombre) {
        this.idt = idt;
        this.nom_event = nom_event;
        this.prix = prix;
        this.nombre = nombre;
    }

    public int getIdt() {
        return idt;
    }

    public String getNom_event() {
        return nom_event;
    }

    public int getPrix() {
        return prix;
    }

    public int getNombre() {
        return nombre;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.entities;

/**
 *
 * @author hazar
 */
public class Vote {
    
    private int id ;
    private int id_cour  ;
    private int id_client;
    private int type_vote;

    public Vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getType_vote() {
        return type_vote;
    }

    public void setType_vote(int type_vote) {
        this.type_vote = type_vote;
    }

    public Vote(int id, int id_cour, int id_client, int type_vote) {
        this.id = id;
        this.id_cour = id_cour;
        this.id_client = id_client;
        this.type_vote = type_vote;
    }

    public int getId_cour() {
        return id_cour;
    }

    public void setId_cour(int id_cour) {
        this.id_cour = id_cour;
    }


    
}

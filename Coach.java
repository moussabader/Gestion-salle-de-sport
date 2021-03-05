/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Riva
 */
public class Coach {
    private int idcoach ; 
    private String nomc , prenomc , genrec , loginc,pwc ; 

    public Coach() {}
    
    
    public Coach(int idcoach, String nomc, String prenomc, String genrec, String loginc , String pwc ) {
        this.idcoach = idcoach;
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.genrec = genrec;
        this.loginc = loginc;
        this.pwc = pwc ; 
    }
    public Coach(int idcoach,int idc, String nomc, String prenomc, String genrec, String loginc , String pwc ) {
        this.idcoach = idcoach;
        this.idcoach=idc ;
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.genrec = genrec;
        this.loginc = loginc;
        this.pwc = pwc ; 
    }


    public int getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(int idcoach) {
        this.idcoach = idcoach;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public String getPrenomc() {
        return prenomc;
    }

    public void setPrenomc(String prenomc) {
        this.prenomc = prenomc;
    }

    public String getGenrec() {
        return genrec;
    }

    public void setGenrec(String genrec) {
        this.genrec = genrec;
    }

    public String getLoginc() {
        return loginc;
    }

    public void setLoginc(String loginc) {
        this.loginc = loginc;
    }

    public String getPwc() {
        return pwc;
    }

    public void setPwc(String pwc) {
        this.pwc = pwc;
    }

    @Override
    public String toString() {
        return "Coach{" + "idcoach=" + idcoach + ", nomc=" + nomc + ", prenomc=" + prenomc + ", genrec=" + genrec + ", loginc=" + loginc + ", pwc=" + pwc + '}';
    }
    
    
}

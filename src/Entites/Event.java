/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;

/**
 *
 * @author ayoub
 */
public class Event {
      private int id;
    private String nom_event;
    private String type_sport;
    private int phone_number;
    private Date datee;
    private String location;
    
     public Event(){
        
    }
    public Event(String nom_event ,String type_sport, int phone_number, Date datee, String location){
        this.nom_event=nom_event;
        this.type_sport=type_sport;
        this.phone_number=phone_number;
        this.datee=datee;
        
        this.location=location;
       
    }
      public Event(int id, String nom_event, String type_sport, int phone_number, Date datee, String location) {
        this.id=id;
        this.nom_event=nom_event;
        this.type_sport=type_sport;
        this.phone_number=phone_number;
        this.datee=datee;
        
        this.location=location;
          

  
  
     }
      
    public String getnom_event (){return nom_event;}
    public void setNom_event (String nom_event){ this.nom_event = nom_event;}
    
    public String gettype_sport (){return type_sport;}
    public void settype_sport (String type_sport){ this.type_sport = type_sport;}
    
    public int getphone_number (){return phone_number;}
    public void setPhone_number (int phone_number){ this.phone_number = phone_number;}
    
   public Date getdatee (){return datee;}
    public void setdatee (Date datee){ this.datee = datee;}
    
    
    
    
    public String getLocation (){return location;}
    public void setLocation (String location){ this.location = location;}
    
    
  
     
}
    
    


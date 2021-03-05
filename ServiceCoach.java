/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Coach;
import Services.ISericeCoach;
import Utils.MyConnection;
import com.oracle.net.Sdp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riva
 */
public class ServiceCoach implements ISericeCoach {

    Connection cnx;
    private PreparedStatement ste ;

    public ServiceCoach() {
    cnx =MyConnection.getInstance().getConnection();        
    }
    
    @Override
    public void ajouterc(Coach c) {
       String req ="INSERT INTO coach (idcoach, nomc, prenomc, genrec, loginc ,pwc) VALUES (?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            
            ste.setInt(1,c.getIdcoach());
            ste.setString(2,c.getNomc());
            ste.setString(3,c.getPrenomc());
	    ste.setString(4,c.getGenrec());
            ste.setString(5,c.getLoginc());
            ste.setString(6,c.getPwc());
            ste.executeUpdate();
            System.out.println("Coach ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
      }
    }

   public List<Coach> ListClasse() {
        List<Coach> Mylist = new ArrayList<>();
        try {
            String requete = "select * from coach";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Coach pre = new Coach();
              
            pre.setIdcoach(e.getInt("idcoach"));
            pre.setNomc(e.getString("nomc"));
            pre.setPrenomc(e.getString("prenomc"));
            pre.setGenrec(e.getString("genrec"));
            pre.setLoginc(e.getString("loginc"));
            pre.setPwc(e.getString("pwc"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
   
   @Override
    public ResultSet afficherc() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void supprimerc (Coach c ) {
    String requete = "DELETE FROM coach where idcoach=?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getIdcoach());
            pst.executeUpdate();
            System.out.println("coach Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (Coach c) {
    
        try {
            String requete = "update coach set nomc=?,prenomc=?,genrec=?,loginc=?,pwc=? where ? = idcoach";
            PreparedStatement pre = cnx.prepareStatement(requete);

            pre.setString(1, c.getNomc());
            pre.setString(2, c.getPrenomc());
            pre.setString(3, c.getGenrec());
            pre.setString(4, c.getLoginc());
            pre.setString(5, c.getPwc());
            pre.setInt(6,c.getIdcoach());

            pre.executeUpdate();
            System.out.println("evenement Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    
    
    

   
    
    
    
    
}

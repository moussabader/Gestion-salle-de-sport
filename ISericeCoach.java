/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Coach;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Riva
 */
public interface ISericeCoach {
    public void ajouterc(Coach c);
    public ResultSet afficherc() throws SQLException;
  
    
}

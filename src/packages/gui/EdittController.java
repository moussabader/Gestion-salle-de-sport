/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import packages.tools.cnxBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class EdittController implements Initializable {

    PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
     cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = myc.getConnection();
    @FXML
    private TextField nom_event;
    @FXML
    private TextField px;
    @FXML
    private TextField nbr;
    @FXML
    private Button update;
    
    int ref =Integer.parseInt(Show_ticketController.ref_ticket);

    /**
     * Initializes the controller class.
     */
            
  
    public void initialize(URL url, ResourceBundle rb) {
        Statement statement=null ;
        ResultSet resultSet = null ;
        try {
            
            statement=(Statement) cnx.createStatement();
          
            String sql="SELECT * FROM ticket WHERE idt = '"+ref+"' ";
            
            //preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
           resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
           
            nom_event.setText(resultSet.getString("nom_event"));
          
            px.setText(resultSet.getString("prix"));
            nbr.setText(resultSet.getString("nombre"));
            
           
                  	 // InputStream istreamImage = resultSet.getBinaryStream("image_db");

           // im.resultSet.getImage("image_db");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
        
        updatedata();
        showMessageDialog(null,"update with success");
    }

    public void updatedata() throws SQLException{
        String st = "UPDATE ticket SET nom_event= ?, prix = ?, nombre = ? WHERE idt = '"+ref+"';";
        preparedStatement = (PreparedStatement) cnx.prepareStatement(st);
        preparedStatement.setString(1, nom_event.getText());
        preparedStatement.setString(2, px.getText());
        preparedStatement.setString(3, nbr.getText());
       
       
        preparedStatement.executeUpdate();
        // TODO
    }    
    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("show_ticket.fxml"));
            Stage stage = (Stage) update.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  
}

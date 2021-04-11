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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class EditController implements Initializable {

    
    PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
     cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = myc.getConnection();
    @FXML
    private Button update;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField tel;
    @FXML
    private TextField date;
    @FXML
    private TextField location;
    int ref =Integer.parseInt(Show_eventController.ref_event);
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().addAll("Gymnastique","Spinning","Musculation","Marathon");
         Statement statement=null ;
        ResultSet resultSet = null ;
        try {
            
            statement=(Statement) cnx.createStatement();
          
            String sql="SELECT * FROM event WHERE id = '"+ref+"' ";
            
            //preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
           resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
           
            nom.setText(resultSet.getString("nom_event"));
          
            type.setValue(resultSet.getString("type_sport"));
            tel.setText(resultSet.getString("phone_number"));
            date.setText(resultSet.getString("datee"));
            location.setText(resultSet.getString("location"));
           
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
        String st = "UPDATE event SET nom_event= ?, type_sport = ?, phone_number = ?,  datee = ?,  location = ? WHERE id = '"+ref+"';";
        preparedStatement = (PreparedStatement) cnx.prepareStatement(st);
        preparedStatement.setString(1, nom.getText());
        preparedStatement.setString(2, type.getValue());
        preparedStatement.setString(3, tel.getText());
        preparedStatement.setString(4, date.getText());
        preparedStatement.setString(5, location.getText());
       
        preparedStatement.executeUpdate();
      
       
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("show_event.fxml"));
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

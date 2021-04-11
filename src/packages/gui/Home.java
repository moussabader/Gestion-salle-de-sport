/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Home extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root;
            //root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            //root = FXMLLoader.load(getClass().getResource("ListProduitClient.fxml"));
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            root.getStylesheets().add(getClass().getResource("menu.css").toString());
            Scene scene = new Scene(root, 746, 540);
            
            //primaryStage.setTitle("Ajout des Produits");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

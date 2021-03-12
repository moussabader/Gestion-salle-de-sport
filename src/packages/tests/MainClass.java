/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import packages.entities.Commande;
import packages.entities.LigneCommande;
import packages.entities.Produit;
import packages.services.CommandeCRUD;
import packages.services.ProduitCRUD;
import packages.tools.MyConnection;


public class MainClass {

    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        
      // Test Produit  
        ProduitCRUD pcd = new ProduitCRUD();
        Produit p = new Produit();
        
        //Ajouter un produit 
        Scanner sc = new Scanner(System.in);
            
            System.out.println("Entrer l'url de l'image de produit:");
            String path = sc.nextLine();
            p.setImage_path(path);
            
            System.out.println("Entrer le nom de produit:");
            String nom = sc.nextLine();
            p.setNom_produit(nom);
            
            System.out.println("Entrer la marque de produit:");
            String marque = sc.nextLine();
            p.setMarque_produit(marque);
            
            System.out.println("Entrer la quantité de produit:");
            int qte = sc.nextInt();
            p.setQuantite(qte);
            
            System.out.println("Entrer le prix de produit:");
            Double prix = sc.nextDouble();
            p.setPrix(prix);
        
        
        pcd.ajouterProduit(p);
        System.out.println(pcd.afficherProduits());
        System.out.println("=================================");
        
        //modifier un produit 
        
        Scanner sc2 = new Scanner(System.in);     

            
            System.out.println("Entrer le nouveau url de l'image de produit:");
            String newpath = sc2.nextLine();
            p.setImage_path(newpath);
            
            System.out.println("Entrer le nouveau nom de produit:");
            String newnom = sc2.nextLine();
            p.setNom_produit(newnom);
            
            System.out.println("Entrer la nouvelle marque de produit:");
            String newmarque = sc2.nextLine();
            p.setMarque_produit(newmarque);
            
            System.out.println("Entrer la nouvelle quantité de produit:");
            int newqte = sc2.nextInt();
            p.setQuantite(newqte);
            
            System.out.println("Entrer le nouveau prix de produit:");
            Double newprix = sc2.nextDouble();
            p.setPrix(newprix);
            
            System.out.println("Entrer l'id de produit à modifier:");
            int id = sc2.nextInt();
            
            pcd.modifierProduit(p,id );
        
        
        System.out.println(pcd.afficherProduits());
        System.out.println("---------------------------------------");
        
        // supprimer un produit 
        Scanner sc8 = new Scanner(System.in);
        System.out.println("Entrer l'id de produit à supprimer:");
        int idp_supp = sc8.nextInt();
        pcd.supprimerProduit(idp_supp);
        System.out.println(pcd.afficherProduits());
        
        
        // Test Commande
        CommandeCRUD ccd = new CommandeCRUD();
        Commande c = new Commande();
        LigneCommande lc = new LigneCommande();
        
        // Ajouter une commande et ligne commande
        Scanner sc3 = new Scanner(System.in);
            
        System.out.println("Saisissez la date de la commande (AAAA-MM-JJ) :");
        String str = sc3.nextLine();
        if (str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            c.setDate_commande(str);
        } else{
            System.out.println("date invalide");
        }
                    /*if (str.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                        try {
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(str);
                            c.setDate_commande(str);
                            } catch (ParseException ex) {
                            System.out.println(ex.getMessage());
                            }
                            } else {
                            System.out.println("Erreur format");
                            }*/
        
        ccd.ajouterCommande(c);
        System.out.println(ccd.afficherCommandes());
        //Ajouter une ligne commande
        System.out.println("Saisissez l'id de la commande récemment ajouté");
        int idc = sc3.nextInt();
        System.out.println("Saisissez le nombre des produits commandés");
        int nbr = sc3.nextInt();
        for (int i = 1; i <= nbr; i++) {
            System.out.println("Saisissez l'id de produit "+i+":");
            int idp = sc3.nextInt();
            System.out.println("Saisissez la quantité commandée de ce produit:");
            int qtecmd = sc3.nextInt();    
            lc.setQuantite_commande(qtecmd);
            ccd.ajouterProduitCommande(idc,idp, lc);
            double mt = ccd.calculerMontant(idc, idp);
            ccd.updateMontant(mt,idc);
        }
        //System.out.println(ccd.afficherProduitCommandes());
        
        
        
        //modifier une commande  
        Scanner sc4 = new Scanner(System.in);     
        System.out.println("Entrer le nouveau date de commande:");
        String newdate = sc4.nextLine();
        c.setDate_commande(newdate);

        System.out.println("Entrer l'id de la commande à modifier:");
        int idc_edit = sc4.nextInt();

        ccd.modifierCommande(c, idc_edit);
        System.out.println(ccd.afficherCommandes());
            
        //Supprimer une Commande
        Scanner sc5 = new Scanner(System.in);
        System.out.println("Entrer l'id de la commande à supprimer:");
        int idc_supp=sc5.nextInt();
        ccd.supprimerCommande(idc_supp);
        System.out.println(ccd.afficherCommandes());
            
        //Modifier une ligne commande
        Scanner sc6 = new Scanner(System.in);
        System.out.println("Entrer l'id de la commande de la ligne à modifier:");
        int id_lc=sc6.nextInt();
        
        System.out.println("Entrer le nouveau id de produit de la ligne à modifier:");
        int idp_lc= sc6.nextInt();
        p.setId_produit(idp_lc);
        
        System.out.println("Entrer la nouvelle quantité de la ligne à modifier:");
        int qte_lc= sc6.nextInt();
        lc.setQuantite_commande(qte_lc);
        int id2 = p.getId_produit();
        ccd.modifierProduitCommande(id2, lc, id_lc);
        //System.out.println(ccd.afficherProduitCommandes());
          
        //Supprimer une ligne commande
        Scanner sc7 = new Scanner(System.in);
        System.out.println("Entrer l'id commande de la ligne commande à supprimer:");
        int idlc_supp=sc7.nextInt();
        ccd.supprimerProduitCommande(idlc_supp);
        //System.out.println(ccd.afficherProduitCommandes());
        
        //Afficher les listes des commandes et lignes commandes
        System.out.println("Affichage de la liste des commandes");
        System.out.println("-----------------------------------");
        System.out.println(ccd.afficherCommandes());
        //System.out.println("Affichage de la liste des lignes commandes");
        //System.out.println("------------------------------------------");
        //System.out.println(ccd.afficherProduitCommandes());
        
        
        //Test calcul montant d'une seule ligne commande
        double i = ccd.calculerMontant(41, 21);
        System.out.println(i);
    }
}

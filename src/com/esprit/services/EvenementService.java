/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.IService.IEvenementService;
import com.esprit.entities.Evenement;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhk
 */
public class EvenementService implements IEvenementService {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    

    @Override
    public void ajouterEvenement(Evenement p) {
        try {
            String req = "INSERT INTO Evenement (nom_evenement,theme_evenement,lieu_evenement,dateEvenement,nbr_participant,nbr_max_participant,image_evenement,description_evenement,validation) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, p.getNom_evenement());
            st.setString(2, p.getTheme_evenement());
            st.setString(3, p.getLieu_evenement());
            st.setString(4, p.getDate_evenement());
           
            st.setInt(5, p.getNbr_participant());
            st.setInt(6, p.getNbr_max_participant());
            
                   st.setString(7, p.getImage_evenement());   
            st.setString(8, p.getDescription_evenement());
             st.setString(9, p.getValidation());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public List<Evenement> lireEvenementenattente() {
        try {
            String a="En attente";
            String query = "SELECT * FROM evenement WHERE validation like 'En attente'";
            ArrayList Resultat = new ArrayList<Evenement>();
            Statement stm= conn.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Evenement evenement=new Evenement(rs.getInt("id"),rs.getString("nom_evenement"),rs.getString("theme_evenement"),rs.getString("lieu_evenement"),rs.getString("dateEvenement"),rs.getInt("nbr_participant"),rs.getInt("nbr_max_participant"),rs.getString("image_evenement"),rs.getString("description_evenement"),rs.getString("validation"));
            Resultat.add(evenement);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec");
            return null;
        } 
    }
  
    public List<Evenement> lireEvenement() {
        try {
            String query = "SELECT * FROM evenement";
            ArrayList Resultat = new ArrayList<Evenement>();
            Statement stm= conn.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Evenement evenement=new Evenement(rs.getInt("id"),rs.getString("nom_evenement"),rs.getString("theme_evenement"),rs.getString("lieu_evenement"),rs.getString("dateEvenement"),rs.getInt("nbr_participant"),rs.getInt("nbr_max_participant"),rs.getString("image_evenement"),rs.getString("description_evenement"),rs.getString("validation"));
            Resultat.add(evenement);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec");
            return null;
        } 
    }
    @Override
  public void modifierEvenement(Evenement e) {
        try {
            String query = "UPDATE `evenement` SET `nom_evenement`='"+e.getNom_evenement()+"',`theme_evenement`='"+e.getTheme_evenement()+"',`lieu_evenement`='"+e.getLieu_evenement()+"',`dateEvenement`='"+e.getDate_evenement()+"',`nbr_participant`='"+e.getNbr_participant()+"'"
                    + ",`nbr_max_participant`='"+e.getNbr_max_participant()+"',`image_evenement`='"+e.getImage_evenement()+"',`description_evenement`='"+e.getDescription_evenement()+"',`validation`='"+e.getValidation()+"'"
                    +  "WHERE `id`="+e.getId()+"";
            Statement stm= conn.createStatement();
           
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }
    }
         public void supprimerEvenement(int id) {
         try {
            String query = "DELETE FROM `evenement` "
                   + "WHERE id="+id+"";
            //String query = "INSERT INTO promotions (nomPromotion, imagePromotion) "
              //      + "VALUES ('test', 'test') ";
             Statement stm= conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
        }
    }
    }

   
   
  
    



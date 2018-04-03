/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author bhk
 */
public class Evenement {
    public int id ;
    public String nom_evenement;
    public String theme_evenement;
    public String lieu_evenement;
    public String date_evenement;
   
    public int nbr_participant;
    public int  nbr_max_participant;
    String image_evenement;
     public String  description_evenement;
     String validation;
     

    public Evenement( String nom_evenement, String theme_evenement, String lieu_evenement, String  date_evenement, int nbr_participant, int nbr_max_participant,String image_evenement, String description_evenement,String validation) {
        
        this.nom_evenement = nom_evenement;
        this.theme_evenement = theme_evenement;
        this.lieu_evenement = lieu_evenement;
        this.date_evenement = date_evenement;
       
        this.nbr_participant = nbr_participant;
        this.nbr_max_participant = nbr_max_participant;
        this.image_evenement= image_evenement;
        this.description_evenement = description_evenement;
        this.validation=validation;
    }

    public Evenement(int id, String nom_evenement, String theme_evenement, String lieu_evenement, String date_evenement, int nbr_participant, int nbr_max_participant,String image_evenement, String description_evenement,String validation) {
        this.id = id;
        this.nom_evenement = nom_evenement;
        this.theme_evenement = theme_evenement;
        this.lieu_evenement = lieu_evenement;
        this.date_evenement = date_evenement;
        this.nbr_participant = nbr_participant;
        this.nbr_max_participant = nbr_max_participant;
        this.image_evenement= image_evenement;
        this.description_evenement = description_evenement;
         this.validation=validation;
    }

    public Evenement() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getTheme_evenement() {
        return theme_evenement;
    }

    public void setTheme_evenement(String theme_evenement) {
        this.theme_evenement = theme_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }

    public String getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.date_evenement = date_evenement;
    }

   
    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public int getNbr_max_participant() {
        return nbr_max_participant;
    }

    public void setNbr_max_participant(int nbr_max_participant) {
        this.nbr_max_participant = nbr_max_participant;
    }

    public String getImage_evenement() {
        return image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
    }

   

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom_evenement=" + nom_evenement + ", theme_evenement=" + theme_evenement + ", lieu_evenement=" + lieu_evenement + ", date_evenement=" + date_evenement +", nbr_participant=" + nbr_participant + ", nbr_max_participant=" + nbr_max_participant + ", description_evenement=" + description_evenement + '}';
    }
    

    
    
    
    
}

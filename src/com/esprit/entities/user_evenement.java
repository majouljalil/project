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
public class user_evenement {
    public int id ;
    public String login;
    public String MDP;
    public String mail;
    public String role;
   
    public user_evenement() {
    }

    public user_evenement(int id, String login, String MDP,String mail, String role) {
        this.id = id;
        this.login = login;
        this.MDP= MDP;
         this.mail=mail;
        this.role = role;
        
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMDP() {
        return MDP;
    }

    public void setMDP(String MDP) {
        this.MDP = MDP;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
   
     

    
}

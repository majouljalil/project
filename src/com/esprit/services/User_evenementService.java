/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.IService.IEvenementService;
import com.esprit.entities.Evenement;
import com.esprit.entities.user_evenement;
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
public class User_evenementService  {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    
user_evenement user_evenement=new user_evenement();
    
   

  public user_evenement  rechercheuser(String login,String password) {
        try {
            
           
            Statement stm= conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("Select * from user_evenement where login=? and MDP=?");
       pst.setString(1, login); 
       pst.setString(2, password);
       ResultSet rs = pst.executeQuery();                        
       if(rs.next()){            
           user_evenement=new user_evenement(rs.getInt("id"),rs.getString("login"),rs.getString("MDP"),rs.getString("mail"),rs.getString("role"));   
        return user_evenement;}
       else
           return null;            
   }
   catch(Exception e){
      
       return null;
   }       
    
  
    
    
}
public user_evenement  finduser(int id) {
        try {
            
           
            Statement stm= conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("Select * from user_evenement where id=? ");
       pst.setInt(1, id); 
      
       ResultSet rs = pst.executeQuery();                        
       if(rs.next()){            
           user_evenement=new user_evenement(rs.getInt("id"),rs.getString("login"),rs.getString("MDP"),rs.getString("mail"),rs.getString("role"));   
        return user_evenement;}
       else
           return null;            
   }
   catch(Exception e){
      
       return null;
   }       
    
  
    
    
}


}
   
   
  
    



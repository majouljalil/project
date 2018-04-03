
package com.esprit.IService;

import com.esprit.entities.Evenement;

import java.util.List;

/**
 *
 * @author abb
 */
public interface IEvenementService {
    public void ajouterEvenement(Evenement E);
  
    public void supprimerEvenement(int id);
    
    public void modifierEvenement(Evenement E);
    
    public List<Evenement> lireEvenement();
}

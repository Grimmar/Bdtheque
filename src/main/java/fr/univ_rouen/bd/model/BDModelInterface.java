package fr.univ_rouen.bd.model;

import java.util.List;

/**
 *
 * @author bissoqu1
 */
interface BDModelInterface {
    
    /**
     * Méthode permettant de chercher une bande dessinée grêce à un id
     * @param id
     * @return la bd si l'id existe dans la base, null sinon
     */
    Bd getById(int id);
    
    List<Bd> searchFor();
    
    List<Bd> list();
    
    boolean add(Bd bd);
    
    boolean delete(Bd bd);
    
    int count();
    
}

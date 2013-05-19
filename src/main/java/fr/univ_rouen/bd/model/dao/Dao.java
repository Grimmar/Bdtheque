package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.search.SearchBean;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.util.List;
import java.util.Map;

/**
 *	Interface de base pour tous les objets d'accès aux données
 *  Elle permet la mise en place du modèle CRUD avec les fonction add, searchFor, et update, delete
 *  Elle doit être instanciée par la DAOFactory
 * @author bissoqu1
 */
interface Dao<E> {

    /**
       Méthode permettant de compter le nombre d'élement de type E dans la base
       @return Le nombre d'éléments de type E dans la base
       @throws DAOException
    */
    int count() throws DAOException;

    /**
       Méthode permettant de récupérer un objet de type E dans la base grâce à son identifiant 
       @return L'objet s'il a été trouvé, null sinon
       @throws DAOException
    */
    E get(String id) throws DAOException;

    /**
       Méthode permettant de chercher une liste d'objet de type E selon les critères présents dans le searchType (JavaBean) et la map orderBy contenant des valeurs comme (id, ascending) ou (titre, desending) 
       @return La liste des objets répondant au critères
       @throws DAOException
    */
    List<E> searchFor(SearchBean searchType, Map<String, String> orderBy) throws DAOException;

    /**
       Méthode permettant d'ajouter un objet de type E à la base
       @return vrai si l'objet a été ajouté sans erreur
       @throws DAOException
    */
    boolean add(E e) throws DAOException;

    /**
       Méthode permettant de supprimer un objet de type E de la base
       @return vrai si l'objet a été supprimé sans erreur
       @throws DAOException
    */
    boolean delete(String id) throws DAOException;

    /**
       Méthode permettant de modifier un objet de type E dans la base
       @return vrai si l'objet a été modifié sans erreur
       @throws DAOException
    */
    boolean update(E e) throws DAOException;
    
}

package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.search.SearchBean;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bissoqu1
 */
interface Dao<E> {

    int count() throws DAOException;

    E get(String id) throws DAOException;

    List<E> searchFor(SearchBean searchType, Map<String, String> orderBy) throws DAOException;

    boolean add(E e) throws DAOException;

    boolean delete(String id) throws DAOException;

    boolean update(E e) throws DAOException;
}

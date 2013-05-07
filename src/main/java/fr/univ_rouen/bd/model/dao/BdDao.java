package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import fr.univ_rouen.bd.model.beans.search.SearchBean;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

/**
 *
 * @author bissoqu1
 */
public class BdDao implements Dao<Bd> {

    private static final String XML_SUFFIX = ".xml";
    private DAOFactory daoFactory;

    public BdDao(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public int count() throws DAOException {
        try {
            Collection col = daoFactory.getCollection();
            int count = col.getResourceCount();
            col.close();
            return count;
        } catch (XMLDBException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Bd get(String id) throws DAOException {
        try {
            Collection col = daoFactory.getCollection();
            XMLResource r = (XMLResource) col.getResource(id + XML_SUFFIX);
            col.close();

            if (r != null) {
                Unmarshaller u = daoFactory.getJAXBContext().createUnmarshaller();
                UnmarshallerHandler handler = u.getUnmarshallerHandler();

                r.getContentAsSAX(handler);
                return (Bd) handler.getResult();
            }

            return null;
        } catch (XMLDBException e) {
            throw new DAOException(e);
        } catch (JAXBException e) {
            throw new DAOException(e);
        }
    }

    public Bd getByISBN(String isbn) throws DAOException {
        StringBuilder query = new StringBuilder();
        Map<String, String> params = new HashMap<String, String>();
        query.append("for $bd in collection(\"bedetheque\")");
        params.put("isbn", isbn);
        query.append(" where contains(upper-case($bd/bd:bd/@bd:isbn), upper-case($isbn))");
        query.append(" return $bd");

        try {
            ResourceSet result = daoFactory.executeXQuery(query.toString(), params);
            Unmarshaller u = daoFactory.getJAXBContext().createUnmarshaller();
            UnmarshallerHandler handler = u.getUnmarshallerHandler();
            ResourceIterator i = result.getIterator();
            List<Bd> l = new ArrayList<Bd>();
            while (i.hasMoreResources()) {
                XMLResource r = (XMLResource) i.nextResource();
                r.getContentAsSAX(handler);
                l.add((Bd) handler.getResult());
            }
            if (CollectionUtils.isEmpty(l)) {
                return null;
            }
            return l.get(0);
        } catch (XMLDBException e) {
            throw new DAOException(e);
        } catch (JAXBException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Bd> searchFor(SearchBean sb, Map<String, String> orderBy) throws DAOException {
        BdSearchBean searchBean = (BdSearchBean) sb;
        try {
            Map<String, String> params = new HashMap<String, String>();
            StringBuilder query = new StringBuilder();

            query.append("for $bd in collection(\"bedetheque\")");
            String separator = " where ";
            if (sb != null) {
                if (StringUtils.isNotBlank(searchBean.getTitre())) {
                    params.put("titre", searchBean.getTitre());
                    query.append(separator).append("contains(upper-case($bd/bd:bd/bd:titre/text()), upper-case($titre))");
                    separator = " and ";
                }
                if (StringUtils.isNotBlank(searchBean.getEditeur())) {
                    params.put("editeur", searchBean.getEditeur());
                    query.append(separator).append("contains(upper-case($bd/bd:bd/bd:editeur/text()), upper-case($editeur))");
                    separator = " and ";
                }

                if (StringUtils.isNotBlank(searchBean.getLangue())) {
                    params.put("langue", searchBean.getLangue());
                    query.append(separator).append("contains(upper-case($bd/bd:bd/@bd:langue), upper-case($langue))");
                    separator = " and ";
                }

                if (StringUtils.isNotBlank(searchBean.getResume())) {
                    params.put("resume", searchBean.getResume());
                    query.append(separator).append("contains(upper-case($bd/bd:bd/bd:resume/text()), upper-case($resume))");
                    separator = " and ";
                }

                if (StringUtils.isNotBlank(searchBean.getSerie())) {
                    params.put("serie", searchBean.getSerie());
                    query.append(separator).append("contains(upper-case($bd/bd:bd/@bd:serie), upper-case($serie))");
                    separator = " and ";
                }

                if (CollectionUtils.isNotEmpty(searchBean.getScenaristes().getScenariste())) {
                    query.append(separator);
                    setParameterList(query, params, searchBean.getScenaristes().getScenariste(), "scenariste");
                    separator = " and ";
                }

                if (CollectionUtils.isNotEmpty(searchBean.getColoristes().getColoriste())) {
                    query.append(separator);
                    setParameterList(query, params, searchBean.getColoristes().getColoriste(), "coloriste");
                    separator = " and ";
                }

                if (CollectionUtils.isNotEmpty(searchBean.getDessinateurs().getDessinateur())) {
                    query.append(separator);
                    setParameterList(query, params, searchBean.getDessinateurs().getDessinateur(), "dessinateur");
                    separator = " and ";
                }

                if (CollectionUtils.isNotEmpty(searchBean.getEncrages().getEncrage())) {
                    query.append(separator);
                    setParameterList(query, params, searchBean.getEncrages().getEncrage(), "encrage");
                    separator = " and ";
                }

                if (CollectionUtils.isNotEmpty(searchBean.getLettrages().getLettrage())) {
                    query.append(separator);
                    setParameterList(query, params, searchBean.getLettrages().getLettrage(), "lettrage");
                }
            }
            if (MapUtils.isNotEmpty(orderBy)) {
                separator = " order by ";
                for (String key : orderBy.keySet()) {
                    String value = orderBy.get(key);
                    if (StringUtils.equals("ascending", value)
                            || StringUtils.equals("descending", value)) {
                        query.append(separator).append("$bd/bd:bd/bd:").append(key).append(" ").append(value);
                        separator = ", ";
                    }
                }
            }

            query.append(" return $bd");

            ResourceSet result = daoFactory.executeXQuery(query.toString(), params);
            Unmarshaller u = daoFactory.getJAXBContext().createUnmarshaller();
            UnmarshallerHandler handler = u.getUnmarshallerHandler();
            ResourceIterator i = result.getIterator();
            List<Bd> l = new ArrayList<Bd>();
            while (i.hasMoreResources()) {
                XMLResource r = (XMLResource) i.nextResource();
                r.getContentAsSAX(handler);
                l.add((Bd) handler.getResult());
            }

            return l;
        } catch (JAXBException e) {
            throw new DAOException(e);
        } catch (XMLDBException e) {
            throw new DAOException(e);
        }
    }

    private void setParameterList(StringBuilder query, Map<String, String> params, List<IndividuType> l, String prefix) {
        int i = 0;
        query.append("(");
        String separator = "";
        for (IndividuType ind : l) {
            //params.put(prefix + i, ind.getNom() + " " + ind.getPrenom());
            i++;
            query.append(separator)
                    .append("$bd/bd:bd/bd:").append(prefix).append("s").append("/bd:").append(prefix).append("/bd:nom= \"")
                    .append(ind.getNom())
                    .append("\"");
            if (StringUtils.isNotBlank(ind.getPrenom())) {
                query.append("and $bd/bd:bd/bd:").append(prefix).append("s").append("/bd:").append(prefix).append("/bd:prenom=\"")
                        .append(ind.getPrenom())
                        .append("\"");
            }
            if (i <= 1) {
                separator = " or ";
            }
        }
        query.append(")");

    }

    @Override
    public boolean add(Bd bd) throws DAOException {
        try {
            Collection col = daoFactory.getCollection();
            String id = col.createId();
            while (get(id) != null) {
                id = col.createId();
            }
            bd.setId(StringUtils.chomp(id, XML_SUFFIX));
            bd.setInsertedDate(DAOFactory.getXMLGregorianCalendar(new GregorianCalendar()));

            XMLResource resource = (XMLResource) col.createResource(id, XMLResource.RESOURCE_TYPE);
            Marshaller m = daoFactory.getJAXBContext().createMarshaller();
            m.marshal(bd, resource.setContentAsSAX());
            col.storeResource(resource);
            col.close();
            return true;
        } catch (XMLDBException e) {
            throw new DAOException(e);
        } catch (DatatypeConfigurationException e) {
            throw new DAOException(e);
        } catch (JAXBException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        try {
            Collection col = daoFactory.getCollection();
            XMLResource resource = (XMLResource) col.createResource(id + XML_SUFFIX, XMLResource.RESOURCE_TYPE);
            if (resource != null) {
                col.removeResource(resource);
                col.close();
                return true;
            }
            col.close();
            return false;
        } catch (XMLDBException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(Bd bd) throws DAOException {
        try {
            Collection col = daoFactory.getCollection();

            XMLResource resource = (XMLResource) col.createResource(bd.getId() + XML_SUFFIX, XMLResource.RESOURCE_TYPE);
            if (resource != null) {
                Marshaller m = daoFactory.getJAXBContext().createMarshaller();
                m.marshal(bd, resource.setContentAsSAX());
                col.storeResource(resource);
                col.close();
                return true;
            }
            col.close();
            return false;
        } catch (XMLDBException e) {
            throw new DAOException(e);
        } catch (JAXBException e) {
            throw new DAOException(e);
        }

    }
}

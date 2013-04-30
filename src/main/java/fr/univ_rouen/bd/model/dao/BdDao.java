package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import fr.univ_rouen.bd.model.beans.search.SearchBean;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Bd> searchFor(SearchBean sb, Map<String, String> orderBy) throws DAOException {
        BdSearchBean searchBean = (BdSearchBean) sb;
        try {
            Map<String, String> params = new HashMap<String, String>();
            StringBuilder query = new StringBuilder();

            query.append("for $bd in collection(\"bedetheque\")");
            String separator = " where ";

            if (StringUtils.isNotBlank(searchBean.getTitre())) {
                params.put("titre", searchBean.getTitre());
                query.append(separator).append("contains(upper-case($bd/bd:bd/bd:titre/text()), upper-case($titre))");
                separator = " and ";
            }
            if (StringUtils.isNotBlank(searchBean.getEditeur())) {
                params.put("editeur", searchBean.getEditeur());
                query.append(separator).append("compare(upper-case($bd/bd:bd/bd:editeur/text()), upper-case($editeur)) == 0");
                separator = " and ";
            }

            if (StringUtils.isNotBlank(searchBean.getLangue())) {
                params.put("langue", searchBean.getLangue());
                query.append(separator).append("compare(upper-case($bd/bd:bd/@bd:langue), upper-case($langue)) == 0");
                separator = " and ";
            }

            if (StringUtils.isNotBlank(searchBean.getResume())) {
                params.put("resume", searchBean.getResume());
                query.append(separator).append("contains(upper-case($bd/bd:bd/bd:resume/text()), upper-case($resume))");
                separator = " and ";
            }

            if (StringUtils.isNotBlank(searchBean.getSerie())) {
                params.put("serie", searchBean.getSerie());
                query.append(separator).append("compare(upper-case($bd/bd:bd/@bd:serie), upper-case($serie)) == 0");
                separator = " and ";
            }

            if (MapUtils.isNotEmpty(orderBy)) {
                separator = " order by ";
                query.append(separator);
                for (String key : orderBy.keySet()) {
                    String value = orderBy.get(key);
                    if (StringUtils.equals("ascending", value)
                            || StringUtils.equals("descending", value)) {
                        query.append(separator).append(value);
                        separator = ", ";
                    }
                }
            }

            query.append("return $bd");

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
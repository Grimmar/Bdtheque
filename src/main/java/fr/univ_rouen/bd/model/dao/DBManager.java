package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.ColoristesType;
import fr.univ_rouen.bd.model.beans.DessinateursType;
import fr.univ_rouen.bd.model.beans.EncragesType;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.LettragesType;
import fr.univ_rouen.bd.model.beans.ScenaristesType;
import fr.univ_rouen.bd.model.beans.TomeType;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author bissoqu1
 */
public class DBManager {

    private static final String XML_SUFFIX = ".xml";
    
    private Properties properties;
    private static DBManager instance;

    public static DBManager getInstance() throws Exception {
        if (null == instance) {
            instance = new DBManager();
        }
        return instance;
    }
    private final JAXBContext jc;

    private DBManager() throws Exception {
        properties = new Properties();
        properties.load(DBManager.class.getClassLoader()
                .getResourceAsStream("application.properties"));
        /*Context ctx = new InitialContext();
         Context existCtx = (Context) ctx.lookup("java:comp/env/exist");
         Database database = (Database) existCtx.lookup("xmlDB");*/
        Class cl = Class.forName(properties.getProperty("ClassName"));
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        jc = JAXBContext.newInstance(Bd.class, ColoristesType.class,
                DessinateursType.class, EncragesType.class, IndividuType.class,
                LettragesType.class, ScenaristesType.class, TomeType.class);
    }

    private Collection getCollection() throws XMLDBException {
        return getOrCreateCollection(properties.getProperty("Collection"), 0);
    }

    private Collection getOrCreateCollection(String collection, int pathSegmentOffset) throws XMLDBException {
        //Récupération des propriétés
        String URI = properties.getProperty("URI");
        String username = properties.getProperty("Username");
        String password = properties.getProperty("Password");

        // Récupération de la collection
        Collection col = DatabaseManager.getCollection(URI + collection, username, password);
        if (col == null) {
            if (collection.startsWith("/")) {
                collection = collection.substring(1);
            }
            String pathSegments[] = collection.split("/");
            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
                path.append(pathSegments[0]);
                for (int i = 1; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }
                Collection start = DatabaseManager.getCollection(URI + path, username, password);
                if (start == null) {
                    //collection does not exist, so create
                    if (!path.substring(0, path.lastIndexOf("/")).isEmpty()) {
                        String parentPath = path.substring(0, path.lastIndexOf("/"));
                        Collection parent = DatabaseManager.getCollection(URI + parentPath);
                        CollectionManagementService mgt =
                                (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
                        col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                        col.close();
                        parent.close();
                    }
                } else {
                    start.close();
                }
            }
            return getOrCreateCollection(collection, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    private ResourceSet executeXQuery(String query, Map<String, String> params) throws XMLDBException {
        Collection col = getCollection();
        XQueryService xqs = (XQueryService) col.getService("XQueryService", "1.0");
        xqs.setNamespace("bd", properties.getProperty("namespace"));
        xqs.setProperty("indent", "yes");
             
        for (String s : params.keySet()) {
            System.out.println(s + " : " + params.get(s));
            xqs.declareVariable(s, params.get(s));
        }
        
        CompiledExpression compiled = xqs.compile(query);
        System.out.println(query);
        ResourceSet result = xqs.execute(compiled);
        col.close();
        return result;
    }

    public int count() throws Exception {
        Collection col = getCollection();
        int count = col.getResourceCount();
        col.close();
        return count;
    }

    public Bd get(String id) throws Exception {
        Collection col = getCollection();
        XMLResource r = (XMLResource) col.getResource(id + XML_SUFFIX);
        col.close();

        if (r != null) {
            Unmarshaller u = jc.createUnmarshaller();
            UnmarshallerHandler handler = u.getUnmarshallerHandler();

            r.getContentAsSAX(handler);
            return (Bd) handler.getResult();
        }

        return null;
    }

    public List<Bd> searchFor(BdSearchBean searchType, Map<String, String> orderBy) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        StringBuilder query = new StringBuilder();
        
        query.append("for $bd in collection(\"bedetheque\")");
        String separator = " where ";
        
        if (StringUtils.isNotBlank(searchType.getTitre())) {
            params.put("titre", searchType.getTitre());
            query.append(separator).append("contains(upper-case($bd/bd:bd/bd:titre/text()), upper-case($titre))");
            separator = " and ";
        }
        if (StringUtils.isNotBlank(searchType.getEditeur())) {
            params.put("editeur", searchType.getEditeur());
            query.append(separator).append("compare(upper-case($bd/bd:bd/bd:editeur/text()), upper-case($editeur)) == 0");
            separator = " and ";
        }

        if (StringUtils.isNotBlank(searchType.getLangue())) {
            params.put("langue", searchType.getLangue());
            query.append(separator).append("compare(upper-case($bd/bd:bd/@bd:langue), upper-case($langue)) == 0");
            separator = " and ";
        }

        if (StringUtils.isNotBlank(searchType.getResume())) {
            params.put("resume", searchType.getResume());
            query.append(separator).append("contains(upper-case($bd/bd:bd/bd:resume/text()), upper-case($resume))");
            separator = " and ";
        }

        if (StringUtils.isNotBlank(searchType.getSerie())) {
            params.put("serie", searchType.getSerie());
            query.append(separator).append("compare(upper-case($bd/bd:bd/@bd:serie), upper-case($serie)) == 0");
            separator = " and ";
        }

        /**
         * A voir
         */
        if (CollectionUtils.isNotEmpty(searchType.getScenaristes().getScenariste())) {
            query.append(separator);
            setParameterList(query, params, searchType.getScenaristes().getScenariste(), "scenariste");
            separator = " and ";
        }

        if (CollectionUtils.isNotEmpty(searchType.getColoristes().getColoriste())) {
            query.append(separator);
            setParameterList(query, params, searchType.getColoristes().getColoriste(), "coloriste");
            separator = " and ";
        }

        if (CollectionUtils.isNotEmpty(searchType.getDessinateurs().getDessinateur())) {
            query.append(separator);
            setParameterList(query, params, searchType.getDessinateurs().getDessinateur(), "dessinateur");
            separator = " and ";
        }

        if (CollectionUtils.isNotEmpty(searchType.getEncrages().getEncrage())) {
            query.append(separator);
            setParameterList(query, params, searchType.getEncrages().getEncrage(), "encrage");
            separator = " and ";
        }

        if (CollectionUtils.isNotEmpty(searchType.getLettrages().getLettrage())) {
            query.append(separator);
            setParameterList(query, params, searchType.getLettrages().getLettrage(), "lettrage");
        }

        
        if (MapUtils.isNotEmpty(orderBy)) {
            separator = " order by ";
            query.append(" order by ");
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

        ResourceSet result = executeXQuery(query.toString(), params);
        Unmarshaller u = jc.createUnmarshaller();
        UnmarshallerHandler handler = u.getUnmarshallerHandler();
        ResourceIterator i = result.getIterator();
        List<Bd> l = new ArrayList<Bd>();
        System.out.println("result size : " + result.getSize());
        while (i.hasMoreResources()) {
            XMLResource r = (XMLResource) i.nextResource();
            r.getContentAsSAX(handler);
            l.add((Bd) handler.getResult());
        }

        return l;
    }

    private XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        now.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return now;
    }

    public boolean add(Bd bd) throws Exception {
        Collection col = getCollection();
        String id = col.createId();
        while (get(id) != null) {
            id = col.createId();
        }
        bd.setId(StringUtils.chomp(id, XML_SUFFIX));
        bd.setInsertedDate(getXMLGregorianCalendarNow());

        XMLResource resource = (XMLResource) col.createResource(id, XMLResource.RESOURCE_TYPE);
        Marshaller m = jc.createMarshaller();
        m.marshal(bd, resource.setContentAsSAX());
        col.storeResource(resource);
        col.close();
        return true;
    }

    public boolean delete(String id) throws Exception {
        Collection col = getCollection();
        XMLResource resource = (XMLResource) col.createResource(id + XML_SUFFIX, XMLResource.RESOURCE_TYPE);
        if (resource != null) {
            col.removeResource(resource);
            col.close();
            return true;
        }
        col.close();
        return false;
    }

    public boolean update(Bd bd) throws Exception {
        Collection col = getCollection();

        XMLResource resource = (XMLResource) col.createResource(bd.getId() + XML_SUFFIX, XMLResource.RESOURCE_TYPE);
        if (resource != null) {
            Marshaller m = jc.createMarshaller();
            m.marshal(bd, resource.setContentAsSAX());
            col.storeResource(resource);
            col.close();
            return true;
        }
        col.close();
        return false;

    }

    private void setParameterList(StringBuilder query, Map<String, String> params, List<IndividuType> l, String prefix) {
        int i = 0;
        query.append("(");
        String separator = "";
        for (IndividuType ind : l) {
            params.put(prefix + i, ind.getNom() + " " + ind.getPrenom());
            i++;
            //query.append(separator).append(/*set query*/);
            if (i <= 1) {
                separator = " or ";
            }
        }
        query.append(")");

    }
}

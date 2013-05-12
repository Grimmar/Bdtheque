package fr.univ_rouen.bd.model.dao;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.ColoristesType;
import fr.univ_rouen.bd.model.beans.DessinateursType;
import fr.univ_rouen.bd.model.beans.EncragesType;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.LettragesType;
import fr.univ_rouen.bd.model.beans.ScenaristesType;
import fr.univ_rouen.bd.model.beans.TomeType;
import fr.univ_rouen.bd.model.dao.exception.DAOConfigurationException;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author bissoqu1
 */
public class DAOFactory {

    private static final String PROPERTIES_FILE = "application.properties";
    private static final String PROPERTY_URI = "uri";
    private static final String PROPERTY_COLLECTION_NAME = "collection";
    private static final String PROPERTY_CLASS_NAME = "className";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_DATABASE_CREATE = "create-database";
    private static final String PROPERTY_NAMESPACE_VALUE = "namespace";
    private static final String PROPERTY_NAMESPACE_KEY = "bd";
    private static final String COLLECTION_MANAGEMENT_SERVICE_NAME = "CollectionManagementService";
    private static final String COLLECTION_MANAGEMENT_SERVICE_VERSION = "1.0";
    private static final String XQUERY_SERVICE_NAME = "XQueryService";
    private static final String XQUERY_SERVICE_VERSION = "1.0";
    private String uri;
    private String username;
    private String password;
    private String collection;
    private String namespaceValue;
    private static DAOFactory instance;
    private static JAXBContext jc;

    DAOFactory(String uri, String username, String password, String collection, String namespaceValue) {
        this.uri = uri;
        this.username = username;
        this.password = password;
        this.collection = collection;
        this.namespaceValue = namespaceValue;
    }

    private static InputStream getPropertyFile() throws DAOConfigurationException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable.");
        }

        return propertiesFile;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        if (DAOFactory.instance != null) {
            return DAOFactory.instance;
        }

        String className;

        Properties properties = new Properties();

        String username;
        String password;
        String uri;
        String collection;
        String namespaceValue;

        try {
            properties.load(getPropertyFile());

            uri = properties.getProperty(PROPERTY_URI);
            collection = properties.getProperty(PROPERTY_COLLECTION_NAME);
            className = properties.getProperty(PROPERTY_CLASS_NAME);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
            namespaceValue = properties.getProperty(PROPERTY_NAMESPACE_VALUE);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES_FILE, e);
        }

        try {
            /*Context ctx = new InitialContext();
             Context existCtx = (Context) ctx.lookup("java:comp/env/exist");
             Database database = (Database) existCtx.lookup("xmlDB");*/
            Class cl = Class.forName(className);
            Database database = (Database) cl.newInstance();
            database.setProperty(PROPERTY_DATABASE_CREATE, "true");
            DatabaseManager.registerDatabase(database);
            jc = JAXBContext.newInstance(Bd.class, ColoristesType.class,
                    DessinateursType.class, EncragesType.class, IndividuType.class,
                    LettragesType.class, ScenaristesType.class, TomeType.class);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        } catch (InstantiationException e) {
            throw new DAOConfigurationException("Un problème a eu lieu lors de l'instanciation de la bade de données exist.", e);
        } catch (XMLDBException e) {
            throw new DAOConfigurationException("La connexion avec la base de données n'a pu être établie", e);
        } catch (JAXBException e) {
            throw new DAOConfigurationException("La mise en place du contexte a échoué", e);
        } catch (IllegalAccessException e) {
            throw new DAOConfigurationException("La connexion avec la base de données n'a pu être établie", e);
        }

        DAOFactory.instance = new DAOFactory(uri, username, password, collection, namespaceValue);
        return DAOFactory.instance;
    }

    /*package*/ Collection getCollection() throws XMLDBException {
        return getOrCreateCollection(collection, 0);
    }

    private Collection getOrCreateCollection(String collection, int pathSegmentOffset) throws XMLDBException {
        // Récupération de la collection
        Collection col = DatabaseManager.getCollection(uri + collection, username, password);
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
                Collection start = DatabaseManager.getCollection(uri + path, username, password);
                if (start == null) {
                    //collection does not exist, so create
                    if (!path.substring(0, path.lastIndexOf("/")).isEmpty()) {
                        String parentPath = path.substring(0, path.lastIndexOf("/"));
                        Collection parent = DatabaseManager.getCollection(uri + parentPath);
                        CollectionManagementService mgt =
                                (CollectionManagementService) parent.getService(COLLECTION_MANAGEMENT_SERVICE_NAME, COLLECTION_MANAGEMENT_SERVICE_VERSION);
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

    public static XMLGregorianCalendar getXMLGregorianCalendar(GregorianCalendar gc)
            throws DatatypeConfigurationException {
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gc);
        now.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return now;
    }

    /**
     *
     * @return @throws Exception
     */
    public ResourceSet executeXQuery(String query, Map<String, String> params) throws DAOException {
        try {
            Collection col = getCollection();
            XQueryService xqs = (XQueryService) col.getService(XQUERY_SERVICE_NAME, XQUERY_SERVICE_VERSION);
            xqs.setNamespace(PROPERTY_NAMESPACE_KEY, namespaceValue);
            xqs.setProperty("indent", "yes");

            for (String s : params.keySet()) {
                xqs.declareVariable(s, params.get(s));
            }
            CompiledExpression compiled = xqs.compile(query);
            ResourceSet result = xqs.execute(compiled);
            col.close();
            return result;
        } catch (XMLDBException e) {
            throw new DAOException("L'execution de la requête a échoué", e);
        }
    }

    public JAXBContext getJAXBContext() {
        return jc;
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
    public BdDao getBdDao() {
        return new BdDao(this);
    }
}

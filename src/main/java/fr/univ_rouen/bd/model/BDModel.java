package fr.univ_rouen.bd.model;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author bissoqu1
 */
public class BDModel {

    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String driver = "org.exist.xmldb.DatabaseImpl";

    public BDModel() throws Exception {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
         database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    public Collection getCollection() throws XMLDBException {
        Collection col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bd");
        return col;
    }

    public String executeXQuery() throws Exception {
        XPathQueryService service = (XPathQueryService) getCollection().getService("XPathQueryService", "1.0");
        service.setNamespace("bd", "http://univ-rouen.fr/bd");
        service.setProperty("indent", "yes");
        ResourceSet result = service.query("/bd:bd");
        ResourceIterator i = result.getIterator();
        Resource r = null;
        while (i.hasMoreResources()) {
            r = i.nextResource();
        }
        return r == null ? "test" :  ((String) r.getContent());
    }
}

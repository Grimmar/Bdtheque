package fr.univ_rouen.bd.model;

import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author bissoqu1
 */
public class DBManager {

    /*Everytime, test if isopen collection*/
    private final XQueryService xqs;
    Properties properties;

    public DBManager() throws Exception {
        properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("application.properties");
        properties.load(inputStream);
        /*Context ctx = new InitialContext();
        Context existCtx = (Context) ctx.lookup("java:comp/env/exist");
        Database database = (Database) existCtx.lookup("xmlDB");*/
        String driver = properties.getProperty("ClassName");
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = getBdCollection();
        xqs = (XQueryService) col.getService("XQueryService", "1.0");
        xqs.setNamespace("bd", properties.getProperty("namespace"));
        xqs.setProperty("indent", "yes");
    }

    private Collection getBdCollection() throws XMLDBException {
        return DatabaseManager.getCollection(properties.getProperty("URI") + properties.getProperty("Collection"), properties.getProperty("Username"), properties.getProperty("Password"));
    }

    /**
     *
     * @return @throws Exception
     */
    private ResourceSet executeXQuery(CompiledExpression ce/*, map*/) throws XMLDBException {
        return xqs.execute(ce);
    }

    public int count() throws Exception {
        Collection col = getBdCollection();
        return col.getResourceCount();
    }

    public Bd get(String id) throws Exception {
        Collection col = getBdCollection();
        XMLResource r = (XMLResource) col.getResource(id + ".xml");
        if (r != null) {
            JAXBContext jc = JAXBContext.newInstance(Bd.class, ColoristesType.class, DessinateursType.class, EncragesType.class, IndividuType.class, LettragesType.class, ScenaristesType.class, TomeType.class);
            Unmarshaller u = jc.createUnmarshaller();
            UnmarshallerHandler handler = u.getUnmarshallerHandler();
            r.getContentAsSAX(handler);
            return (Bd) handler.getResult();
        }
        return null;
    }

    /*public List<Bd> search() throws Exception {
     String query = "declare variable $id as xs:string external; /bd:bd";
     CompiledExpression compiled = xqs.compile(query);
     //xqs.declareVariable("id", id);
     ResourceSet result = executeXQuery(compiled);
     JAXBContext jc = JAXBContext.newInstance(Bd.class, ColoristesType.class, DessinateursType.class, EncragesType.class, IndividuType.class, LettragesType.class, ScenaristesType.class, TomeType.class);
     Unmarshaller u = jc.createUnmarshaller();
     UnmarshallerHandler handler = u.getUnmarshallerHandler();
     ResourceIterator i = result.getIterator();
     List<Bd> l = new ArrayList<Bd>();
     if (i.hasMoreResources()) {
     XMLResource r = (XMLResource) i.nextResource();
     r.getContentAsSAX(handler);
     l.add((Bd) handler.getResult());
     }
     return l;
     }*/
    private XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }

    public boolean add(Bd bd) throws Exception {
        Collection col = getBdCollection();
        String id = col.createId();
        while (get(id) != null) {
            id = col.createId();
        }
        bd.setId(id.replace(".xml", ""));
        bd.setInsertedDate(getXMLGregorianCalendarNow());

        XMLResource resource = (XMLResource) col.createResource(id, XMLResource.RESOURCE_TYPE);
        JAXBContext jc = JAXBContext.newInstance(Bd.class, ColoristesType.class, DessinateursType.class, EncragesType.class, IndividuType.class, LettragesType.class, ScenaristesType.class, TomeType.class);
        Marshaller m = jc.createMarshaller();
        m.marshal(bd, resource.setContentAsSAX());
        col.storeResource(resource);
        return true;
    }

    public boolean delete(Bd bd) throws Exception {
        Collection col = getBdCollection();
        XMLResource resource = (XMLResource) col.createResource(bd.getId() + ".xml", XMLResource.RESOURCE_TYPE);
        //TODO if null
        col.removeResource(resource);
        return true;
    }

    public boolean update(Bd bd) throws Exception {
        Collection col = getBdCollection();

        XMLResource resource = (XMLResource) col.createResource(bd.getId() + ".xml", XMLResource.RESOURCE_TYPE);
        //TODO if null
        JAXBContext jc = JAXBContext.newInstance(Bd.class, ColoristesType.class, DessinateursType.class, EncragesType.class, IndividuType.class, LettragesType.class, ScenaristesType.class, TomeType.class);
        Marshaller m = jc.createMarshaller();
        m.marshal(bd, resource.setContentAsSAX());
        col.storeResource(resource);
        return true;
    }
}

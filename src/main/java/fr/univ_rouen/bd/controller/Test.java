package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.Bd;
import fr.univ_rouen.bd.model.BdSearchBean;
import fr.univ_rouen.bd.model.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.fop.apps.FOPException;

/**
 *
 * @author David
 */
public class Test extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FOPException {
        response.setContentType("text/html;charset=UTF-8");


    }

    private XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DBManager model = DBManager.getInstance();
            PrintWriter out = response.getWriter();

            out.println("Hello World");
/*            Bd bd = new Bd();
            bd.setIsbn("6565684985");
            bd.setTitre("Une ressource");

            bd.setParution(getXMLGregorianCalendarNow());
            bd.setEditeur("un editeur");
            bd.setResume("un resume");
            bd.setFormat("un format");
            bd.setCreationDate(getXMLGregorianCalendarNow());
            bd.setImage("un editeur");
            bd.setScenaristes(null);
            bd.setDessinateurs(null);
            bd.setSerie("One-Shot");
            bd.setPlanches(BigInteger.valueOf(40));
            bd.setLangue("FR");
            out.println(model.add(bd));*/

            BdSearchBean searchBean = new BdSearchBean();
            searchBean.setTitre("ress");
            System.out.println(model.searchFor(searchBean, null).get(0).getId());
            
           
            searchBean.setTitre("resss");
            System.out.println(model.searchFor(searchBean, null).size());
            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*FopFactory fopFactory = FopFactory.newInstance();
         OutputStream out = null;

         TransformerFactory factory = TransformerFactory.newInstance();

         String realContextPath = getServletContext().getRealPath("/WEB-INF/bd-fo.xsl");
         Source src = new StreamSource(new File(realContextPath));
         Transformer transformer = null;
         try {
         transformer = factory.newTransformer(src); // identity transformer
         } catch (TransformerConfigurationException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         }

         Map<String, File> fichier = new HashMap<String, File>();
         fichier.put("Hellblazer Death and cigarettes", new File("/WEB-INF/bd.xml"));
         fichier.put("Batman-Sombre reflet", new File("/WEB-INF/bd1.xml"));
         for (String name : fichier.keySet()) {
         File f = new File("WEB-INF/" + name + ".pdf");
         System.out.println(f.getAbsolutePath());
         f.createNewFile();
         out = new BufferedOutputStream(new FileOutputStream(f));
         try {
         Fop fop = fopFactory.newFop("application/pdf", out);

         StreamSource source2 = new StreamSource(fichier.get(name));
         // Resulting SAX events (the generated FO) must be piped through to FOP
         Result res = new SAXResult(fop.getDefaultHandler());

         transformer.transform(source2, res);
         } catch (TransformerException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FOPException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         }

         out.close();
         }*/
        getServletContext().getRequestDispatcher("/jsp/test2.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FOPException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

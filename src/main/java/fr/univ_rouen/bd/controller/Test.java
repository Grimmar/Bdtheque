package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
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

/**
 *
 * @author David
 */
public class Test extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    private BdDao bdDao;

    @Override
    public void init() throws ServletException {
        this.bdDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBdDao();
    }
  
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
            PrintWriter out = response.getWriter();

            out.println("Hello World");
            Bd bd = new Bd();
            bd.setIsbn("6565684985");
            bd.setTitre("Une ressource");

            bd.setParution(DAOFactory.getXMLGregorianCalendar(new GregorianCalendar()));
            bd.setEditeur("un editeur");
            bd.setResume("un resume");
            bd.setFormat("un format");
            bd.setCreationDate(DAOFactory.getXMLGregorianCalendar(new GregorianCalendar()));
            bd.setImage("http://www.romainblachier.fr/wp-content/uploads/2013/03/google.jpg");
            bd.setScenaristes(null);
            bd.setDessinateurs(null);
            bd.setSerie("One-Shot");
            bd.setPlanches(BigInteger.valueOf(40));
            bd.setLangue("FR");
            out.println(bdDao.add(bd));


        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/test2.jsp").forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.DBManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class ShowServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/jsp/show.jsp";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String ATTR_BD = "bd";
    private static final String ATTR_RESOURCE = "resource";

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
            throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String resource_id = request.getParameter(ATTR_RESOURCE);
        if (StringUtils.isBlank(resource_id)) {
            //TODO throw error
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Le code " + resource_id + " est inexistant");
        } else {

            Bd bd = null;
            try {
                bd = DBManager.getInstance().get(resource_id);
            } catch (Exception ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (bd == null) {
                //TODO throw error
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Le code " + resource_id + " est inexistant");
            } else {
                request.setAttribute(ATTR_BD, bd);

                this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
            }

        }
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Consultation of a resource";
    }// </editor-fold>
}

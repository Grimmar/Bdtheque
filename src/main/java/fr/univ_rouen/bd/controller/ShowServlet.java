package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class ShowServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/jsp/bd/show.jsp";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String SESSION_NOTICE = "notice";
    private static final String ATTR_BD = "bd";
    private static final String ATTR_RESOURCE = "resource";
    private static final String ATTR_MESSAGE = "message";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private BdDao bdDao;

    @Override
    public void init() throws ServletException {
        this.bdDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBdDao();
    }

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
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Il est obligatoire de passer le nom d'une ressource en paramètre pour pouvoir accéder à cette page.");
        } else {

            try {
                Bd bd = bdDao.get(resource_id);
                if (bd == null) {
                    request.setAttribute(ATTR_MESSAGE, "La BD ayant l'identifiant '" + resource_id + "' n'existe pas dans notre application");
                } else {
                    request.setAttribute(ATTR_BD, bd);
                }
            } catch (DAOException e) {

                request.setAttribute(ATTR_MESSAGE, "Une erreur a eu lieu lors de l'accès aux données.");
            }

            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_NOTICE) != null) {
                String notice = (String) session.getAttribute(SESSION_NOTICE);
                session.setAttribute(SESSION_NOTICE, null);
                request.setAttribute(SESSION_NOTICE, notice);
            }
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
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

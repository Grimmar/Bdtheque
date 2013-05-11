package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class DeleteServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/jsp/bd/search.jsp";
    private static final String REDIRECT_URL = "/home";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String RESOURCE_ATTR = "resource";
    private static final String BD_ATTR = "bd";
    private static final String REQUEST_NOTICE = "notice";
    private static final String REQUEST_ERROR = "error";
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
        String id = request.getParameter(RESOURCE_ATTR);
        Bd bd = null;
        if (StringUtils.isNotEmpty(id)) {
            bd = bdDao.get(id);
        }

        if (bd != null) {
            request.setAttribute(BD_ATTR, bd);
            bdDao.delete(id);
            request.setAttribute(REQUEST_NOTICE, "La bd " + bd.getTitre() + " a été supprimée.");
        } else {
            request.setAttribute(REQUEST_ERROR, "La bd que vous essayer de supprimer n'existe pas.");
        }
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
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
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Suppression d'une bd";
    }
}

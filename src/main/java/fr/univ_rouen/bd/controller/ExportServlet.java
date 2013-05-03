package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import fr.univ_rouen.bd.model.export.ExportBd;
import fr.univ_rouen.bd.model.export.exception.ExportBdException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class ExportServlet extends HttpServlet {

    private static final String ATTR_RESOURCE = "resource";
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
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = getServletContext();
        
        String resource_id = request.getParameter(ATTR_RESOURCE);
        Bd bd = null;

        if (StringUtils.isBlank(resource_id)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Il est obligatoire de passer le nom d'une ressource en paramètre pour pouvoir accéder à cette page.");
        } else {

            try {
                bd = bdDao.get(resource_id);
                if (bd == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La BD ayant l'identifiant '" + resource_id + "' n'existe pas dans notre application");
                }
            } catch (DAOException e) {
                Logger.getLogger(ExportServlet.class.getName()).log(Level.SEVERE, null, e);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Une erreur a eu lieu lors de l'accès aux données.");
            }
        }
        ExportBd exportBd = new ExportBd(request, response);
        try {
            exportBd.export(bd, context);
        } catch (ExportBdException ex) {
            Logger.getLogger(ExportServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Une erreur a eu lieu lors de la création du fichier à exporter.");
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
        return "Short description";
    }// </editor-fold>
}

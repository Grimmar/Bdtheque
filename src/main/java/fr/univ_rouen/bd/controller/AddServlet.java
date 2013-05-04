package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.forms.UploadForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bissoqu1
 */
public class AddServlet extends HttpServlet {

    private static final String SESSION_NOTICE = "notice";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String REDIRECT_URL = "/show/";
    private static final String VIEW = "/WEB-INF/jsp/bd/addBd.jsp";
    private static final String UPLOAD_VIEW = "/WEB-INF/jsp/bd/upload.jsp";
    private static final String ATTR_FORM = "form";
    private static final String ATTR_BD = "bd";
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
        response.setContentType("text/html;charset=UTF-8");
        this.getServletContext().getRequestDispatcher(UPLOAD_VIEW).forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        UploadForm form = new UploadForm(bdDao);
        Bd bd = form.validateForm(request);

        request.setAttribute(ATTR_FORM, form);
        request.setAttribute(ATTR_BD, bd);
        
        HttpSession session = request.getSession();
        
        if (form.isValid()) {
            session.setAttribute(SESSION_NOTICE, "La bd a bien été ajoutée.");
            
            String context = this.getServletContext().getContextPath();
            response.sendRedirect(context + REDIRECT_URL + bd.getId());
        } else {
            this.getServletContext().getRequestDispatcher(UPLOAD_VIEW).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet used to add a new bd";
    }
}

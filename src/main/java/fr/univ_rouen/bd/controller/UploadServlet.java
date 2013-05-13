package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.forms.Form;
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
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_VIEW = "/WEB-INF/jsp/bd/uploadForm.jsp";
    private static final String VIEW = "/WEB-INF/jsp/bd/add.jsp";
    private static final String SESSION_NOTICE = "notice";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String REDIRECT_URL = "/show/";
    private static final String ATTR_FORM = "form";
    private static final String ATTR_BD = "bd";
    private static final String ATTR_FORM_NAME = "formName";
    private static final String FORM_NAME = "upload";
    private BdDao bdDao;

    @Override
    public void init() throws ServletException {
        this.bdDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBdDao();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ATTR_FORM_NAME, FORM_NAME);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        Form<Bd> form = new UploadForm(bdDao);

        Bd bd = form.validateForm(request);

        request.setAttribute(ATTR_FORM, form);
        request.setAttribute(ATTR_BD, bd);

        HttpSession session = request.getSession();

        if (form.isValid()) {
            session.setAttribute(SESSION_NOTICE, "La bd suivante vient d'être ajoutée.");
            String context = this.getServletContext().getContextPath();
            response.sendRedirect(context + REDIRECT_URL + bd.getId());
        } else {
            processRequest(request, response);
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
    }
}

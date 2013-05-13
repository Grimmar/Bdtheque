package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.dao.exception.DAOException;
import fr.univ_rouen.bd.model.forms.BdForm;
import fr.univ_rouen.bd.model.forms.UpdateForm;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class UpdateServlet extends HttpServlet {

    private static final String SESSION_NOTICE = "notice";
    private static final String REDIRECT_URL = "/show/";
    private static final String ATTR_FORM = "form";
    private static final String ATTR_BD = "bd";
    private static final String ATTR_RESOURCE = "resource";
    private static final String VIEW = "/WEB-INF/jsp/bd/updateBd.jsp";
    private static final String DEPOT_LEGAL_ATTR = "depotLegal";
    private static final String FIN_IMPRESSION_ATTR = "finImpression";
    private static final String PARUTION_ATTR = "parution";
    private static final String CREATION_DATE_ATTR = "creationDate";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private BdDao bdDao;

    @Override
    public void init() throws ServletException {
        this.bdDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBdDao();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String resource_id = request.getParameter(ATTR_RESOURCE);
        if (StringUtils.isBlank(resource_id)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Il est obligatoire de passer le nom d'une ressource en paramètre pour pouvoir accéder à cette page.");
        } else {

            try {
                Bd bd = bdDao.get(resource_id);
                if (bd == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La BD ayant l'identifiant '" + resource_id + "' n'existe pas dans notre application");
                } else {
                    request.setAttribute(ATTR_BD, bd);
                    setAttributesInRequest(request, bd);
                    processRequest(request, response);
                }
            } catch (DAOException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Une erreur a eu lieu lors de l'accès aux données.");
            }
        }

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

        boolean isbnUnicity = true;
        String resource_id = request.getParameter(ATTR_RESOURCE);
        if (StringUtils.isBlank(resource_id)) {
            isbnUnicity = true;
        } else {

            try {
                Bd bd = bdDao.get(resource_id);
                if (bd != null) {
                    isbnUnicity = false;
                }
            } catch (DAOException e) {
                isbnUnicity = true;
            }
        }
        BdForm form = new UpdateForm(bdDao, resource_id);
        form.setUniqueIsbn(isbnUnicity);
        Bd bd = form.validateForm(request);

        request.setAttribute(ATTR_FORM, form);
        request.setAttribute(ATTR_BD, bd);

        HttpSession session = request.getSession();
        if (form.isValid()) {
            session.setAttribute(SESSION_NOTICE, "La bd a été modifiée.");
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
        return "Servlet de modification d'une bd";
    }

    private void setAttributesInRequest(HttpServletRequest request, Bd bd) {
        if (bd != null) {
            if (bd.getCreationDate() != null) {
                GregorianCalendar gc = bd.getCreationDate().toGregorianCalendar();
                Date d = gc.getTime();
                request.setAttribute(CREATION_DATE_ATTR, d);
            }

            if (bd.getParution() != null) {
                GregorianCalendar gc = bd.getParution().toGregorianCalendar();
                Date d = gc.getTime();
                request.setAttribute(PARUTION_ATTR, d);
            }
            //TOdO format
            request.setAttribute(DEPOT_LEGAL_ATTR, bd.getDepotLegal());
            request.setAttribute(FIN_IMPRESSION_ATTR, bd.getFinImpression());

            if (bd.getScenaristes() != null
                    && CollectionUtils.isNotEmpty(bd.getScenaristes().getScenariste())) {
                setStringInRequest(request, bd.getScenaristes().getScenariste(), "scenaristesString");
            }
            if (bd.getDessinateurs() != null
                    && CollectionUtils.isNotEmpty(bd.getDessinateurs().getDessinateur())) {
                setStringInRequest(request, bd.getDessinateurs().getDessinateur(), "dessinateursString");
            }
            if (bd.getColoristes() != null
                    && CollectionUtils.isNotEmpty(bd.getColoristes().getColoriste())) {
                setStringInRequest(request, bd.getColoristes().getColoriste(), "coloristesString");
            }
            if (bd.getEncrages() != null
                    && CollectionUtils.isNotEmpty(bd.getEncrages().getEncrage())) {
                setStringInRequest(request, bd.getEncrages().getEncrage(), "encreursString");
            }
            if (bd.getLettrages() != null
                    && CollectionUtils.isNotEmpty(bd.getLettrages().getLettrage())) {
                setStringInRequest(request, bd.getLettrages().getLettrage(), "lettreursString");
            }
        }
    }

    private void setStringInRequest(HttpServletRequest request, List<IndividuType> inds, String attribute) {
        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (IndividuType ind : inds) {
            sb.append(separator).append(ind.getNom()).append(" ").append(ind.getPrenom());
            separator = ";";
        }
        request.setAttribute(attribute, sb.toString());
    }
}

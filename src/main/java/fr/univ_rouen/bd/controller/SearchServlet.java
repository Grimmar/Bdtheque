package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.DessinateursType;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.ScenaristesType;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class SearchServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/jsp/bd/search.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String SESSION_NOTICE = "notice";
    private static final String SESSION_ERROR = "error";
    public static final String ATTR_LIST_BD = "searchBd";
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
        Map<String, String> orderBy = new HashMap<String, String>();
        orderBy.put("insertedDate", "ascending");
        BdSearchBean searchAttributes = new BdSearchBean();

        for (String key : request.getParameterMap().keySet()) {
            System.out.println(key + " " + request.getParameter(key));
        }
        String searchTitre = request.getParameter("search-titre");
        String searchEditeur = request.getParameter("search-editeur");
        String searchResume = request.getParameter("search-resume");
        String searchScenaristes = request.getParameter("scenariste");
        String searchDessinateur = request.getParameter("dessinateur");

        ScenaristesType lstScenariste = new ScenaristesType();
        DessinateursType lstDessinateur = new DessinateursType();

        if (StringUtils.isNotBlank(searchTitre)) {
            searchAttributes.setTitre(searchTitre);
            request.setAttribute("searchTitre", searchTitre);
        }
        if (StringUtils.isNotBlank(searchEditeur)) {
            searchAttributes.setEditeur(searchEditeur);
            request.setAttribute("searchEditeur", searchEditeur);
        }
        if (StringUtils.isNotBlank(searchResume)) {
            searchAttributes.setResume(searchResume);
            request.setAttribute("searchResume", searchResume);
        }
        IndividuType ind = null;
        if (StringUtils.isNotBlank(searchScenaristes)) {
            String[] scenariste = searchScenaristes.split(";");
            for (int i = 0; i < scenariste.length; i++) {
                String[] individu = scenariste[i].split(" ");
                if (individu.length == 2) {
                    ind = new IndividuType();
                    ind.setNom(individu[0]);
                    ind.setPrenom(individu[1]);
                    lstScenariste.getScenariste().add(ind);
                } else {
                    ind = new IndividuType();
                    ind.setNom(individu[0]);
                    lstScenariste.getScenariste().add(ind);
                }
            }
            searchAttributes.setScenaristes(lstScenariste);
        }

        if (StringUtils.isNotBlank(searchDessinateur)) {
            String[] dessinateur = searchDessinateur.split(";");
            for (int i = 0; i < dessinateur.length; i++) {
                String[] individu = dessinateur[i].split(" ");
                if (individu.length == 2) {
                    ind = new IndividuType();
                    ind.setNom(individu[0]);
                    ind.setPrenom(individu[1]);
                    lstDessinateur.getDessinateur().add(ind);
                } else {
                    ind = new IndividuType();
                    ind.setNom(individu[0]);
                    lstDessinateur.getDessinateur().add(ind);
                }
            }
            searchAttributes.setDessinateurs(lstDessinateur);
        }

        request.setAttribute("scenaristes", lstScenariste);
        request.setAttribute("dessinateurs", lstDessinateur);
        List<Bd> searchBd = bdDao.searchFor(searchAttributes, orderBy);
        request.setAttribute(ATTR_LIST_BD, searchBd);

        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_NOTICE) != null) {
            String notice = (String) session.getAttribute(SESSION_NOTICE);
            session.setAttribute(SESSION_NOTICE, null);
            request.setAttribute(SESSION_NOTICE, notice);
        }
        if (session.getAttribute(SESSION_ERROR) != null) {
            String error = (String) session.getAttribute(SESSION_ERROR);
            session.setAttribute(SESSION_ERROR, null);
            request.setAttribute(SESSION_ERROR, error);
        }

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
    }
}

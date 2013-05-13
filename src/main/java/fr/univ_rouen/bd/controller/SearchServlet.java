package fr.univ_rouen.bd.controller;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.forms.SearchForm;
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
    public static final String NB_TOTAL = "nbResult";
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

        SearchForm sh = new SearchForm();
        BdSearchBean searchAttributes = sh.validateForm(request);
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            searchAttributes.setPagination(Integer.parseInt(request.getParameter("page")));
        } else {
            searchAttributes.setPagination(1);
        }
        List<Bd> searchBd = bdDao.searchFor(searchAttributes, orderBy);
        int nbPage = bdDao.countSearch(searchAttributes, orderBy)/BdDao.NB_RESULT_PER_PAGE+1;
        request.setAttribute(NB_TOTAL,nbPage );
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
        request.setAttribute("page", searchAttributes.getPagination());
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

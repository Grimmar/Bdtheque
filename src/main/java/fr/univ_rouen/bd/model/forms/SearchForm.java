/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_rouen.bd.model.forms;

import fr.univ_rouen.bd.model.beans.ColoristesType;
import fr.univ_rouen.bd.model.beans.DessinateursType;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.LettragesType;
import fr.univ_rouen.bd.model.beans.ScenaristesType;
import fr.univ_rouen.bd.model.beans.search.BdSearchBean;
import fr.univ_rouen.bd.model.beans.search.SearchBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author David
 */
public class SearchForm extends AbstractForm<SearchBean> {
    
    private static final String SEARCH_TITRE = "searchTitre";
    private static final String SEARCH_EDITEUR = "searchEditeur";
    private static final String SEARCH_RESUME = "searchResume";
    private static final String SEARCH_SCENARISTES = "scenariste";
    private static final String SEARCH_DESSINATEURS = "dessinateur";
    private static final String SEARCH_COLORISTES = "coloriste";
    private static final String SEARCH_LETTRAGES = "lettrage";
    private static final String SEARCH_SERIE = "searchSerie";
    private static final String SEARCH_LANGUE = "searchLangue";
    
    @Override
    public BdSearchBean validateForm(HttpServletRequest request) {

        BdSearchBean search = getSearchFromRequest(request);
        request.setAttribute(SEARCH_TITRE, search.getTitre());
        request.setAttribute(SEARCH_EDITEUR, search.getEditeur());
        request.setAttribute(SEARCH_RESUME, search.getResume());
        setStringInRequest(request, search.getScenaristes().getScenariste(), SEARCH_SCENARISTES);
        setStringInRequest(request, search.getColoristes().getColoriste(), SEARCH_COLORISTES);
        setStringInRequest(request, search.getDessinateurs().getDessinateur(), SEARCH_DESSINATEURS);
        setStringInRequest(request, search.getLettrages().getLettrage(), SEARCH_LETTRAGES);
        request.setAttribute(SEARCH_SERIE, search.getSerie());
        request.setAttribute(SEARCH_LANGUE, search.getLangue());
        return search;
    }
    
    private BdSearchBean getSearchFromRequest(HttpServletRequest request) {
        BdSearchBean searchAttributes = new BdSearchBean();
        
        String searchTitre = request.getParameter(SEARCH_TITRE);
        String searchEditeur = request.getParameter(SEARCH_EDITEUR);
        String searchResume = request.getParameter(SEARCH_RESUME);
        String searchScenaristes = request.getParameter(SEARCH_SCENARISTES);
        String searchDessinateurs = request.getParameter(SEARCH_DESSINATEURS);
        String searchColoristes = request.getParameter(SEARCH_COLORISTES);
        String searchLettrages = request.getParameter(SEARCH_LETTRAGES);
        String searchSerie = request.getParameter(SEARCH_SERIE);
        String searchLangue = request.getParameter(SEARCH_LANGUE);
        
        ScenaristesType lstScenariste = new ScenaristesType();
        DessinateursType lstDessinateur = new DessinateursType();
        ColoristesType lstColoriste = new ColoristesType();
        LettragesType lstLettrage = new LettragesType();
        
        if (StringUtils.isNotBlank(searchTitre)) {
            searchAttributes.setTitre(searchTitre);
        }
        if (StringUtils.isNotBlank(searchEditeur)) {
            searchAttributes.setEditeur(searchEditeur);
        }
        if (StringUtils.isNotBlank(searchResume)) {
            searchAttributes.setResume(searchResume);
        }
        List<IndividuType> lstInd = null;
        
        if (StringUtils.isNotBlank(searchScenaristes)) {
            lstInd = getIndividuFromRequest(request, SEARCH_SCENARISTES);
            lstScenariste.getScenariste().addAll(lstInd);
            searchAttributes.setScenaristes(lstScenariste);
        }
        if (StringUtils.isNotBlank(searchDessinateurs)) {
            lstInd = getIndividuFromRequest(request, SEARCH_DESSINATEURS);
            lstDessinateur.getDessinateur().addAll(lstInd);
            searchAttributes.setDessinateurs(lstDessinateur);
        }
        if (StringUtils.isNotBlank(searchColoristes)) {
            lstInd = getIndividuFromRequest(request, SEARCH_COLORISTES);
            lstColoriste.getColoriste().addAll(lstInd);
            searchAttributes.setColoristes(lstColoriste);
        }
        if (StringUtils.isNotBlank(searchLettrages)) {
            lstInd = getIndividuFromRequest(request, SEARCH_LETTRAGES);
            lstLettrage.getLettrage().addAll(lstInd);
            searchAttributes.setLettrages(lstLettrage);
        }
        if (StringUtils.isNotBlank(searchSerie)) {
            searchAttributes.setSerie(searchSerie);
        }
        if (StringUtils.isNotBlank(searchLangue)) {
            searchAttributes.setLangue(searchLangue);
        }
        return searchAttributes;
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
    
    private List<IndividuType> getIndividuFromRequest(HttpServletRequest request, String s) {
        List<IndividuType> inds = new ArrayList<IndividuType>();
        String individus = request.getParameter(s);
        if (StringUtils.isNotBlank(individus.trim())) {
            String[] split = individus.split(";");
            for (String string : split) {
                String[] ind = string.split(" ");
                IndividuType i = new IndividuType();
                i.setNom(ind[0]);
                if (ind.length > 1) {
                    i.setPrenom(ind[1]);
                }
                inds.add(i);
            }
        }
        return inds;
    }
}

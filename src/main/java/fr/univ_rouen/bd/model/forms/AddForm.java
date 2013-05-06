package fr.univ_rouen.bd.model.forms;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.ColoristesType;
import fr.univ_rouen.bd.model.beans.DessinateursType;
import fr.univ_rouen.bd.model.beans.EncragesType;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.LettragesType;
import fr.univ_rouen.bd.model.beans.ScenaristesType;
import fr.univ_rouen.bd.model.beans.TomeType;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.validation.BdValidator;
import fr.univ_rouen.bd.model.validation.Validator;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class AddForm extends AbstractForm<Bd> {

    private static final String ISBN_ATTR = "isbn";
    private static final String TITRE_ATTR = "titre";
    private static final String SERIE_ATTR = "serie";
    private static final String PLANCHES_ATTR = "planches";
    private static final String LANGUE_ATTR = "langue";
    private static final String DEPOT_LEGAL_ATTR = "depotLegal";
    private static final String FIN_IMPRESSION_ATTR = "finImpression";
    private static final String PARUTION_ATTR = "parution";
    private static final String EDITEUR_ATTR = "editeur";
    private static final String RESUME_ATTR = "resume";
    private static final String FORMAT_ATTR = "format";
    private static final String CREATION_DATE_ATTR = "creationDate";
    private static final String IMAGE_ATTR = "image";
    private static final String TOME_NUMERO_ATTR = "numero";
    private static final String TOME_INFORMATIONS_ATTR = "informations";
    private static final String SCENARISTE_ATTR = "scenariste";
    private static final String DESSINATEUR_ATTR = "dessinateur";
    private static final String COLORISTE_ATTR = "coloriste";
    private static final String LETTRAGE_ATTR = "lettrage";
    private static final String ENCRAGE_ATTR = "encrage";
    private static final String PARTIAL_DATE_PATTERN = "((([1-9][0-9]{3}))/((0[1-9])|(1[0-2])))|(((([1-9][0-9]{3}))/((0[1-9])|(1[012])))/((0[1-9])|([12][0-9])|(3[0-1])))";
    private final BdDao bdDao;

    public AddForm(BdDao dao) {
        this.bdDao = dao;
    }

    @Override
    public Bd validateForm(HttpServletRequest request) {
        Bd bd = getBdFromRequest(request);
        Validator<Bd> validator = new BdValidator("bd", bdDao);
        if (!validator.isValid(bd)) {
            Map<String, List<String>> map = validator.getValidationMessages();
            for (String s : map.keySet()) {
                addAllErrors(s, map.get(s));
            }
        }
        if (isValid()) {
            bdDao.add(bd);
            setResult("Succès lors de l'ajout de la nouvelle bd.");
        } else {
            setResult("Echec lors de l'ajout de la nouvelle bd.");
        }
        return bd;
    }

    private Bd getBdFromRequest(HttpServletRequest request) {
        Bd bd = new Bd();
        bd.setIsbn(request.getParameter(ISBN_ATTR));
        bd.setTitre(request.getParameter(TITRE_ATTR));
        bd.setSerie(request.getParameter(SERIE_ATTR));
        bd.setPlanches(new BigInteger(request.getParameter(PLANCHES_ATTR)));
        bd.setLangue(request.getParameter(LANGUE_ATTR));
        bd.setEditeur(request.getParameter(EDITEUR_ATTR));
        bd.setResume(request.getParameter(RESUME_ATTR));
        bd.setFormat(request.getParameter(FORMAT_ATTR));
        bd.setImage(request.getParameter(IMAGE_ATTR));

        if (StringUtils.isNotBlank(request.getParameter(TOME_NUMERO_ATTR))) {
            TomeType tome = new TomeType();
            tome.setNumero(Float.parseFloat(request.getParameter(TOME_NUMERO_ATTR)));
            if (StringUtils.isNotBlank(request.getParameter(TOME_INFORMATIONS_ATTR))) {
                tome.setInformations(request.getParameter(TOME_INFORMATIONS_ATTR));
            }
            bd.setTome(tome);
        }

        if (request.getParameter(DEPOT_LEGAL_ATTR).matches(PARTIAL_DATE_PATTERN)) {
            String s = StringUtils.reverse(request.getParameter(DEPOT_LEGAL_ATTR));
            bd.setDepotLegal(s.replace("/", "-"));
        } else {
            bd.setDepotLegal("");
        }

        if (request.getParameter(FIN_IMPRESSION_ATTR).matches(PARTIAL_DATE_PATTERN)) {
            String s = StringUtils.reverse(request.getParameter(FIN_IMPRESSION_ATTR));
            bd.setDepotLegal(s.replace("/", "-"));
        } else {
            bd.setDepotLegal("");
        }


        XMLGregorianCalendar creationDate = null;
        XMLGregorianCalendar parutionDate = null;
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Calendar c = GregorianCalendar.getInstance();
        try {
            if (StringUtils.isNotBlank(request.getParameter(CREATION_DATE_ATTR))) {
                Date date = sdf.parse(request.getParameter(CREATION_DATE_ATTR));
                GregorianCalendar gc = (GregorianCalendar) c;
                gc.setTime(date);
                creationDate = DAOFactory.getXMLGregorianCalendar(gc);
                System.out.println(creationDate);
            }
            if (StringUtils.isNotBlank(request.getParameter(PARUTION_ATTR))) {
                Date date = sdf.parse(request.getParameter(PARUTION_ATTR));
                GregorianCalendar gc = (GregorianCalendar) c;
                gc.setTime(date);
                parutionDate = DAOFactory.getXMLGregorianCalendar(gc);
            }
        } catch (ParseException ex) {
            creationDate = null;
        } catch (DatatypeConfigurationException ex) {
            parutionDate = null;
        }

        bd.setCreationDate(creationDate);
        bd.setParution(parutionDate);

        ScenaristesType scenaristes = new ScenaristesType();
        List<IndividuType> inds = getIndividuFromRequest(request, SCENARISTE_ATTR);
        scenaristes.getScenariste().addAll(inds);
        bd.setScenaristes(scenaristes);

        DessinateursType dessinateurs = new DessinateursType();
        inds = getIndividuFromRequest(request, DESSINATEUR_ATTR);
        dessinateurs.getDessinateur().addAll(inds);
        bd.setDessinateurs(dessinateurs);

        ColoristesType coloristes = new ColoristesType();
        inds = getIndividuFromRequest(request, COLORISTE_ATTR);
        coloristes.getColoriste().addAll(inds);
        bd.setColoristes(coloristes);

        LettragesType lettrages = new LettragesType();
        inds = getIndividuFromRequest(request, LETTRAGE_ATTR);
        lettrages.getLettrage().addAll(inds);
        bd.setLettrages(lettrages);

        EncragesType encrages = new EncragesType();
        inds = getIndividuFromRequest(request, ENCRAGE_ATTR);
        encrages.getEncrage().addAll(inds);
        bd.setEncrages(encrages);
        return bd;
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
                    i.setPrenom(ind[0]);
                }
                inds.add(i);
            }
        }
        return inds;
    }
}

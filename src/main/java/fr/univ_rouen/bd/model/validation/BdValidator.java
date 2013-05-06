package fr.univ_rouen.bd.model.validation;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.beans.IndividuType;
import fr.univ_rouen.bd.model.beans.TomeType;
import fr.univ_rouen.bd.model.dao.BdDao;
import java.util.Arrays;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class BdValidator extends AbstractValidator<Bd> {

    private static final String PNG_CONTENT_TYPE = "image/png";
    private static final String JPEG_CONTENT_TYPE = "image/jpeg";
    private static final String ONE_SHOT_VALUE = "One-Shot";
    private static final String PARTIAL_DATE_PATTERN = "((([1-9][0-9]{3}))-((0[1-9])|(1[0-2])))|(((([1-9][0-9]{3}))-((0[1-9])|(1[012])))-((0[1-9])|([12][0-9])|(3[0-1])))";
    private final BdDao bdDao;

    public BdValidator(String fieldName, BdDao bdDao) {
        super(fieldName);
        this.bdDao = bdDao;
    }

    @Override
    protected boolean validate(Bd bd) {
        String isbn = StringUtils.remove(bd.getIsbn(), " ");
        isbn = StringUtils.remove(isbn, "-");
        isbn = StringUtils.remove(isbn, ".");

        Validator<String> stringValidator = new ISBNValidator("isbn");
        stringValidator.setErrorName("isbn");
        if (!stringValidator.isValid(isbn)) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }
        bd.setIsbn(isbn);

        if (bdDao.getByISBN(isbn) != null) {
            addValidationMessage("isbn", "L'ISBN correspond à une bd déjà présente dans la base.");
        }

        stringValidator = new MinLengthValidator("titre", 3);
        stringValidator.setErrorName("titre");
        if (!stringValidator.isValid(bd.getTitre())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }

        if (StringUtils.isBlank(bd.getSerie())) {
            bd.setSerie(ONE_SHOT_VALUE);
        }

        stringValidator = new MinLengthValidator("éditeur", 5);
        stringValidator.setErrorName("editeur");
        if (!stringValidator.isValid(bd.getEditeur())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }

        stringValidator = new MinLengthValidator("résumé", 30);
        stringValidator.setErrorName("resume");
        if (!stringValidator.isValid(bd.getResume())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }

        stringValidator = new MinLengthValidator("format", 5);
        stringValidator.setErrorName("format");
        if (!stringValidator.isValid(bd.getFormat())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }

        stringValidator = new MinLengthValidator("langue", 2);
        stringValidator.setErrorName("langue");
        if (!stringValidator.isValid(bd.getLangue())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }

        stringValidator = new MaxLengthValidator("langue", 2);
        stringValidator.setErrorName("langue");
        if (!stringValidator.isValid(bd.getLangue())) {
            addAllValidationMessage(stringValidator.getValidationMessages());
        }
        bd.setLangue(StringUtils.upperCase(bd.getLangue()));

        if (bd.getParution() == null || !bd.getParution().isValid()) {
            addValidationMessage("parution", "La date de parution est invalide");
        }

        if (bd.getCreationDate() != null && !bd.getCreationDate().isValid()) {
            addValidationMessage("creationDate", "La date de création est invalide");
        }

        if (bd.getPlanches().intValue() <= 0) {
            addValidationMessage("planches", "Le nombre de planche doit être supérieur à 0.");
        }

        Validator<IndividuType> individuValidator = new IndividuTypeValidator();
        if (bd.getScenaristes() != null && CollectionUtils.isNotEmpty(bd.getScenaristes().getScenariste())) {
            int i = 1;
            for (IndividuType ind : bd.getScenaristes().getScenariste()) {
                individuValidator.setFieldName("scenariste " + i);
                individuValidator.setErrorName("scenariste");
                if (!individuValidator.isValid(ind)) {
                    addAllValidationMessage(individuValidator.getValidationMessages());
                }
            }
        } else {
            addValidationMessage("scenariste", "Il doit y avoir au moins un scénariste.");
        }

        if (bd.getColoristes() != null && CollectionUtils.isNotEmpty(bd.getColoristes().getColoriste())) {
            int i = 1;
            for (IndividuType ind : bd.getColoristes().getColoriste()) {
                individuValidator.setFieldName("coloriste " + i);
                individuValidator.setErrorName("coloriste");
                if (!individuValidator.isValid(ind)) {
                    addAllValidationMessage(individuValidator.getValidationMessages());
                }
            }
        }

        if (bd.getDessinateurs() != null && CollectionUtils.isNotEmpty(bd.getDessinateurs().getDessinateur())) {
            int i = 1;
            for (IndividuType ind : bd.getDessinateurs().getDessinateur()) {
                individuValidator.setFieldName("dessinateur " + i);
                individuValidator.setErrorName("dessinateur");
                if (!individuValidator.isValid(ind)) {
                    addAllValidationMessage(individuValidator.getValidationMessages());
                }
            }
        }

        if (bd.getEncrages() != null && CollectionUtils.isNotEmpty(bd.getEncrages().getEncrage())) {
            int i = 1;
            for (IndividuType ind : bd.getEncrages().getEncrage()) {
                individuValidator.setFieldName("encrage " + i);
                individuValidator.setErrorName("encrage");
                if (!individuValidator.isValid(ind)) {
                    addAllValidationMessage(individuValidator.getValidationMessages());
                }
            }
        }

        if (bd.getLettrages() != null && CollectionUtils.isNotEmpty(bd.getLettrages().getLettrage())) {
            int i = 1;
            for (IndividuType ind : bd.getLettrages().getLettrage()) {
                individuValidator.setFieldName("lettrages " + i);
                individuValidator.setErrorName("lettrages");
                if (!individuValidator.isValid(ind)) {
                    addAllValidationMessage(individuValidator.getValidationMessages());
                }
            }
        }

        if (StringUtils.isNotBlank(bd.getImage())) {
            stringValidator = new ExistingUrlValidator("image", Arrays.asList(PNG_CONTENT_TYPE, JPEG_CONTENT_TYPE));
            stringValidator.setErrorName("image");
            if (!stringValidator.isValid(bd.getImage())) {
                addAllValidationMessage(stringValidator.getValidationMessages());
            }
        }

        if (StringUtils.isNotBlank(bd.getDepotLegal())) {
            stringValidator = new PatternMatchingValidator("dépôt légal", PARTIAL_DATE_PATTERN);
            stringValidator.setErrorName("depotLegal");
            if (!stringValidator.isValid(bd.getDepotLegal())) {
                addAllValidationMessage(stringValidator.getValidationMessages());
            }
        }

        if (StringUtils.isNotBlank(bd.getFinImpression())) {
            stringValidator = new PatternMatchingValidator("fin d'impression", PARTIAL_DATE_PATTERN);
            stringValidator.setErrorName("finImpression");
            if (!stringValidator.isValid(bd.getFinImpression())) {
                addAllValidationMessage(stringValidator.getValidationMessages());
            }
        }

        if (bd.getTome() != null) {
            TomeType tome = bd.getTome();
            if (tome.getNumero() < 0) {
                addValidationMessage("tome", "Le numéro du tome doit être supérieur à 0");
            }
        }

        System.out.println(getValidationMessages());
        
        return MapUtils.isEmpty(getValidationMessages());
    }
}

package fr.univ_rouen.bd.model.validation;

import fr.univ_rouen.bd.model.beans.IndividuType;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class IndividuTypeValidator extends AbstractValidator<IndividuType> {

    public IndividuTypeValidator() {
        this(null);
    }

    public IndividuTypeValidator(String fieldName) {
        super(fieldName);
    }

    @Override
    protected boolean validate(IndividuType e) {
        boolean result = true;

        if (e == null) {
            addValidationMessage("Le champ " + getFieldName() + " ne peut être vide.");
            return !result;
        }

        Validator<String> minLengthValidator = new MinLengthValidator("nom du " + getFieldName(), 3);
        if (!minLengthValidator.isValid(e.getNom())) {
            addAllValidationMessage(minLengthValidator.getValidationMessages());
            result = false;
        }

        if (StringUtils.isNotBlank(e.getPrenom())) {
            minLengthValidator.setFieldName("prénom du " + getFieldName());
            if (!minLengthValidator.isValid(e.getPrenom())) {
                addAllValidationMessage(minLengthValidator.getValidationMessages());
                result = false;
            }
        }

        return result;
    }
}

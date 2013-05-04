package fr.univ_rouen.bd.model.validation;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class ISBNValidator extends AbstractValidator<String> {

    public ISBNValidator(String fieldName) {
        super(fieldName);
    }

    @Override
    protected boolean validate(String isbn) {
        int j = 0;
        int k;
        boolean result = false;
        int length = isbn.length();
        switch (length) {
            case 10:
                k = length;
                for (int i = 0; i < 9; i++) {
                    j += Integer.parseInt(""+isbn.charAt(i)) * k;
                    k--;
                }
                String t = StringUtils.upperCase(String.valueOf(isbn.charAt(9)));
                j += (t.equals("X") ? 10 : Integer.parseInt(t));
                result = (j % 11 == 0);
                break;
            case 13:
                k = - 1;
                for (int i = 0; i < length; i++) {
                    j += Integer.parseInt(isbn.charAt(i)+"") * (2 + k);
                    k *= -1;
                }
                result = j % 10 == 0;
                break;
        }
        if (!result) {
            addValidationMessage("L'ISBN saisi n'est pas un ISBN-10 ou un ISBN-13 valide.");
        }
        return result;
    }
}

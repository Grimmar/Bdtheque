package fr.univ_rouen.bd.model.validation;

import java.util.List;

/**
 *
 * @author bissoqu1
 */
public interface Validator<E> {
     
    public List<String> getValidationMessages();

    boolean isValid(E e);

    void setFieldName(String s);
}

package fr.univ_rouen.bd.model.validation;

import java.util.List;
import java.util.Map;

/**
 *
 * @author bissoqu1
 */
public interface Validator<E> {
     
    public Map<String, List<String>> getValidationMessages();

    boolean isValid(E e);

    void setFieldName(String s);
    
    void setErrorName(String s);
}

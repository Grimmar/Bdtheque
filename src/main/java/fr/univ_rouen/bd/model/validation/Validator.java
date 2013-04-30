package fr.univ_rouen.bd.model.validation;

import java.util.List;

/**
 *
 * @author bissoqu1
 */
public interface Validator<E> {
     
    List<String> getValidationMessages();

    boolean isValid(E e);
}

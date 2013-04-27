package fr.univ_rouen.bd.model.validation.validator;

import fr.univ_rouen.bd.model.validation.exception.ValidationException;

/**
 *
 * @author bissoqu1
 */
public interface Validator<E> {
     
    String getValidationMessage();

    void validate(E e) throws ValidationException;
}

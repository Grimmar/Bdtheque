package fr.univ_rouen.bd.model.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractValidator<E> implements Validator<E> {
    
    private List<String> messages;
    
    public AbstractValidator() {
        messages = new ArrayList<String>();
    }
    
    @Override
    public List<String> getValidationMessages() {
        return messages;
    }
    
    protected void addValidationMessage(String message) {
        this.messages.add(message);
    }
    
    protected abstract boolean validate(E e);
    
    @Override
    public boolean isValid(E e) {
        return validate(e);
    }
}

package fr.univ_rouen.bd.model.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractValidator<E> implements Validator<E> {

    private List<String> messages;
    private String fieldName;

    public AbstractValidator(String fieldName) {
        this.fieldName = fieldName;
        messages = new ArrayList<String>();
    }

    @Override
    public List<String> getValidationMessages() {
        return messages;
    }

    protected String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String s) {
        this.fieldName = s;
    }

    protected void addValidationMessage(String message) {
        this.messages.add(message);
    }

    protected void addAllValidationMessage(List<String> l) {
        for (String s : l) {
            addValidationMessage(s);
        }
    }
    
    protected abstract boolean validate(E e);

    @Override
    public boolean isValid(E e) {
        return validate(e);
    }
}

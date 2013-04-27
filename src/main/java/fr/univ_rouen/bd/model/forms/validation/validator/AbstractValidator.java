package fr.univ_rouen.bd.model.forms.validation.validator;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractValidator<E> {

    private String message;
    
    public AbstractValidator() {
    }
    
    public String getMessage() {
        return message;
    }
    
    protected void setMessage(String message) {
        this.message = message;
    }
    
    protected abstract boolean validate(E value);
    
    public boolean isValid(E value) {
        return validate(value);
    }
    
}

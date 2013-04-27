package fr.univ_rouen.bd.model.validation.validator;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractValidator<E> implements Validator<E>{

    private String message;
    
    @Override
    public String getValidationMessage() {
        return message;
    }
    
    protected void setValidationMessage(String message) {
        this.message = message;
    }
    
}

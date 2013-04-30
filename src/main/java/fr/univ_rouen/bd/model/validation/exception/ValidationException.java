package fr.univ_rouen.bd.model.validation.exception;
 
public class ValidationException extends Exception {
    /*
     * Constructeurs
     */
    public ValidationException( String message ) {
        super( message );
    }
 
    public ValidationException( String message, Throwable cause ) {
        super( message, cause );
    }
 
    public ValidationException( Throwable cause ) {
        super( cause );
    }
}

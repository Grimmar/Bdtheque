package fr.univ_rouen.bd.model.export.exception;
 
public class ExportBdException extends Exception {
    /*
     * Constructeurs
     */
    public ExportBdException( String message ) {
        super( message );
    }
 
    public ExportBdException( String message, Throwable cause ) {
        super( message, cause );
    }
 
    public ExportBdException( Throwable cause ) {
        super( cause );
    }
}

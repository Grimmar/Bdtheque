package fr.univ_rouen.bd.model.forms;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bissoqu1
 */
public interface Form<E> {

    Map<String, String> getErrors();

    String getResult();
    
    E validateForm(HttpServletRequest request);

    boolean isValid();

    void clearErrors();
}

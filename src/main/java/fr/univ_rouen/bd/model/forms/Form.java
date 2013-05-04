package fr.univ_rouen.bd.model.forms;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bissoqu1
 */
public interface Form<E> {

    Map<String, List<String>> getErrors();

    String getResult();
    
    E validateForm(HttpServletRequest request);

    boolean isValid();
}

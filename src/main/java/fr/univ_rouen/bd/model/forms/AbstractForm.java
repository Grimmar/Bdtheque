package fr.univ_rouen.bd.model.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractForm<E> {

    private Map<String, String> errors;
    private String result;

    public AbstractForm() {
        errors = new HashMap<String, String>();
    }

    public Map<String, String> getErrors() {
        return MapUtils.unmodifiableMap(errors);
    }

    public abstract E validateForm(HttpServletRequest request);

    protected void setError(String field, String message) {
        errors.put(field, message);
    }

    public String getResult() {
        return result;
    }

    public boolean isValid() {
        return MapUtils.isEmpty(errors);
    }

    protected void setResult(String result) {
        this.result = result;
    }

    public void clearErrors() {
        errors.clear();
    }

    protected static String getFieldValue(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return StringUtils.trim(value);
        }
    }
}

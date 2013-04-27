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
public abstract class AbstractForm<E> implements Form<E> {

    private Map<String, String> errors;
    private String result;

    public AbstractForm() {
        errors = new HashMap<String, String>();
    }

    @Override
    public Map<String, String> getErrors() {
        return MapUtils.unmodifiableMap(errors);
    }

    protected void setError(String field, String message) {
        errors.put(field, message);
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public boolean isValid() {
        return MapUtils.isEmpty(errors);
    }

    protected void setResult(String result) {
        this.result = result;
    }

    @Override
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

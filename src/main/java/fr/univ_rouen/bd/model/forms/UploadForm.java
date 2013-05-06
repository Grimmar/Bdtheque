package fr.univ_rouen.bd.model.forms;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.validation.BdValidator;
import fr.univ_rouen.bd.model.validation.Validator;
import fr.univ_rouen.bd.model.validation.MIMETypeValidator;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.input.CloseShieldInputStream;

/**
 *
 * @author bissoqu1
 */
public class UploadForm extends AbstractForm<Bd> {

    private static final String FILENAME_ATTR = "filename";
    private static final String XML_MIME_TYPE = "text/xml";
    public static final int BUFFER_SIZE = 10240;
    private final BdDao bdDao;

    public UploadForm(BdDao dao) {
        this.bdDao = dao;
    }

    @Override
    public Bd validateForm(HttpServletRequest request) {
        Bd bd = null;
        String filename = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalStateException();
        }

        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (!item.isFormField()) {
                    filename = item.getName();
                    item.getContentType();
                    InputStream in = null;
                    try {
                        in = new CloseShieldInputStream(item.openStream());
                        validateMIMEType(item);
                        DAOFactory instance = DAOFactory.getInstance();
                        Unmarshaller unmarshaller = instance.getJAXBContext().createUnmarshaller();
                        bd = (Bd) unmarshaller.unmarshal(in);
                    } catch (IOException e) {
                    } catch (JAXBException ex) {
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
        } catch (IOException e) {
        }

        if (filename != null && !filename.isEmpty()) {
            filename = filename.substring(filename.lastIndexOf('/') + 1)
                    .substring(filename.lastIndexOf('\\') + 1);

            request.setAttribute(FILENAME_ATTR, filename);
        } else {
            setResult("Echec: Le formulaire est invalide.");
            addError("file", "Le formulaire soumis est invalide.");
            return bd;
        }
        if (bd != null) {
            Validator<Bd> validator = new BdValidator("bd", bdDao);
            if (!validator.isValid(bd)) {
                Map<String, List<String>> map = validator.getValidationMessages();
                for (String s : map.keySet()) {
                    addAllErrors("file", map.get(s));
                }
            }
            if (isValid()) {
                bdDao.add(bd);
                setResult("Succès lors de l'envoi du fichier.");
            } else {
                setResult("Echec de l'envoi du fichier.");
            }
            return bd;
        }
        addError("file", "Le fichier XML est invalide.");
        setResult("Le fichier reçu ne correspond pas à une bd gérée par l'application.");
        return bd;


    }

    private void validateMIMEType(FileItemStream f) {
        Validator<String> validator = new MIMETypeValidator("file", XML_MIME_TYPE);
        String mimeType = f.getContentType();
        if (!validator.isValid(mimeType)) {
            Map<String, List<String>> map = validator.getValidationMessages();
            for (String s : map.keySet()) {
                addAllErrors("file", map.get(s));
            }
        }
    }
}

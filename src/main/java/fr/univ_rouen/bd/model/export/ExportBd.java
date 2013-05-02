/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_rouen.bd.model.export;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;

/**
 *
 * @author David
 */
public class ExportBd {

    private HttpServletResponse response;
    private HttpServletRequest request;

    public ExportBd(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
    }

    public void export() {
        if (request.getParameter("format").equals("pdf")) {
        }
    }

    private void exportInPDF(Bd bd) throws IOException, TransformerException, FOPException, JAXBException {
        FopFactory fopFactory = FopFactory.newInstance();
        DAOFactory dao = DAOFactory.getInstance();

        TransformerFactory factory = TransformerFactory.newInstance();
        Source src = new StreamSource(new File("/WEB-INF/bd-fo.xsl"));
        Transformer transformer = factory.newTransformer(src); // identity transformer

        Fop fop = fopFactory.newFop("application/pdf", response.getOutputStream());
        Marshaller marsh = dao.getJAXBContext().createMarshaller();

        File f = File.createTempFile(bd.getTitre(), "pdf");
        marsh.marshal(bd, f);
        StreamSource source = new StreamSource(f);

        Result res = new SAXResult(fop.getDefaultHandler());
        transformer.transform(source, res);
    }

    private void exportInHtml(Bd bd) {
    }
}

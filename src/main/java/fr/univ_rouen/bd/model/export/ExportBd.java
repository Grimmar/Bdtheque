package fr.univ_rouen.bd.model.export;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.DAOFactory;
import fr.univ_rouen.bd.model.export.exception.ExportBdException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
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
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang.StringUtils;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 *
 * @author David
 */
public class ExportBd {

    private static final String ATTR_FORMAT = "format";
    private static final String PDF_FORMAT = "pdf";
    private static final String HTML_FORMAT = "html";
    private HttpServletResponse response;
    private HttpServletRequest request;

    public ExportBd(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
    }

    public void export(Bd bd, ServletContext context) throws ExportBdException {
        response.reset();

        String format = request.getParameter(ATTR_FORMAT);
        if (StringUtils.equalsIgnoreCase(HTML_FORMAT, format)) {
            exportAsHtml(bd, context);
        } else if (StringUtils.equalsIgnoreCase(PDF_FORMAT, format)) {
            exportAsPdf(bd, context);
        } else {
            throw new ExportBdException("Le paramètre format est manquant dans l'url.");
        }
    }

    private void exportAsPdf(Bd bd, ServletContext context) throws ExportBdException {
        String filename = bd.getTitre() + "." + PDF_FORMAT;
        DAOFactory dao = DAOFactory.getInstance();

        response.setContentType(MimeConstants.MIME_PDF);
        response.setHeader(
                "Content-Disposition", "attachment; filename=\"" + filename + "\"");
        File xml = null;
        try {
            xml = File.createTempFile(bd.getTitre(), ".xml");

            Marshaller marsh = dao.getJAXBContext().createMarshaller();
            marsh.marshal(bd, xml);

        } catch (IOException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la transformation du fichier XML", ex);
        } catch (JAXBException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la récupération des données", ex);
        }

        BufferedOutputStream bos = null;

        try {
            FopFactory fopFactory = FopFactory.newInstance();
            bos = new BufferedOutputStream(response.getOutputStream());
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, bos);

            Source source = new StreamSource(xml);
            Result res = new SAXResult(fop.getDefaultHandler());

            String pathname = context.getRealPath("/WEB-INF/bd-fo.xsl");
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File(pathname)));
            transformer.transform(source, res);
        } catch (FOPException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la transformation du ficher XML", ex);
        } catch (TransformerException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la transformation du ficher XML", ex);
        } catch (IOException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors du transfert du PDF", ex);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                throw new ExportBdException("Une erreur a eu lieu lors du transfert du PDF", ex);
            }
        }
        xml.delete();
    }

    private void exportAsHtml(Bd bd, ServletContext context) throws ExportBdException {
        String filename = bd.getTitre() + "." + HTML_FORMAT;
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        DAOFactory dao = DAOFactory.getInstance();

        File xml = null;
        try {
            xml = File.createTempFile(bd.getTitre(), ".xml");

            Marshaller marsh = dao.getJAXBContext().createMarshaller();
            marsh.marshal(bd, xml);

        } catch (IOException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la transformation du fichier XML", ex);
        } catch (JAXBException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la récupération des données", ex);
        }

        TransformerFactory transfomerFactory = TransformerFactory.newInstance();
        String pathname = context.getRealPath("/WEB-INF/bd.xsl");

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            Transformer transformer = transfomerFactory.newTransformer(new StreamSource(pathname));
            transformer.transform(new StreamSource(xml), new StreamResult(bos));
        } catch (TransformerException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors de la transformation du ficher XML", ex);
        } catch (IOException ex) {
            throw new ExportBdException("Une erreur a eu lieu lors du transfert du PDF", ex);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                throw new ExportBdException("Une erreur a eu lieu lors du transfert du PDF", ex);
            }
        }
        xml.delete();
    }
}

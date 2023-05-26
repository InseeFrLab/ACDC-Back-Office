package com.inseefr.acdc.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service
@Slf4j
public class PdfService {

    public void generateEmptyPdf() throws IOException {
        log.info("Generate empty pdf using Apache Fop");
        File xsltFile = new File("static/emptyPdf.xsl");
        File xmlFile = new File("static/emptyPdf.xml");

        // Temp path
        File pdfDir = new File("static");
        pdfDir.mkdirs();
        File pdfFile = new File(pdfDir,"emptyPdf.pdf");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        OutputStream out = new FileOutputStream(pdfFile);
        out = new BufferedOutputStream(out);

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Source src = new StreamSource(xmlFile);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}

package com.inseefr.acdc.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.fop.apps.*;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
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

    public void generatePdfFromXmlXslt(String xmlContent, String xsltContent) throws IOException {
        log.info("Generate pdf from xml and xsd using Apache Fop");
        File xsltFile = File.createTempFile("temp", ".xsl");
        File xmlFile = File.createTempFile("temp", ".xml");
        File pdfDir = new File("static");
        pdfDir.mkdirs();
        File pdfFile = new File(pdfDir, "generatedPdf.pdf");

        try {
            FileWriter xsltWriter = new FileWriter(xsltFile);
            xsltWriter.write(xsltContent);
            xsltWriter.close();

            FileWriter xmlWriter = new FileWriter(xmlFile);
            xmlWriter.write(xmlContent);
            xmlWriter.close();

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
            } catch (TransformerFactoryConfigurationError e) {
                throw new RuntimeException(e);
            } finally {
                out.close();
            }
        } finally {
            FileUtils.deleteQuietly(xsltFile);
            FileUtils.deleteQuietly(xmlFile);
        }
    }

    public void generatePdfFromXsl(String xslContent) throws IOException {
        log.info("Generate pdf from xsl using Apache Fop");
        File xslFile = File.createTempFile("temp", ".xsl");
        File pdfDir = new File("static");
        pdfDir.mkdirs();
        File pdfFile = new File(pdfDir, "generatedPdf.pdf");

        try {
            FileWriter fileWriter = new FileWriter(xslFile);
            fileWriter.write(xslContent);
            fileWriter.close();

            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            OutputStream out = new FileOutputStream(pdfFile);
            out = new BufferedOutputStream(out);

            try {
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
                TransformerFactory factory = TransformerFactory.newInstance();

                Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
                //Test
                String imageFilePath = "static/Enq_ImageLogos.jpg";
                transformer.setParameter("imageFilePath", new File(imageFilePath).toURI().toString());


                Source src = new StreamSource();
                Result res = new SAXResult(fop.getDefaultHandler());

                transformer.transform(src, res);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } finally {
            FileUtils.deleteQuietly(xslFile);
        }
    }
}

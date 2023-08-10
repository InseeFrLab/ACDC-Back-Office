package com.inseefr.acdc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inseefr.acdc.model.MailVariable;
import com.inseefr.acdc.model.PdfRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.fop.apps.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONObject;
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
                String imageFilePath = "https://acdc.demo.insee.io/images";
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

    public void generatePdfFromXslWithVelocity(String jsonArray) throws IOException {
        log.info("ici");
        log.info("jsonArray : " + jsonArray);
        JSONObject jsonObject = new JSONObject(jsonArray);
        ObjectMapper objectMapper = new ObjectMapper();
        MailVariable mailVariable = objectMapper.readValue(jsonObject.getString("mailVariable"), MailVariable.class);
        String xslContent = jsonObject.getString("xslContent");

        // TODO : Refactor this
        log.info("Generate pdf from xsl using Apache Fop and Velocity");
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Velocity.init();
        VelocityContext context = new VelocityContext();

        context.put("Ue_CiviliteEnqueteur", "Monsieur");
        context.put("Enq_CaractereObligatoire", "oui");
        context.put("imageFilePath", "static/coleman/Enq_ImageLogos.jpg");
        context.put("BddAdressePosteeL1", "M. Claude DUPONT");
        context.put("BddAdressePosteeL2", "APT 15");
        context.put("BddAdressePosteeL4", "17 RUE DES LILAS");
        context.put("BddAdressePosteeL6", "75000 PARIS");
        context.put("BddAdressePosteeL7", "FRANCE");
        context.put("Ue_QuiRepond1", "Toutes les personnes vivant plus de la moitié de l’année avec vous. Les personnes vivant la moitié de l’année avec vous répondent uniquement si elles ont dormi à votre domicile la veille du jour où vous répondez à l’enquête.");
        context.put("Ue_QuiRepond2","Si vous vivez dans une gendarmerie, un foyer ou un autre type de collectivité, vous seul(e) devez répondre.");
        context.put("Ue_QuiRepond3","Une personne du foyer peut répondre pour les autres.");
        context.put("Ue_QuiRepond","Toutes les personnes vivant avec vous doivent également répondre.");
        context.put("Ue_AvisDebutParagraphe1","vous avez été sollicité");
        context.put("Ue_CalcIdentifiant","PFAH9N5");
        context.put("Ue_CalcMotDePasse","<![CDATA[3997a!22]]>");
        context.put("Ue_PrenomEnqueteur", "Geralt");
        context.put("Ue_NomEnqueteur", "de Riv");
        context.put("Ue_TelephoneEnqueteur", "01 02 03 04 05");
        context.put("Modif_Enq_PartieDeroulementEnquete", "oui");
        context.put("Modif_Enq_Prestataire", "oui");

        context.put("Enq_AnneeVisa", mailVariable.getEnq_AnneeVisa());
        context.put("Enq_MinistereTutelle", mailVariable.getEnq_MinistereTutelle());
        context.put("Enq_ParutionJo", mailVariable.isEnq_ParutionJo());
        context.put("Enq_DateParutionJo", mailVariable.getEnq_DateParutionJo());
        context.put("Enq_ServiceCollecteurSignataireNom", mailVariable.getEnq_ServiceCollecteurSignataireNom());
        context.put("Enq_ServiceCollecteurSignataireFonction", mailVariable.getEnq_ServiceCollecteurSignataireFonction());
        context.put("Enq_MailRespOperationnel", mailVariable.getEnq_MailRespOperationnel());

        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "VelocityTemplate", xslContent);

        String modifiedXslContent = writer.toString();
        modifiedXslContent = modifiedXslContent.replace("${imageFilePath}", "'${imageFilePath}'");


        File xslFile = File.createTempFile("temp", ".xsl");
        File pdfDir = new File("static");
        pdfDir.mkdirs();
        File pdfFile = new File(pdfDir, "generatedPdf.pdf");

        try {
            FileWriter fileWriter = new FileWriter(xslFile);
            fileWriter.write(modifiedXslContent);
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

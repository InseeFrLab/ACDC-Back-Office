package com.inseefr.acdc.controllers;

import com.inseefr.acdc.model.PdfRequestBody;
import com.inseefr.acdc.services.ExternalService;
import com.inseefr.acdc.services.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/external")
@Tag(name="External Services Controller")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Slf4j
public class ExternalController {

    @Autowired
    private ExternalService externalService;

    @Autowired
    private PdfService pdfService;

    @Operation(summary = "Retrieve questionnaires from Pogues Back Office")
    @GetMapping(value= "/pogues/questionnaire", produces = "application/json")
    public ResponseEntity<String> getQuestionnairesFromPogues() {
        log.info("Get questionnaires data from Pogues");
        return ResponseEntity.ok(externalService.getQuestionnaires());
    }

    @Operation(summary= "Retrieve series from Magma Back Office")
    @GetMapping(value= "/magma/series", produces = "application/json")
    public ResponseEntity<String> getSeriesFromMagma() {
        log.info("Get series data from Magma");
        return ResponseEntity.ok(externalService.getSeries());
    }

    @Operation(summary="Retrieve operations by series from Magma Back Office")
    @GetMapping(value= "/magma/operations/{id}", produces = "application/json")
    public ResponseEntity<String> getOperationsBySeriesFromMagma(@PathVariable String id) {
        log.info("Get operations by series data from Magma");
        return ResponseEntity.ok(externalService.getSeriesOperationById(id));
    }

    @Operation(summary="(TEMP) concert DataCollection to DDI format & create json to be send to Colectica")
    @GetMapping(value="publish/{id}", produces = "application/json")
    public ResponseEntity<String> publishDataCollection(@PathVariable String id) {
        log.info("Publish data collection");
        return ResponseEntity.ok(externalService.convertAndSendToColectica(id));
    }

    @Operation(summary="Generate an empty pdf file")
    @GetMapping(value="mail/new", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getGeneratedPdf(

    ) throws IOException {
        pdfService.generateEmptyPdf();
        File pdfFile = new File("static/emptyPdf.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generatedPdf.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @Operation(summary="Generate a pdf file from xml and xsl file")
    @PostMapping(value="mail/generate", produces = MediaType.APPLICATION_PDF_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> getGeneratedPdfFromXmlAndXslt(@RequestBody PdfRequestBody pdfRequestBody) throws IOException {
        pdfService.generatePdfFromXmlXslt(pdfRequestBody.getXmlContent(), pdfRequestBody.getXsltContent());
        File pdfFile = new File("static/generatedPdf.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generatedPdf.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @Operation(summary="Generate a pdf file from xsl (.fo) file")
    @PostMapping(value="mail/generate/fo", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<InputStreamResource> getGeneratedPdfFromXsl(@RequestBody String xslContent) throws IOException {
        pdfService.generatePdfFromXslWithVelocity(xslContent);
        File pdfFile = new File("static/generatedPdf.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generatedPdf.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @Operation(summary="Get quality report from Metadonee public API")
    @GetMapping(value="quality/{id}", produces = "application/json")
    public ResponseEntity<String> getQualityReport(@PathVariable String id) {
        log.info("Get quality report from Metadonee public API");
        return ResponseEntity.ok(externalService.getQualityReportById(id));
    }

}


package com.inseefr.acdc.controllers;

import com.inseefr.acdc.services.ExternalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
@Tag(name="External Services Controller")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Slf4j
public class ExternalController {

    @Autowired
    private ExternalService externalService;

    @Operation(summary = "Retrieve questionnaires from Pogues Back Office")
    @GetMapping(value= "/pogues/questionnaire", produces = "application/json")
    public ResponseEntity<String> getQuestionnairesFromPogues() {
        log.info("Get questionnaires data from Pogues");
        return ResponseEntity.ok(externalService.getQuestionnaires());
    }
}


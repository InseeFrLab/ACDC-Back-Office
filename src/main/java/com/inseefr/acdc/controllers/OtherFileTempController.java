package com.inseefr.acdc.controllers;

import com.inseefr.acdc.domain.OtherFileTemp;
import com.inseefr.acdc.services.OtherFileTempService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/other")
@Tag(name="Other Files (mostly temp) Controller")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Slf4j
public class OtherFileTempController {

    @Autowired
    private OtherFileTempService otherFileTempService;

    @Operation(summary = "Retrieve all files")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OtherFileTemp>> getAllOtherFileTemps() {
        log.info("Get all files");
        return ResponseEntity.ok(otherFileTempService.getAllOtherFileTemp());
    }

    @Operation(summary = "Retrieve a file by its ID")
    @GetMapping(value= "/{id}", produces = "application/json")
    public ResponseEntity<OtherFileTemp> getOtherFileTempById(@PathVariable String id) {
        log.info("Get file by id: " + id);
        OtherFileTemp fileObject = otherFileTempService.getOtherFileTempById(id);
        if (fileObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(fileObject);
    }
    @Operation(summary = "Save a new file")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OtherFileTemp> saveOtherFileTemp(@RequestBody OtherFileTemp dataCollection) {
        log.info("Save file");
        return ResponseEntity.ok(otherFileTempService.saveOtherFileTemp(dataCollection));
    }
    @Operation(summary = "Delete a file by its ID")
    @DeleteMapping(value= "/{id}")
    public ResponseEntity<Void> deleteOtherFileTemp(@PathVariable String id) {
        log.info("Delete file by id: " + id);
        otherFileTempService.deleteOtherFileTemp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a file")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OtherFileTemp> updateOtherFileTemp(@RequestBody OtherFileTemp dataCollection) {
        log.info("Update file");
        return ResponseEntity.ok(otherFileTempService.updateOtherFileTemp(dataCollection));
    }
}

package com.inseefr.acdc.controllers;

import com.inseefr.acdc.domain.DataCollection;
import com.inseefr.acdc.services.DataCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-collections")
@Tag(name="Campagnes (Data Collection) Controller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DataCollectionController {
    @Autowired
    private DataCollectionService dataCollectionService;
    @Operation(summary = "Retrieve all data collections")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DataCollection>> getAllDataCollections() {
        log.info("Get all data collections");
        return ResponseEntity.ok(dataCollectionService.getAllDataCollection());
    }

    @Operation(summary = "Retrieve a data collection by its ID")
    @GetMapping(value= "/{id}", produces = "application/json")
    public ResponseEntity<DataCollection> getDataCollectionById(@PathVariable String id) {
        log.info("Get data collection by id: " + id);
        DataCollection dataCollectionObject = dataCollectionService.getDataCollectionById(id);
        if (dataCollectionObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dataCollectionObject);
    }
    @Operation(summary = "Save a new data collection")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataCollection> saveDataCollection(@RequestBody DataCollection dataCollection) {
        log.info("Save data collection");
        return ResponseEntity.ok(dataCollectionService.saveDataCollection(dataCollection));
    }
    @Operation(summary = "Delete a data collection by its ID")
    @DeleteMapping(value= "/{id}")
    public ResponseEntity<Void> deleteDataCollection(@PathVariable String id) {
        log.info("Delete data collection by id: " + id);
        dataCollectionService.deleteDataCollection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a data collection")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataCollection> updateDataCollection(@RequestBody DataCollection dataCollection) {
        log.info("Update data collection");
        return ResponseEntity.ok(dataCollectionService.updateDataCollection(dataCollection));
    }
}

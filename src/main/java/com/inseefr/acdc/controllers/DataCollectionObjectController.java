package com.inseefr.acdc.controllers;

import com.inseefr.acdc.model.DataCollectionObject;
import com.inseefr.acdc.services.DataCollectionObjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/old/data-collections")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DataCollectionObjectController {
    @Autowired
    private DataCollectionObjectService dataCollectionObjectService;
    @Operation(summary = "Retrieve all data collections", tags = "Deprecated",
            deprecated = true, hidden = true)
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DataCollectionObject>> getAllDataCollections() {
        log.info("Get all data collections");
        return ResponseEntity.ok(dataCollectionObjectService.getAllDataCollection());
    }

    @Operation(summary = "Retrieve a data collection by its ID", tags = "Deprecated",
            deprecated = true, hidden = true)
    @GetMapping(value= "/{id}", produces = "application/json")
    public ResponseEntity<DataCollectionObject> getDataCollectionById(@PathVariable String id) {
        log.info("Get data collection by id: " + id);
        DataCollectionObject dataCollectionObject = dataCollectionObjectService.getDataCollectionById(id);
        if (dataCollectionObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dataCollectionObject);
    }
    @Operation(summary = "Save a new data collection", tags = "Deprecated",
            deprecated = true, hidden = true)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataCollectionObject> saveDataCollection(@RequestBody DataCollectionObject dataCollectionObject) {
        log.info("Save data collection");
        return ResponseEntity.ok(dataCollectionObjectService.saveDataCollection(dataCollectionObject));
    }
    @Operation(summary = "Delete a data collection by its ID", tags = "Deprecated",
            deprecated = true, hidden = true)
    @DeleteMapping(value= "/{id}")
    public ResponseEntity<Void> deleteDataCollection(@PathVariable String id) {
        log.info("Delete data collection by id: " + id);
        dataCollectionObjectService.deleteDataCollection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a data collection", tags = "Deprecated",
            deprecated = true, hidden = true)
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataCollectionObject> updateDataCollection(@RequestBody DataCollectionObject dataCollectionObject) {
        log.info("Update data collection");
        return ResponseEntity.ok(dataCollectionObjectService.updateDataCollection(dataCollectionObject));
    }
}

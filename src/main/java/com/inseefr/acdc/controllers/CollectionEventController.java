package com.inseefr.acdc.controllers;

import com.inseefr.acdc.model.CollectionEvent;
import com.inseefr.acdc.services.CollectionEventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CollectionEventController {
    @Autowired
    private CollectionEventService collectionEventService;

    @Operation(summary = "Retrieve a collection event by its ID")
    @GetMapping(value = "/{id}" , produces = "application/json")
    public ResponseEntity<CollectionEvent> getCollectionEventById(@PathVariable String id) {
        log.info("Get collection event by id: " + id);
        CollectionEvent collectionEvent = collectionEventService.getCollectionEventById(id);
        if (collectionEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(collectionEvent);
    }

    @Operation(summary = "Save a new collection event")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CollectionEvent> saveCollectionEvent(@RequestBody CollectionEvent collectionEvent) {
        log.info("Save collection event");
        return ResponseEntity.ok(collectionEventService.saveCollectionEvent(collectionEvent));
    }

    @Operation(summary = "Delete a collection event by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionEvent(@PathVariable String id) {
        log.info("Delete collection event by id: " + id);
        collectionEventService.deleteCollectionEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a collection event", description = "Update a collection event")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CollectionEvent> updateCollectionEvent(@RequestBody CollectionEvent collectionEvent) {
        log.info("Update collection event");
        return ResponseEntity.ok(collectionEventService.updateCollectionEvent(collectionEvent));
    }
}

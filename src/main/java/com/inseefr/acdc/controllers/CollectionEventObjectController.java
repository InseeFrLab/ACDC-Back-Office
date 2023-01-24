package com.inseefr.acdc.controllers;

import com.inseefr.acdc.model.CollectionEventObject;
import com.inseefr.acdc.services.CollectionEventObjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/old/collection-events")
@Slf4j
public class CollectionEventObjectController {
    @Autowired
    private CollectionEventObjectService collectionEventObjectService;

    @Operation(summary = "Retrieve a collection event by its ID", tags = "Deprecated",
            deprecated = true, hidden = true)
    @GetMapping(value = "/{id}" , produces = "application/json")
    public ResponseEntity<CollectionEventObject> getCollectionEventById(@PathVariable String id) {
        log.info("Get collection event by id: " + id);
        CollectionEventObject collectionEventObject = collectionEventObjectService.getCollectionEventById(id);
        if (collectionEventObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(collectionEventObject);
    }

    @Operation(summary = "Save a new collection event" , tags = "Deprecated",
            deprecated = true, hidden = true)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CollectionEventObject> saveCollectionEvent(@RequestBody CollectionEventObject collectionEventObject) {
        log.info("Save collection event");
        return ResponseEntity.ok(collectionEventObjectService.saveCollectionEvent(collectionEventObject));
    }

    @Operation(summary = "Delete a collection event by its ID", tags = "Deprecated",
            deprecated = true, hidden = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionEvent(@PathVariable String id) {
        log.info("Delete collection event by id: " + id);
        collectionEventObjectService.deleteCollectionEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a collection event", tags = "Deprecated",
            deprecated = true, hidden = true)
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CollectionEventObject> updateCollectionEvent(@RequestBody CollectionEventObject collectionEventObject) {
        log.info("Update collection event");
        return ResponseEntity.ok(collectionEventObjectService.updateCollectionEvent(collectionEventObject));
    }
}

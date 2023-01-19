package com.inseefr.acdc.services;

import com.inseefr.acdc.model.CollectionEvent;
import com.inseefr.acdc.repository.CollectionEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionEventService {

    @Autowired
    private CollectionEventRepository collectionEventRepository;

    public CollectionEvent getCollectionEventById(String id) {
        return collectionEventRepository.findById(id).orElse(null);
    }

    public CollectionEvent saveCollectionEvent(CollectionEvent collectionEvent) {
        return collectionEventRepository.save(collectionEvent);
    }

    public void deleteCollectionEvent(String id) {
        collectionEventRepository.deleteById(id);
    }

    public CollectionEvent saveAndFlushCollectionEvent(CollectionEvent collectionEvent) {
        return collectionEventRepository.saveAndFlush(collectionEvent);
    }

    public CollectionEvent updateCollectionEvent(CollectionEvent collectionEvent) {
        return collectionEventRepository.saveAndFlush(collectionEvent);
    }



}

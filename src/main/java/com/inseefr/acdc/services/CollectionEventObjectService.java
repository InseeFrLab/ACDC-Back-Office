package com.inseefr.acdc.services;

import com.inseefr.acdc.model.CollectionEventObject;
import com.inseefr.acdc.repository.CollectionEventObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionEventObjectService {

    @Autowired
    private CollectionEventObjectRepository collectionEventObjectRepository;

    public CollectionEventObject getCollectionEventById(String id) {
        return collectionEventObjectRepository.findById(id).orElse(null);
    }

    public CollectionEventObject saveCollectionEvent(CollectionEventObject collectionEventObject) {
        return collectionEventObjectRepository.save(collectionEventObject);
    }

    public void deleteCollectionEvent(String id) {
        collectionEventObjectRepository.deleteById(id);
    }

    public CollectionEventObject saveAndFlushCollectionEvent(CollectionEventObject collectionEventObject) {
        return collectionEventObjectRepository.saveAndFlush(collectionEventObject);
    }

    public CollectionEventObject updateCollectionEvent(CollectionEventObject collectionEventObject) {
        return collectionEventObjectRepository.saveAndFlush(collectionEventObject);
    }



}

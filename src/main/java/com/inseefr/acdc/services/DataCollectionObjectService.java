package com.inseefr.acdc.services;


import com.inseefr.acdc.model.DataCollectionObject;
import com.inseefr.acdc.repository.DataCollectionObjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DataCollectionObjectService {

    @Autowired
    private DataCollectionObjectRepository dataCollectionObjectRepository;

    public List<DataCollectionObject> getAllDataCollection() {
        return dataCollectionObjectRepository.findAll();
    }

    public DataCollectionObject getDataCollectionById(String id) {
        return dataCollectionObjectRepository.findById(id).orElse(null);
    }

    public DataCollectionObject saveDataCollection(DataCollectionObject dataCollectionObject) {
        LocalDateTime lt
                = LocalDateTime.now();
        dataCollectionObject.setVersionDate(lt.toString());
        return dataCollectionObjectRepository.save(dataCollectionObject);
    }

    public void deleteDataCollection(String id) {
        dataCollectionObjectRepository.deleteById(id);
    }

    public DataCollectionObject saveAndFlushDataCollection(DataCollectionObject dataCollectionObject) {
        return dataCollectionObjectRepository.saveAndFlush(dataCollectionObject);
    }

    public DataCollectionObject updateDataCollection(DataCollectionObject dataCollectionObject) {
        return dataCollectionObjectRepository.saveAndFlush(dataCollectionObject);
    }
}

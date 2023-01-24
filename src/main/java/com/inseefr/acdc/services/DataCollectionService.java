package com.inseefr.acdc.services;

import com.inseefr.acdc.domain.DataCollection;
import com.inseefr.acdc.repository.DataCollectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DataCollectionService {
    @Autowired
    DataCollectionRepository dataCollectionRepository;

    public List<DataCollection> getAllDataCollection() {
        return dataCollectionRepository.findAll();
    }

    public DataCollection getDataCollectionById(String id) {
        return dataCollectionRepository.findById(id).orElse(null);
    }

    public DataCollection saveDataCollection(DataCollection dataCollection) {
        return dataCollectionRepository.save(dataCollection);
    }

    public void deleteDataCollection(String id) {
        dataCollectionRepository.deleteById(id);
    }

    public DataCollection saveAndFlushDataCollection(DataCollection dataCollection) {
        return dataCollectionRepository.saveAndFlush(dataCollection);
    }

    public DataCollection updateDataCollection(DataCollection dataCollection) {

        return dataCollectionRepository.saveAndFlush(dataCollection);
    }
}

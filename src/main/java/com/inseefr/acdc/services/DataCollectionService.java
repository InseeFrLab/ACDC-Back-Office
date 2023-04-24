package com.inseefr.acdc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inseefr.acdc.domain.DataCollection;
import com.inseefr.acdc.repository.DataCollectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public DataCollection saveDataCollection(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> jsonList = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});

        if (jsonList.size() == 0) {
            throw new IllegalArgumentException("JSON array is empty.");
        }

        Map<String, Object> jsonData = jsonList.get(0);

        DataCollection dataCollection = new DataCollection();
        dataCollection.setId((String) jsonData.get("id"));
        dataCollection.setJson(jsonData);


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
